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
                        //TODO Implement
                        break;
    
                    case 'c':
                        //TODO Implement
                        break;
    
                    case 'd':
                        //TODO Implement
                        break;
    
                    case 'e':
                        //TODO Implement
                        break;
    
                    case 'r':
                        //TODO Implement
                        break;
    
                    case 's':
                        //TODO Implement
                        break;
    
                    case 'u':
                        //TODO Implement
                        break;
    
                    case 'j':
                        //TODO Implement
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
