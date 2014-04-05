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

import java.util.ArrayList;
import java.util.List;

/**
 * The CompanyHierarchy class generates a general tree of TreeNode objects.
 */
class CompanyHierarchy
{
    private static final String INCORRECT_NAME_ID_PAIR 
                                       = "Incorrect employee name for id!";
    
    private static final String INCORRECT_SUPERVISOR_ID 
                                       = "Incorrect supervisor name for id!";
    
    private static final String ID_IN_USE = "Id already used!";
    
    private static final String CEO_REMOVAL_ATTEMPT 
                                       = "Cannot remove CEO of the company!";
    
    private static final String REPLACE_TITLE_MISMATCH 
                           = "Replacement title does not match existing title!";
    
    private static final String DATE_PARSE_ERROR = "Date parsing failed!";
    
    private static final String NO_SUPERVISOR_CHAIN 
                             = "No Supervisor Chain found for that employee!";
    
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

    private int getMaxLevels(TreeNode node)
    {
        if (node.getEmployee() == null)
            return 0;

        if (node.getWorker().isEmpty())
            return 1;

        int maxLevel = 0;

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
        if (name == null)
            throw new IllegalArgumentException();
        
       return getEmployee(ceo, id, name);
    }

    private Employee getEmployee(TreeNode node, int id, String name)
    {
        // TODO
        return null;
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
        if(employee == null || supervisorName == null)
            throw new IllegalArgumentException();

        // TODO Implement
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
        if (name == null)
            throw new IllegalArgumentException();
 
        return contains(ceo, id, name);
    }

    private boolean contains(TreeNode node, int id, String name)
    {
        if (node.getEmployee() == null || node.getWorker().isEmpty())
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
        if (name == null)
            throw new IllegalArgumentException();

        // TODO Implement
        return true;
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
        if (name == null || newEmployee == null)
            throw new IllegalArgumentException();
        // TODO
        return true;
    }

    /**
     * Gets all Employees with the specified title.
     * 
     * @param title
     *            Query containing a title of the employee (e.g. intern)
     * @return List of all Employees with specified title
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
     * @return List of all employees who joined between those dates.
     * @throws IllegalArgumentException
     *             if either date is null
     * @throws CompanyHierarchyException
     *             if dates cannot be parsed
     */
   List<Employee> getEmployeeInJoiningDateRate(String start, String end)
    {
        if (start == null || end == null)
            throw new IllegalArgumentException();

        // TODO Implement
        return null;
    }

    /**
     * Gets the supervisor chain of a specified employee.
     * 
     * @param id
     *            ID of the employee
     * @param name
     *            Name of the employee
     * @return List of employees who are supervisors to the described employee
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if at any point id matches but name does not. Also thrown
     *             when Employee has no supervisors.
     */
    List<Employee> getSupervisorChain(int id, String name)
    {
        if (name == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        
        return null;
    }

    /**
     * Gets all co-workers of a specified employee.
     * 
     * @param id
     *            ID of the employee
     * @param name
     *            Name of the employee
     * @return List of all employees who are co-workers of the employee
     * @throws IllegalArgumentException
     *             if name is null
     * @throws CompanyHierarchyException
     *             if id matches but name does not
     */
    List<Employee> getCoWorkers(int id, String name)
    {
        if (name == null)
            throw new IllegalArgumentException();
        //TODO Implement
        
        //get all workers on the same level
        
        return null;
    }

}
