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
 * 
 *
 */
public class CompanyHierarchyMain
{
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        //check for one argument
        if(args.length != 1)
        {
            System.out.println("Usage: java CompanyHierarchyMain FileName");
            return;
        }
        
        //read in file
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

                switch (option)
                {
                    case 'a':
                        String a_command_validator 
                                        = "^\\d,[^,]+,[^,]+,[^,]+,\\d,[^,]+";                        
                        
                        //forces input: [#, String, String, String, #, String]
                        if(remainder != null
                                    && remainder.matches(a_command_validator))
                        {
                            String[] a_data = remainder.split(",");
                            
                            int e_id = Integer.parseInt(a_data[0]);
                            int supervisor_id = Integer.parseInt(a_data[4]);
                            
                            Employee sup_exist 
                              = hierarchy.getEmployee(supervisor_id, a_data[5]);
                            
                            if(sup_exist == null)
                            {
                                System.out.println("Cannot add employee as" 
                                                + " supervisor was not found!");
                                break;
                            }
                            
                            Employee freshMeat = new Employee(a_data[1]
                                                                , e_id
                                                                , a_data[2]
                                                                , a_data[3]);
                            
                            boolean success = hierarchy.addEmployee(freshMeat
                                                                , supervisor_id
                                                                , a_data[5]);
                            
                            if(success)
                                System.out.println("Employee added!");
                        }
                        
                        break;
    
                    case 'd':
                        System.out.println("# of employees in company tree: " 
                                            + hierarchy.getNumEmployees());
                        
                        System.out.println("# max levels in company tree: "
                                            + hierarchy.getMaxLevels());
                        
                        System.out.println("CEO: " + hierarchy.getCEO());
                        
                        break;
    
                    case 'e':
                        if(remainder == null)
                            break;
                        
                        List<Employee> emp_title 
                                    = hierarchy.getEmployeeWithTitle(remainder);
                        
                        if(emp_title == null)
                        {
                            System.out.println("Employee not found!");
                        }
                        
                        for(Employee e : emp_title)
                            System.out.println(e.getName());
                        
                        break;
    
                    case 'r':
                        //input ok if [#, String] and no trailing commas
                        if(remainder != null 
                                    && remainder.matches("^\\d,[^,]+"))
                        {
                            String[] r_data = remainder.split(",");
                            
                            int remove_id = Integer.parseInt(r_data[0]);
                            
                            boolean success 
                               = hierarchy.removeEmployee(remove_id, r_data[1]);
                            
                            if(success)
                            {
                                System.out.println("Employee removed");
                            }
                            
                            else
                            {
                                Employee ceo_check 
                                        = new Employee(r_data[1]
                                                        , remove_id
                                                        , null
                                                        , null);
                                
                                if(!ceo_check.equals(hierarchy.getEmployee(
                                                          remove_id
                                                        , hierarchy.getCEO())))
                                {
                                    System.out.println("Employee not found!");
                                }
                            }
                        }
                        
                        break;
    
                    case 's':
                        //input ok if [#, String] and no trailing commas
                        if(remainder != null 
                                    && remainder.matches("^\\d,[^,]+"))
                        {
                            
                            String[] s_data = remainder.split(",");
                            
                            List<Employee> emp_supervisor =
                                    hierarchy.getSupervisorChain(
                                                    Integer.parseInt(s_data[0])
                                                    , s_data[1]);
                            
                            if(emp_supervisor == null)
                            {
                                System.out.println("Employee not found!");
                                break;
                            }
                            
                            for(Employee e : emp_supervisor)
                                System.out.println(e.getName());
                        }
                        
                        break;
    
                    case 'u':
                        //forces input: 
                        //[#, String, #, String, String, String]
                        String u_command_validater 
                                = "^\\d,[^,]+,\\d,[^,]+,[^,]+,[^,]+";
                        
                        if(remainder != null
                                && remainder.matches(u_command_validater))
                        {
                            String[] u_data = remainder.split(",");
                            
                            int oldId = Integer.parseInt(u_data[0]);
                            int newId = Integer.parseInt(u_data[2]);
                            
                            Employee exist_check 
                                    = hierarchy.getEmployee(oldId, u_data[1]);
                            
                            if(exist_check == null)
                            {
                                System.out.println("Employee not found!");
                                break;
                            }
                            
                            Employee newEmployee = new Employee(u_data[3]
                                                                , newId
                                                                , u_data[4]
                                                                , u_data[5]);
                            
                            boolean success 
                                        = hierarchy.replaceEmployee(oldId
                                                    , u_data[1]
                                                    , newEmployee);
                            
                            if(success)
                                System.out.println("Employee replaced");
                        }
                        
                        break;
    
                    case 'j':
                        if(remainder == null)
                            break;
                        
                        String[] dates = remainder.split(",");
                        
                        if(dates.length != 2)
                            break;
                        
                        //get employees with join dates
                        List<Employee> emp_dates 
                            = hierarchy.getEmployeeInJoiningDateRate(
                                                            dates[0], dates[1]);
                        
                        if(emp_dates == null)
                        {
                            System.out.println("Employee not found!");
                            break;
                        }
                        
                        //print out names
                        for(Employee e : emp_dates)
                        {
                            System.out.println(e.getName());
                        }
                        
                        break;
    
                    case 'x':
                        stop = true;
                        System.out.println("exit");
                        break;
    
                    default:
                        break;
                }
            }
        }
        
        //close the scanner to ensure no resource leak
        stdin.close();
    }
}