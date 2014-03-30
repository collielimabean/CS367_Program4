import java.util.*;
import java.io.*;

public class CompanyHierarchyMain
{

    public static void main(String[] args)
    {
        // *** Step 1: Check whether exactly one command-line argument is given
        // ***
        // *** Add code for step1 here ***

        // *** Step 2: Check whether the input file exists and is readable ***
        // *** Add code for step2 here ***

        /*
         * Step 3: Load the data from the input file and use it to construct an
         * company tree. Note: employees are to be added to the company tree in
         * the order in which they appear in the text file.
         */
        // *** Add code for step3 here ***

        /*
         * Step 4: Prompt the user to enter command options and process them
         * until the user types x for exit.
         */
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
                    // *** Add code to implement this option ***
                    break;

                case 'c':
                    // *** Add code to implement this option ***
                    break;

                case 'd':
                    // *** Add code to implement this option ***
                    break;

                case 'e':
                    // *** Add code to implement this option ***
                    break;

                case 'r':
                    // *** Add code to implement this option ***
                    break;

                case 's':
                    // *** Add code to implement this option ***
                    break;

                case 'u':
                    // *** Add code to implement this option ***
                    break;

                case 'j':
                    // *** Add code to implement this option ***
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
    }
}
