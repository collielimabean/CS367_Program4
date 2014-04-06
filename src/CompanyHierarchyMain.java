///////////////////////////////////////////////////////////////////////////////
// Title:            CompanyHierarchyMain
// Files:            BadConfigurationFileException.java, CompanyFileReader.java,
//                   CompanyHierarchy.java, CompanyHierarchyMain.java
// Semester:         CS367 Spring 2014
//
// Author:           Allen Hung
// Email:            athung2@wisc.edu
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     William Jen
// Email:            wjen@wisc.edu
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;
import java.util.Scanner;

/**
 * This class exposes a command-line interface for interacting with a
 * CompanyHierarchy.
 */
public class CompanyHierarchyMain
{
    /**
     * Regular expression for the add command [int, String, String, String, int,
     * String]
     */
    public static final String a_regex = "^\\d*,.*,.*,.*,\\d*,.*";

    /**
     * Regular expression for the get co-workers command [int, String]
     */
    public static final String c_regex = "^\\d*,.*";

    /**
     * Regular expression for the remove command [int, String]
     */
    public static final String r_regex = "^\\d*,.*";

    /**
     * Regular expression for the "get supervisor chain" command [int, String]
     */
    public static final String s_regex = "^\\d*,.*";

    /**
     * Regular expression for the replace command [int, String, int, String,
     * String, String]
     */
    public static final String u_regex = "^\\d*,.*,\\d*,.*,.*,.*";

    /**
     * Removes all trailing whitespace in a array of Strings
     * 
     * @param array
     *            String[] to be trimmed
     * @return String[] with trimmed strings
     */
    static String[] trim(String[] array)
    {
        for (int i = 0; i < array.length; i++)
            array[i] = array[i].trim();

        return array;
    }

    /**
     * Entry point for the command-line interface.
     * 
     * @param args
     *            Command line arguments containing a file path containing the
     *            Company data.
     */
    public static void main(String[] args)
    {
        // check for one argument
        if (args.length != 1)
        {
            System.out.println("Usage: java CompanyHierarchyMain FileName");
            return;
        }

        // attempt to read in file
        CompanyFileReader reader = null;

        try
        {
            reader = new CompanyFileReader(args[0]);
        }

        catch (BadConfigurationFileException e)
        {
            System.out.println("Error: Cannot access input file");
            return;
        }

        CompanyHierarchy hierarchy = reader.getCompanyHierarchyFromFile();

        boolean stop = false;
        Scanner stdin = new Scanner(System.in);

        while (!stop)
        {
            System.out.println("\nEnter Command: ");
            String input = stdin.nextLine();
            String remainder = null;

            if (input.length() > 0)
            {
                char option = input.charAt(0);

                if (input.length() > 1)
                {
                    remainder = input.substring(1).trim();
                }

                try
                {
                    switch (option)
                    {

                    /** 'a' adds a new employee to the hierarchy */
                    case 'a':
                        if (remainder == null || !remainder.matches(a_regex))
                            break;

                        String[] a_data = trim(remainder.split(","));

                        int e_id = Integer.parseInt(a_data[0]);
                        int supervisor_id = Integer.parseInt(a_data[4]);

                        Employee freshMeat = new Employee(a_data[1], e_id,
                                a_data[2], a_data[3]);

                        if (hierarchy.getEmployee(freshMeat.getId(),
                                freshMeat.getName()) != null)
                        {
                            System.out.println("Employee already exists!");
                            break;
                        }

                        boolean added = hierarchy.addEmployee(freshMeat,
                                supervisor_id, a_data[5]);

                        if (added)
                            System.out.println("Employee added");

                        else
                            System.out.println("Cannot add employee as"
                                    + " supervisor was not found!");

                        break;

                    case 'c':
                        if (remainder == null || !remainder.matches(c_regex))
                            break;

                        String[] c_data = trim(remainder.split(","));

                        List<Employee> coworkers = hierarchy.getCoWorkers(
                                Integer.parseInt(c_data[0]), c_data[1]);

                        if (coworkers == null)
                            System.out.println("Employee not found!");

                        else if (coworkers.isEmpty())
                            System.out
                                    .println("The employee has no co-workers");

                        else
                            for (Employee coworker : coworkers)
                                System.out.println(coworker.getName());

                        break;

                    /** Displays information about the company tree. */
                    case 'd':
                        System.out.println("# of employees in company tree: "
                                + hierarchy.getNumEmployees());

                        System.out.println("max levels in company tree: "
                                + hierarchy.getMaxLevels());

                        System.out.println("CEO: " + hierarchy.getCEO());

                        break;

                    /** Gets all employees with specified title. */
                    case 'e':
                        if (remainder == null)
                            break;

                        List<Employee> emp_title = hierarchy
                                .getEmployeeWithTitle(remainder.trim());

                        if (emp_title == null)
                            System.out.println("Employee not found!");

                        // print out names
                        else
                            for (Employee e : emp_title)
                                System.out.println(e.getName());

                        break;

                    /**
                     * Gets all of the employees who joined within a certain
                     * start and end date.
                     */
                    case 'j':
                        if (remainder == null)
                            break;

                        String[] dates = remainder.split(",");

                        // get employees with join dates
                        List<Employee> emp_dates = hierarchy
                                .getEmployeeInJoiningDateRange(dates[0],
                                        dates[1]);

                        if (emp_dates == null)
                            System.out.println("Employee not found!");

                        // print out names
                        else
                            for (Employee e : emp_dates)
                                System.out.println(e.getName());

                        break;

                    /** Removes the specified employee from the company tree. */
                    case 'r':
                        if (remainder == null || !remainder.matches(r_regex))
                            break;

                        String[] r_data = trim(remainder.split(","));

                        int remove_id = Integer.parseInt(r_data[0]);

                        boolean successRemove = hierarchy.removeEmployee(
                                remove_id, r_data[1]);

                        if (successRemove)
                            System.out.println("Employee removed");

                        else
                            System.out.println("Employee not found!");

                        break;

                    /** Gets the supervisor chain of an employee. */
                    case 's':
                        if (remainder == null || !remainder.matches(s_regex))
                            break;

                        String[] s_data = trim(remainder.split(","));

                        List<Employee> emp_supervisor = hierarchy
                                .getSupervisorChain(
                                        Integer.parseInt(s_data[0]), s_data[1]);

                        if (emp_supervisor == null)
                            System.out.println("Employee not found!");

                        else
                            for (Employee supervisor : emp_supervisor)
                                System.out.println(supervisor.getName());

                        break;

                    /** Replaces a current employee with another. */
                    case 'u':
                        if (remainder == null || !remainder.matches(u_regex))
                            break;

                        String[] u_data = trim(remainder.split(","));

                        int oldId = Integer.parseInt(u_data[0]);
                        int newId = Integer.parseInt(u_data[2]);

                        Employee newEmployee = new Employee(u_data[3], newId,
                                u_data[4], u_data[5]);

                        boolean successReplace = hierarchy.replaceEmployee(
                                oldId, u_data[1], newEmployee);

                        if (successReplace)
                            System.out.println("Employee replaced");

                        else
                            System.out.println("Employee not found!");

                        break;

                    /** Exits the program. */
                    case 'x':
                        stop = true;
                        System.out.println("exit");
                        break;

                    default:
                        break;
                    }
                }

                catch (CompanyHierarchyException e)
                {
                    System.out.println(e.getMessage());
                    continue;
                }

            }
        }

        // close the scanner to ensure no resource leak
        stdin.close();
    }
}