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

import java.util.List;

/**
 * The CompanyHierarchy class generates a general tree of TreeNode objects. 
 */
class CompanyHierarchy
{
    private TreeNode ceo;
    private int numItems;
    
    /**
     * Initializes a CompanyHierarchy object.
     */
    CompanyHierarchy()
    {
        ceo = null;
        numItems = 0;
    }
    
    /**
     * Returns the CEO (root) of the CompanyHierarchy.
     * @return null if there is no CEO, the name of the CEO otherwise.
     */
    String getCEO()
    {
        if(ceo == null)
            return null;
        
        return ceo.getEmployee().getName();
    }
    
    /**
     * Gets the number of employees in the company.
     * @return thue number of employees in the company
     */
    int getNumEmployees()
    {
        return numItems;
    }
    
    /**
     * Gets the number of levels (how many tiers) in the company.
     * @return number of tiers of supervisors/employees in the company.
     */
    int getMaxLevels()
    {
        // TODO Implement
        return 0;
    }
    
    /**
     * Gets the specified employee with id and name.
     * @param id ID of the employee
     * @param name Name of the employee
     * @throws IllegalArgumentException if name is null.
     * @return the Employee with id and name, null if does not exist
     */
    Employee getEmployee(int id, String name)
    {
        if(name == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        return null;
    }

    boolean addEmployee(Employee employee, int supervisorId,
            String supervisorName)
    {
        if(employee == null || supervisorName == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        return true;
    }

    boolean contains(int id, String name)
    {
        if(name == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        return true;
    }
    
    boolean removeEmployee(int id, String name)
    {
        if(name == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        return true;
    }

    boolean replaceEmployee(int id, String name, Employee newEmployee)
    {
        if(name == null || newEmployee == null)
            throw new IllegalArgumentException();
        
        //TODO 
        return true;
    }
    
    List<Employee> getEmployeeWithTitle(String title)
    {
        if(title == null)
            throw new IllegalArgumentException();
        
        // TODO Implement
        return null;
    }

    List<Employee> getEmployeeInJoiningDateRate(String startDate
                                                    , String endDate)
    {
        if(startDate == null || endDate == null)
            throw new IllegalArgumentException();
        
        //TODO Implement
        return null;
    }
    
    List<Employee> getSupervisorChain(int id, String name)
    {
        if(name == null)
            throw new IllegalArgumentException();
        
        //TODO Implement
        return null;
    }
    
    List<Employee> getCoWorkers(int id, String name)
    {
        if(name == null)
            throw new IllegalArgumentException();
        
        //TODO Implement
        return null;
    }
    
}
