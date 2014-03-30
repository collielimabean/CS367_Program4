import java.util.List;

class CompanyHierarchy
{
    CompanyHierarchy()
    {

    }

    String getCEO()
    {
        // TODO Implement
        return null;
    }

    int getNumEmployees()
    {
        // TODO Implement
        return 0;
    }

    int getMaxLevels()
    {
        // TODO Implement
        return 0;
    }

    Employee getEmployee(int id, String name)
    {
        // TODO Implement
        return null;
    }

    boolean addEmployee(Employee employee, int supervisorId,
            String superVisorName)
    {
        // TODO Implement
        return true;
    }

    boolean contains(int id, String name)
    {
        // TODO Implement
        return true;
    }

    boolean removeEmployee(int id, String name)
    {
        // TODO Implement
        return true;
    }

    List<Employee> getEmployeeWithTitle(String title)
    {
        // TODO Implement
        return null;
    }

    List<Employee> getEmployeeInJoiningDateRate(String startDate
                                                    , String endDate)
    {
        //TODO Implement
        return null;
    }
    
    List<Employee> getSupervisorChain(int id, String name)
    {
        //TODO Implement
        return null;
    }
    
    List<Employee> getCoWorkers(int id, String name)
    {
        //TODO Implement
        return null;
    }
    
}
