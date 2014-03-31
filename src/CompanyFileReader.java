///////////////////////////////////////////////////////////////////////////////
// Main Class File:  CompanyHierarchyMain.java
// File:             CompanyFileReader.java
// Semester:         CS367 Spring 2014
//
// Author:           William Jen <wjen@wisc.edu>
// CS Login:         jen
// Lecturer's Name:  Professor Jim Skrentny
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// Pair Partner:     Allen Hung <athung2@wisc.edu>
// CS Login:         ahung
// Lecturer's Name:  Professor Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class parses and builds a CompanyHierarchy from a given company
 * hierarchy file.
 */
public class CompanyFileReader
{
    private File fileName;
    private CompanyHierarchy hierarchy;
    
    /**
     * Constructs a CompanyFileReader object.
     * @param filePath A path to the file containing the data to load.
     * @throws BadConfigurationFileException if the file cannot be accessed
     * or cannot be read (invalid data).
     */
    public CompanyFileReader(String filePath)
                                        throws BadConfigurationFileException
    {
        fileName = new File(filePath);
        hierarchy = new CompanyHierarchy();
        
        boolean success = parseFile();
        
        if(!success)
            throw new BadConfigurationFileException();
    }
    
    /**
     * Returns the built CompnanyHierarchy from the given configuration file.
     * @return a CompanyHierarchy object containing the Company data.
     */
    public CompanyHierarchy getCompanyHierarchyFromFile()
    {
        return hierarchy;
    }
    
    /**
     * This method parses the file and builds the CompanyHierarchy.
     * @return true if successful in parsing file, false otherwise
     */
    private boolean parseFile()
    {
        Scanner input = null;
        
        try
        {
            input = new Scanner(fileName);
            
            while(input.hasNextLine())
            {
                String[] fields = input.nextLine().split(",");
                
                //ensure no trailing whitespace
                for(int i = 0; i < fields.length; i++)
                    fields[i] = fields[i].trim();
                
                //invalid input if not 4 or 6 elements in length
                if(!(fields.length == 4 || fields.length == 6))
                {
                    input.close();
                    return false;
                }
                
                Employee e = new Employee(fields[0]
                                            , Integer.parseInt(fields[1])
                                            , fields[2]
                                            , fields[3]);
                
                int supervisorId = -1;
                
                if(fields.length == 6)
                    supervisorId = Integer.parseInt(fields[5]);
                
                //if the employee has a supervisor, set supervisorId
                if(supervisorId != -1)
                {
                    hierarchy.addEmployee(e, supervisorId, fields[4]);
                }
                
                //if the employee is the CEO
                else
                {
                    hierarchy.addEmployee(e, e.getId(), null);
                }
                
            }
            
            input.close();
        } 
        
        catch (NumberFormatException | FileNotFoundException e)
        {
            if(input != null)
                input.close();
            
            return false;
        }
        
        return true;
    }
}
