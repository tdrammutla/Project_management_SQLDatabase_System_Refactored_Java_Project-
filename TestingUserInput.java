/*
 *TestingUserInput Class
 * @author Tebogo Rammutla
 */

// Importing necessary classes to help with project
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;


/**
 * Testing user input Class
 */
public class TestingUserInput 
{
	  private static Scanner scanner = new Scanner(System.in);
	/**
	 * TestingUserInput
	 * @param format the string date format
	 * @param value the string date format
	 * @param locale the locale
	 * @return boolean boolean of whether input entered is valid date format
	 */ 
	public static boolean isValidFormat(String format, String value, Locale locale) 
    	{
			LocalDateTime localdatetime = null;
			DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);
			try 
				{
					localdatetime = LocalDateTime.parse(value, fomatter);
					String result = localdatetime.format(fomatter);
					return result.equals(value);
				}
			catch (DateTimeParseException e) 
				{
					try 
						{
							LocalDate ld = LocalDate.parse(value, fomatter);
							String result = ld.format(fomatter);
							return result.equals(value);
						} 
					catch (DateTimeParseException exp)
						{
							try 
								{
									LocalTime localtime = LocalTime.parse(value, fomatter);
									String result = localtime.format(fomatter);
									return result.equals(value);
								} 
							catch (DateTimeParseException e2) 
								{
									e2.printStackTrace();
								}
						}
				}
			return false;
    	}
	
	  /**
     * IsNumeric
     * @param string the string to test if its numeric
     * @return boolean boolean of whether input entered is numeric
     */ 
    public static boolean isNumeric(String string) 
    {
        Double doubleValue;	 
        try
        {
            // If what the user has entered as a number can be converted to be an integer,return true
            doubleValue = Double.parseDouble(string);
            return true;
        } 
        catch (NumberFormatException e) 
        {
            // If what the user has typed as a number cannot be converted to integer,display a message
            System.out.println(string + " is invalid,either you have put a currecy symbol or not supplied a valid number!! ");
        }
        // Return false if what the user has entered cannot be converted to an integer
        return false; 
    }
    
    /* validDate
     * @return String date
     */
    public static String validDate()
    {
		// Asking user to change the due date of the project
		System.out.print("Enter the new due date of the project (dd/MM/yyyy): ");
		String newDueDate = scanner.next();
		newDueDate += scanner.nextLine();
		String dueDate = "";
		// Testing to see if the user has entered a valid date in valid format
		boolean valid = TestingUserInput.isValidFormat("dd/MM/yyyy", newDueDate, Locale.ENGLISH);
		if(valid)
		{
			dueDate = newDueDate;
		}
		else
		{   
    	/**
    	 * If the user has entered an invalid date or date in invalid format
    	 * Ask the to enter date again until it is correct format
    	 */
			while(!valid)
			{
              System.out.println("Invalid date format,Enter the date(dd/mm/YYYY)");
              newDueDate = scanner.next();
  			  newDueDate += scanner.nextLine();
              valid = TestingUserInput.isValidFormat("dd/MM/yyyy", newDueDate, Locale.ENGLISH);
              if(valid)
              {
                 dueDate = newDueDate;
                 break;
              }
			}
		}
	return dueDate;	
    }
    
    /* validNum
     * @return String totalpaidtodate
     */
    public static String validNum()
    {
    	 // Asking user to change the new total amount of the fee paid to date for the project
        System.out.print("Enter the new total amount of the fee paid to date : ");
        String totalPaidToDate = scanner.next();
        try 
        {
             boolean b2 = TestingUserInput.isNumeric(totalPaidToDate);
            /** If what the user has typed as second number is not a valid number,
             * ask user to enter second number again until the input is a valid number
           */           
             while(b2 != true)
             {
               System.out.print("Please enter the new total amount of the fee paid to date again : ");
               totalPaidToDate = scanner.next();
               // Testing again if input is a valid number
               b2 = TestingUserInput.isNumeric(totalPaidToDate);
             }   	
        }
        catch(NumberFormatException e)
        {
        	e.printStackTrace();
        }
return totalPaidToDate ;
    }

}
