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

class CompanyHierarchy
{
    CompanyHierarchy()
    {

    }

    String getCEO()
    {
        // TODO Implement
    	// recursive method where you hunt with getSupervisor until throws null
    	//base case return getEmployee(getName());
        return null;
    }

    int getNumEmployees()
    {
        // TODO Implement
    	//involves using a getLeft and getRight recursion style
        return 0;
    }

    int getMaxLevels()
    {
        // TODO Implement
    	if (not root)
    		return 0;
    	int leftSub = getMaxLevels(left subtree)
    	int rightSub = getMaxLevels(right subtree)
    	//there should be an adding of heights, but i don't really know where to implement it
        return 0;
    }

    Employee getEmployee(int id, String name)
    {
        // TODO Implement
    	involve the left and right moving methods
    	add check into recursive method
        return null;
    }

    boolean addEmployee(Employee employee, int supervisorId,
            String supervisorName)
    {
    	//REHEAPIFY WHYYYYYYYY EW
        // TODO Implement
        return true;
    }

    boolean contains(int id, String name)
    {
        // TODO Implement
    	//left and right subtree recursion madness like the ones above
        return true;
    }
    
    boolean removeEmployee(int id, String name)
    {
        // TODO Implement
    	//reheapify, not even touching for now 
        return true;
    }

    boolean replaceEmployee(int id, String name, Employee newEmployee)
    {
        //TODO 
    	//left and right recursive searching followed by updateEmployee(newEmployee)
        return true;
    }
    
    List<Employee> getEmployeeWithTitle(String title)
    {
        // TODO Implement
    	//left and right subtree recursion with data haul
    	//.getemployee().getTitle()
        return null;
    }

    List<Employee> getEmployeeInJoiningDateRate(String startDate,
                                                     String endDate)
    {
        //TODO Implement
    	//same as above with an if statement enclosing the .getEmployee().getDateOfJoining()
        return null;
    }
    
    List<Employee> getSupervisorChain(int id, String name)
    {
        //TODO Implement
    	//non-recursive search? followed by recursive back up to root with .getSupervisor()
    	//base case is the root 
        return null;
    }
    
    List<Employee> getCoWorkers(int id, String name)
    {
        //TODO Implement
    	//does this mean all the ones on their same level or just everyone under the same parent?
        return null;
    }
    
}
