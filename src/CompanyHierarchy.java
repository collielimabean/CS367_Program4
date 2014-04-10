///////////////////////////////////////////////////////////////////////////////
// Main Class File:  CompanyHierarchyMain.java
// File:             CompanyHierarchy.java
// Semester:         CS367 Spring 2014
//
// Author:           Allen Hung <athung2@wisc.edu>
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     William Jen <wjen@wisc.edu>
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The CompanyHierarchy class generates a general tree of TreeNode objects.
 */
class CompanyHierarchy
{
    /** Exception message when an incorrect name and id pair is detected. */
    private static final String INCORRECT_NAME_ID_PAIR = "Incorrect employee name for id!";

    /**
     * Exception message when an incorrect supervisor name and id pair is
     * detected
     */
    private static final String INCORRECT_SUPERVISOR_ID = "Incorrect supervisor name for id!";

    /**
     * Exception message when an add operation attempts to add an employee with
     * an already existing ID.
     */
    private static final String ID_IN_USE = "Id already used!";

    /** Exception message when a remove operation attempts to remove the CEO. */
    private static final String CEO_REMOVAL_ATTEMPT = "Cannot remove CEO of the company!";

    /**
     * Exception message when a replacement employee does not have the same
     * title as the old employee.
     */
    private static final String REPLACE_TITLE_MISMATCH = "Replacement title does not match existing title!";

    /** Exception message when passed in dates are unable to parsed. */
    private static final String DATE_PARSE_ERROR = "Date parsing failed!";

    /** Exception message when an employee does not have a supervisor chain. */
    private static final String NO_SUPERVISOR_CHAIN = "No Supervisor Chain found for that employee!";

    /** Format for dates in CompanyHierarchy: Month/Day/Year */
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    private TreeNode ceo;
    private int numItems;

    /**
     * Initializes a CompanyHierarchy object.
     */
    CompanyHierarchy()
    {
        ceo = new TreeNode(null, null);
        numItems = 0;
    }

    /**
     * Returns the CEO (root) of the CompanyHierarchy.
     * 
     * @return null if there is no CEO, the name of the CEO otherwise.
     */
    String getCEO()
    {
        if (ceo.getEmployee() == null)
            return null;

        return ceo.getEmployee().getName();
    }

    /**
     * Gets the number of employees in the company.
     * 
     * @return the number of employees in the company
     */
    int getNumEmployees()
    {
        return numItems;
    }

    /**
     * Gets the number of levels (how many tiers) in the company.
     * 
     * @return number of tiers of supervisors/employees in the company.
     */
    int getMaxLevels()
    {
        return getMaxLevels(ceo);
    }

    /**
     * Gets the max levels of the tree. (helper method)
     * 
     * @param node
     *            TreeNode of the Tree to determine max level
     * @return max levels of employees in the company
     */
    private int getMaxLevels(TreeNode node)
    {
        if (node.getEmployee() == null)
            return 0;

        if (node.getWorker().isEmpty())
            return 1;

        int maxLevel = 0;

        // calculate max depth of each subtree
        // if greater than maxLevel, assign it to maxLevel
        for (TreeNode sub : node.getWorker())
        {
            int childLevel = getMaxLevels(sub);
            maxLevel = (maxLevel < childLevel) ? childLevel : maxLevel;
        }

        return maxLevel + 1;
    }

    /**
     * Gets the specified employee with id and name.
     * 
     * @param id
     *            ID of the employee
     * @param name
     *            Name of the employee
     * @return the Employee with id and name, null if does not exist
     * @throws IllegalArgumentException
     *             if name is null.
     * @throws CompanyHierarchyException
     *             if id does not match name
     */
    Employee getEmployee(int id, String name)
    {
        if (name == null || id < 0)
            throw new IllegalArgumentException();

        return getEmployee(ceo, id, name).getEmployee();
    }

    /**
     * Gets the TreeNode of the requested Employee. (helper method)
     * 
     * @param node
     *            TreeNode to begin searching
     * @param id
     *            Query ID of employee
     * @param name
     *            Query name of employee
     * @return the TreeNode containing requested Employee, null if does not
     *         exist
     */
    private TreeNode getEmployee(TreeNode node, int id, String name)
    {
        if (!contains(id, name))
            return null;

        if (node.getEmployee().getName().equals(name)
                && node.getEmployee().getId() == id)
            return node;

        else
        {
            // for every child, check if there exists an employee
            // with name and id.
            for (TreeNode child : node.getWorker())
            {
                TreeNode result = getEmployee(child, id, name);

                if (result != null)
                    return result;
            }

            return null;
        }
    }

    /**
     * Adds the requested employee into the tree given a supervisor id and name.
     * 
     * @param employee
     *            new Employee to add into the company.
     * @param supervisorId
     *            ID of the supervisor
     * @param supervisorName
     *            Name of the supervisor
     * @return true if successful, false otherwise
     * @throws IllegalArugmentException
     *             if employee or the supervisor's name is null.
     * @throws CompanyHierarchyException
     *             if supervisor's name does not match supervisor's id.
     */
    boolean addEmployee(Employee employee, int supervisorId,
            String supervisorName)
    {
        if (employee == null || supervisorId < 0)
            throw new IllegalArgumentException();

        else if (ceo.getEmployee() == null && supervisorName == null)
        {
            ceo.updateEmployee(employee);
            numItems++;
            return true;
        }

        else if (supervisorName == null)
            throw new IllegalArgumentException();

        // if the list already contains employee, then return false
        // if error is thrown, rethrow w/ appropriate message
        try
        {
            if (contains(employee.getId(), employee.getName()))
                return false;
        }

        catch (CompanyHierarchyException e)
        {
            throw new CompanyHierarchyException(ID_IN_USE);
        }

        // if the supervisor !exist, return false
        // if error thrown, rethrow w/ appropriate message
        // if checks pass, then add to supervisor
        try
        {
            TreeNode supervisor = getEmployee(ceo, supervisorId, supervisorName);

            if (supervisor == null)
                return false;

            // add to given supervisor
            supervisor.addWorker(new TreeNode(employee, supervisor));
            numItems++;
        }

        catch (CompanyHierarchyException e)
        {
            throw new CompanyHierarchyException(INCORRECT_SUPERVISOR_ID);
        }

        return true;
    }

    /**
     * Checks whether an employee exists with the specified id and name.
     * 
     * @param id
     *            Query ID of the employee
     * @param name
     *            Query name of the employee
     * @return true if the company contains an employee, false otherwise
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if id matches but name does not
     */
    boolean contains(int id, String name)
    {
        if (name == null || id < 0)
            throw new IllegalArgumentException();

        return contains(ceo, id, name);
    }

    /**
     * Checks whether an employee with specified id and name in the tree.
     * (helper method)
     * 
     * @param node
     *            TreeNode to search
     * @param id
     *            Query ID of employee
     * @param name
     *            Query name of employee
     * @return true if found, false otherwise
     */
    private boolean contains(TreeNode node, int id, String name)
    {
        if (node.getEmployee() == null)
            return false;

        Employee current = node.getEmployee();

        if (current.getId() == id && current.getName().equals(name))
            return true;

        if (current.getId() == id && !current.getName().equals(name))
            throw new CompanyHierarchyException(INCORRECT_NAME_ID_PAIR);

        else
            for (TreeNode subWorker : node.getWorker())
                if (contains(subWorker, id, name))
                    return true;

        return false;
    }

    /**
     * Removes "fires" the requested employee from the company.
     * 
     * @param id
     *            ID of the to-be-removed employee
     * @param name
     *            Name of the to-be-removed employee
     * @return true if removal successful, false otherwise (does not exist)
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if specified employee is the CEO
     */
    boolean removeEmployee(int id, String name)
    {
        if (name == null || id < 0)
            throw new IllegalArgumentException();

        TreeNode terminate = getEmployee(ceo, id, name);
        TreeNode supervisor = terminate.getSupervisor();

        if (terminate.getEmployee() == null)
            return false;

        if (terminate.equals(ceo))
            throw new CompanyHierarchyException(CEO_REMOVAL_ATTEMPT);

        // update supervisors of the children and update supervisor list
        for (TreeNode node : terminate.getWorker())
        {
            node.updateSupervisor(supervisor);
            supervisor.addWorker(node);
        }

        // remove the employee
        return supervisor.getWorker().remove(terminate);
    }

    /**
     * Replaces a current employee with another.
     * 
     * @param id
     *            ID of the employee to be replaced
     * @param name
     *            Name of the employee to be replaced
     * @param newEmployee
     *            the new Employee to replace the current.
     * @return true if successful, false otherwise
     * @throws IllegalArgumentException
     *             if name or newEmployee is null
     * @throws CompanyHierarchyException
     *             if id matches but name does not. Also thrown when a Employee
     *             is found but title does not match.
     */
    boolean replaceEmployee(int id, String name, Employee newEmployee)
    {
        if (name == null || newEmployee == null || id < 0)
            throw new IllegalArgumentException();

        TreeNode employeeNode = getEmployee(ceo, id, name);
        Employee employee = employeeNode.getEmployee();

        // if employee is null, means that no such employee exists
        if (employee == null)
            return false;

        if (!employee.getTitle().equals(newEmployee.getTitle()))
            throw new CompanyHierarchyException(REPLACE_TITLE_MISMATCH);

        employeeNode.updateEmployee(newEmployee);

        return true;
    }

    /**
     * Gets all Employees with the specified title.
     * 
     * @param title
     *            Query containing a title of the employee (e.g. intern)
     * @return List of all Employees with specified title or null if no employee
     *         with requested title found
     * @throws IllegalArgumentException
     *             if title is null
     */
    List<Employee> getEmployeeWithTitle(String title)
    {
        if (title == null)
            throw new IllegalArgumentException();

        List<Employee> collection = new ArrayList<Employee>();
        getEmployeeWithTitle(collection, title, ceo);

        return (collection.isEmpty()) ? null : collection;
    }

    /**
     * Adds all employees with specified title into the list. (helper method)
     * Called for its side-effect. Precondition: collection and title are not
     * null
     * 
     * @param collection
     *            Collection of all employees with given title
     * @param title
     *            A specified employee's title
     * @param node
     *            A TreeNode specifying where to search
     */
    private void getEmployeeWithTitle(List<Employee> collection, String title,
            TreeNode node)
    {
        if (node.getEmployee() == null)
            return;

        Employee current = node.getEmployee();

        if (current.getTitle().equals(title))
            collection.add(current);

        for (TreeNode sub : node.getWorker())
            getEmployeeWithTitle(collection, title, sub);
    }

    /**
     * Gets all Employees who joined between the specified date range.
     * 
     * @param startDate
     *            Earliest bound on date in format MM/DD/YYYY
     * @param endDate
     *            Latest bound on date in format MM/DD/YYYY
     * @return List of all employees who joined between those dates, or null if
     *         no employees found.
     * @throws IllegalArgumentException
     *             if either date is null
     * @throws CompanyHierarchyException
     *             if dates cannot be parsed
     */
    List<Employee> getEmployeeInJoiningDateRange(String start, String end)
    {
        if (start == null || end == null)
            throw new IllegalArgumentException();

        List<Employee> employees = new ArrayList<Employee>();

        try
        {
            getEmployeeInJoiningDateRange(employees, ceo, new DateChecker(
                    DATE_FORMAT, start, end));
        }

        catch (ParseException e)
        {
            throw new CompanyHierarchyException(DATE_PARSE_ERROR);
        }

        return (employees.isEmpty()) ? null : employees;
    }

    /**
     * Gets all employees who joined within a specified time range. Called for
     * its side-effect.
     * 
     * @param employees
     *            List of employees who joined within a certain range
     * @param node
     *            TreeNode to search
     * @param checker
     *            DateChecker object to compare passed in date
     * @throws ParseException
     *             if date is not parseable
     */
    private void getEmployeeInJoiningDateRange(List<Employee> employees,
            TreeNode node, DateChecker checker) throws ParseException
    {
        if (node.getEmployee() == null)
            return;

        if (checker.isWithinDateRange(node.getEmployee().getDateOfJoining()))
            employees.add(node.getEmployee());

        for (TreeNode child : node.getWorker())
            getEmployeeInJoiningDateRange(employees, child, checker);
    }

    /**
     * Gets the supervisor chain of a specified employee.
     * 
     * @param id
     *            ID of the employee
     * @param name
     *            Name of the employee
     * @return List of employees who are supervisors to the described employee,
     *         or null if no such employee exists
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if at any point id matches but name does not. Also thrown
     *             when Employee has no supervisors.
     */
    List<Employee> getSupervisorChain(int id, String name)
    {
        if (name == null || id < 0)
            throw new IllegalArgumentException();

        TreeNode employeeNode = getEmployee(ceo, id, name);

        // no such employee exists
        if (employeeNode == null)
            return null;

        // if no supervisor (ceo)
        if (employeeNode.getSupervisor() == null)
            throw new CompanyHierarchyException(NO_SUPERVISOR_CHAIN);

        // create a traversal node to avoid destroying tree
        TreeNode traversal = employeeNode.getSupervisor();

        List<Employee> supervisors = new ArrayList<Employee>();

        // add supervisors to the list
        while (traversal != null)
        {
            supervisors.add(traversal.getEmployee());
            traversal = traversal.getSupervisor();
        }

        return supervisors;
    }

    /**
     * Gets all co-workers of a specified employee.
     * 
     * @param id
     *            ID of the employee
     * @param name
     *            Name of the employee
     * @return List of all employees who are co-workers of the employee or null
     *         if no such employee exists
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if id matches but name does not
     */
    List<Employee> getCoWorkers(int id, String name)
    {
        if (name == null || id < 0)
            throw new IllegalArgumentException();

        // get TreeNode associated with specified id and name
        TreeNode employeeNode = getEmployee(ceo, id, name);

        if (employeeNode.getEmployee() == null)
            return null;

        // coworkers are those on the same level (i.e. supervisor's children)
        List<TreeNode> coworkerNode = employeeNode.getSupervisor().getWorker();
        List<Employee> coworkers = new ArrayList<Employee>();

        // add unique employees to the list
        for (TreeNode node : coworkerNode)
            if (!node.equals(employeeNode))
                coworkers.add(node.getEmployee());

        return coworkers;
    }

    /**
     * This class' sole purpose is to determine if a String containing a date is
     * within a range of dates.
     */
    private class DateChecker
    {
        private DateFormat formatter;
        private Date start;
        private Date end;

        /**
         * Instantiates a DateChecker object.
         * 
         * @see java.util.SimpleDateFormat for date format Strings.
         * @param dateFormat
         *            The format to parse dates
         * @param startDate
         *            Starting date (earliest bound)
         * @param endDate
         *            Ending date (latest bound)
         * @throws ParseException
         *             if either passed in dates cannot be parsed.
         */
        DateChecker(String dateFormat, String startDate, String endDate)
                throws ParseException
        {
            formatter = new SimpleDateFormat(dateFormat);
            start = formatter.parse(startDate);
            end = formatter.parse(endDate);
        }

        /**
         * Checks whether a string containing a date is within the date range.
         * 
         * @param date
         *            String containing a date in the specified format.
         * @return true if within range, false otherwise
         * @throws ParseException
         *             if date cannot be parsed
         */
        boolean isWithinDateRange(String date) throws ParseException
        {
            Date check = formatter.parse(date);

            if (start.compareTo(check) <= 0 && end.compareTo(check) >= 0)
                return true;

            return false;
        }
    }

}
