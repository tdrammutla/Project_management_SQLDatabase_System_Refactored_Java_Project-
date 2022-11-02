/*
 *Project management system for a small structural engineering firm to keep track of the many projects on which they work
 * @author Tebogo Rammutla
 */

// Importing necessary classes from java core library
import java.util.*;
import java.text.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;

 /**
  * Poised Class
  */
public class Poised 
{ 
    //Properties of the class 
    private static Person person;
    private static Scanner scanner = new Scanner(System.in);
    private static int projectNo;
    private static String buildingType;
    private static String projectStatus = "not finalised";
    private static Project project;
    private static Poised poised;
    private static Connection connection;
    private static Statement statement;
    private static int rowsAffected;
    private static ResultSet results;
   
    /**
     * Constructor of Poised Class
     * @param project the Project to set
     * @param person the person to set
     */
    public Poised(Project project,Person person)
    {
        //Properties of Poised class
        Poised.project = project;
        Poised.person = person;
    }
    
    /**
     * getProject
     * @return project object
     */
    public Project getProject()
    {   // returns Project object
        return Poised.project;  
    } 
    
    /**
     * getPerson
     * @return person object
     */ 
    public Person getPerson()
    {  
        return Poised.person;  
    } 
    
    /**
     * updateContractorDetails
     * @param contractor object to set
     * @param connection database connection
     * @param statement statement query to execute
     * @param proNumber Project to update
     * @exception SQLException throwing SQL exception 
     */ 
    private static void  updateContractorDetails(Statement statement,String proNumber)
    {    
    	try
    	{
    		System.out.print("What would you like to change ?(telephone,email or both?) : ");
            String choice = scanner.next();
            if(choice.equalsIgnoreCase("telephone"))
                {
                    //Updating the contractor telephone details in contractor class                       
                    System.out.print("Please enter the contractor telephone number : ");
                    String telephoneNo = scanner.next();
                    rowsAffected = statement.executeUpdate("UPDATE Contractor SET telephoneNumber  = '" + telephoneNo + "' WHERE projectNumber = '"+ proNumber + "'");
                }
            else if(choice.equalsIgnoreCase("email"))
                { 
                    //Updating the contractor email details in contractor class    
                    System.out.print("Please enter the contractor email address : ");
                    String emailAddress = scanner.next();
                    rowsAffected = statement.executeUpdate("UPDATE Contractor SET emailAddress  = '" + emailAddress + "' WHERE projectNumber = '"+ proNumber + "'"); 
                }
            else if(choice.equalsIgnoreCase("both"))
                { 
                    //Updating the contractor contact details in contractor class                   
                    System.out.print("Please enter the contractor telephone number : ");
                    String telephoneNo = scanner.next();
                    rowsAffected = statement.executeUpdate("UPDATE Contractor SET telephoneNumber  = '" + telephoneNo + "' WHERE projectNumber = '"+ proNumber + "'");                    
                    System.out.print("Please enter the contractor email address : ");
                    String emailAddress = scanner.next();
                    rowsAffected = statement.executeUpdate("UPDATE Contractor SET emailAddress  = '" + emailAddress + "' WHERE projectNumber = '"+ proNumber + "'");                       
                }
            System.out.println("Details updated successfully !!");
    	}
    	catch(SQLException e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    /**
     * create Person
     * @return person object
     */ 
    private static Person createPersonObject()
    {
        Customer customer = new Customer("Tebogo","Rammutla","015 564 3456","tebza@hotmail.com","23 Vilakazi Street,Soweto,Johannesburg");
        Architect architect = new Architect("Jones","Makgoka","012 564 3456","jonesM@yahoo.com","34 Mole Street,Montana,Pretoria");
        Contractor contractor = new Contractor("Sara","Bartman","041 564 3456","sara.bartman@hotmail.com","11 Seapoint Street,Summerstrand,Port Elizaberth");
        return new Person(customer,architect,contractor);
    }
    
    /**
     * create Project 
     * @param projectName project name
     * @param buildingType project buildingType
     * @param physicalAddress project physicalAddress
     * @param erfNo  project Erf number
     * @param projectTotalFee  project Project total fees
     * @param totalPaid project  total fees paid to date
     * @param projectDeadline project projectDeadLine
     * @return project object
     */ 
    private static Project createProjectObject(String projectName,String buildingType,String physicalAddress,String erfNo, String projectTotalFee, String totalPaid,String projectDeadline)
    {
        projectNo ++;
        String projNo = "Project" + projectNo;
        return new Project(projNo,projectName,buildingType,physicalAddress,erfNo,projectTotalFee,totalPaid,projectDeadline);   
    }
    
    /**
     * Generating Customer Invoice method 
     * @param proNumber The project number to generate and invoice for
     * @param person The person object to get customer details
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    private static void  generatingInvoice(String proNumber,Connection connection,Statement statement)
    {	
    	try
    	{
				results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
				double totalProjectFee = 0;
				double totalAmountPaid = 0;
				double total = 0;
				while (results.next()) 
				{
    			 totalProjectFee += results.getDouble("projectTotalFee");
    			totalAmountPaid += results.getDouble("totalAmountPaidToDate");
				}	
				if (totalProjectFee != totalAmountPaid ) 
				{
					results = statement.executeQuery("SELECT * FROM Customer Where ProjectNumber = '" + proNumber + "'");
					System.out.println("CUSTOMERS 'INVOICE :\n");
					while (results.next()) 
					{
						System.out.println("Project Number : " + results.getString("projectNumber") + "\nCustomer Name : " + results.getString("name") + "\nCustomer Surname : " +
    						            results.getString("surname") + "\nTelephone Number"+ results.getString("telephoneNumber")+ "\nEmail Address : " + results.getString("emailAddress") + "\nPhysical Address : " +
    						            results.getString("physicalAddress"));
					}
					total += totalProjectFee - totalAmountPaid;
					System.out.println("The total amount the customer still has to pay is : R" + Math.round(total));
				}
    	}
    	catch(SQLException e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    /**
     * Finalizing a project
     * @param person The person object to get customer details
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */ 
    private static void  finalizeProject(Statement statement)
    {
        	System.out.println("\nEnter project number to finalize (eg :'Project1' : ");
        	String proNumber = scanner.next();
        	try
        	{
        		results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
        		while (results.next()) 
    			{
        		rowsAffected = statement.executeUpdate("UPDATE Project SET projectStatus='Finalised' WHERE projectNumber = '" + proNumber + "'" );	
        		System.out.println("Project is now finalised !!");
        		generatingInvoice(proNumber,connection,statement);
    			}
       			while (!results.next()) 
    			{
    				System.out.println("Project Number not found in the database :");
    				System.out.println("Enter project number to finalise (eg :'Project1' : ");
    	        	proNumber = scanner.next();
        			results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
        			while (results.next()) 
        			{
        				rowsAffected = statement.executeUpdate("UPDATE Project SET projectStatus='Finalised' WHERE projectNumber = '" + proNumber + "'" );	
        				System.out.println("Project is now finalised !!");
        				generatingInvoice(proNumber,connection,statement);
        				return;
        			}
    			}
        	}
        	catch(SQLException e)
        	{
        		e.printStackTrace();
        	}
    }
    
    /**
     * Creating a new File and writing new project details to it
     * @param poised passing poised object as parameter
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    private static void  writtingProjectDataTDatabase(Poised poised,Statement statement)
    {  
        try 
        {       	
        	rowsAffected = statement.executeUpdate("Insert into Project Values('"+ poised.getProject().getProjectNumber()
        			+ "','" + poised.getProject().getProjectName() + "','" + poised.getProject().getBuildingType() + "','"
        			+ poised.getProject().getPhysicalAddress() + "','" + poised.getProject().getErfNumber() + "'," + 
        			poised.getProject().getProjectTotalFee() + "," + poised.getProject().getTotalAmountPaidToDate() +
        			",'" + poised.getProject().getProjectDeadlineDate() + "','" + projectStatus + "')");
        	System.out.println("Query complete, " + rowsAffected + " rowsadded.");
         }
         catch(SQLException e)
         {
        	e.printStackTrace();
         }
        System.out.println("\n\nProject added to the database !!\n");
    }
    
    /**
     * Creating a new File and writing new project details to it
     * @param poised passing poised object as parameter
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    private static void  writtingPersonDataToDatabase(Poised poised,Statement statement)
    {  
        try 
        {
        	rowsAffected = statement.executeUpdate("Insert into Customer Values('"+ poised.getProject().getProjectNumber()
        			+ "','" + poised.getPerson().getCustomer().getName() + "','" + poised.getPerson().getCustomer().getSurname() 
        			+ "','" + poised.getPerson().getCustomer().getTelephoneNumber() + "','" + poised.getPerson().getCustomer().getEmailAddress()
        			+ "','" + poised.getPerson().getCustomer().getPhysicalAddress() + "')");
        	
        	rowsAffected = statement.executeUpdate("Insert into Architect Values('"+ poised.getProject().getProjectNumber()
        			+ "','" + poised.getPerson().getArchitect().getName() + "','" + poised.getPerson().getArchitect().getSurname() 
        			+ "','" + poised.getPerson().getArchitect().getTelephoneNumber() + "','" + poised.getPerson().getArchitect().getEmailAddress()
        			+ "','" + poised.getPerson().getArchitect().getPhysicalAddress() + "')");
        	
        	rowsAffected = statement.executeUpdate("Insert into Contractor Values('"+ poised.getProject().getProjectNumber()
        			+ "','" + poised.getPerson().getContractor().getName() + "','" + poised.getPerson().getContractor().getSurname() 
        			+ "','" + poised.getPerson().getContractor().getTelephoneNumber() + "','" + poised.getPerson().getContractor().getEmailAddress()
        			+ "','" + poised.getPerson().getContractor().getPhysicalAddress() + "')");        	
        }
        catch(SQLException e)
        {
        	e.printStackTrace();
        }
    } 
    
    /**
     * Creating a project  method for the user to add a new project
     * @return Project returning Project created
     */
    public static Project createProjectObject() 
    {
        System.out.print("Enter the project name : ");
        String projectName = scanner.next();
        System.out.print("What type of building is being designed ? \n(house/apartment block/store etc): ");
        buildingType = scanner.next();
        System.out.print("What is the physical address for the project? : ");
        String physicalAddress = scanner.next();
        physicalAddress += scanner.nextLine();
        System.out.print("What is the ErfNo : ");
        String erfNo = scanner.next();
        System.out.print("What is the total fee being charged for the project? (Do not input currency symbol !!) : ");
        String projectTotalFee = "";
        String totalPaid = "";
        try 
        {
            projectTotalFee = scanner.next();
            boolean isnumeric = TestingUserInput.isNumeric(projectTotalFee );  
            /** If what the user has typed as first number is not a valid number,
              * ask user to enter first number again until the input is a valid number
            */
                while(!isnumeric)
                {
                //Asking user to enter first number again
                    System.out.print("Please enter the total fee being charged for the project again? :  ");
                    projectTotalFee = scanner.next();
                    // Testing again if input is a valid number
                    isnumeric = TestingUserInput.isNumeric( projectTotalFee);
                }
            System.out.print("What is the total amount paid to date?(Do not input currency symbol !!) : ");
            totalPaid = scanner.next();
            boolean numeric = TestingUserInput.isNumeric(totalPaid);
            /** If what the user has typed as second number is not a valid number,
             * ask user to enter second number again until the input is a valid number
           */           
                while(!numeric)
                {
                    System.out.print("Please enter the total fee being charged for the project again? : ");
                    totalPaid = scanner.next();
                    // Testing again if input is a valid number
                    numeric = TestingUserInput.isNumeric(totalPaid);
                }	
        }
        catch(NumberFormatException e)
        {
        	e.printStackTrace();
        }
        System.out.print("What is the deadline for the project (dd/MM/yyyy) : ");
        String projectDeadline = scanner.next();
        projectDeadline += scanner.nextLine();
        String dueDate="";
        // Testing to see if the user has entered a valid date in valid format
        boolean valid = TestingUserInput.isValidFormat("dd/MM/yyyy", projectDeadline, Locale.ENGLISH);
        if(valid)
        {
        	 dueDate = projectDeadline;
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
                  projectDeadline = scanner.next();
                  valid = TestingUserInput.isValidFormat("dd/MM/yyyy", projectDeadline, Locale.ENGLISH);
                  if(valid)
                  {
                    dueDate = projectDeadline;
                    break;
                   }
            }
        }
        return createProjectObject(projectName,buildingType,physicalAddress,erfNo,projectTotalFee,totalPaid,dueDate);  
    }
    
    /**
     * updateProject method for the user to update a specific project
     * @param proNumber Project number to update
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    public static void updateProject(String proNumber,Statement statement) 
    {  
    	try 
    	{
    		// Asking user to state whether they need to change the due date of the project
    		System.out.print("Do you want to update the due date of the project ?(yes/no)");
    		String updateDueDate = scanner.next();
    		String dueDate = "";
    		if(updateDueDate.equalsIgnoreCase("yes") )
    		{
    			dueDate = TestingUserInput.validDate(); 
    			//Updating the due date of the project in Project Database
    			rowsAffected = statement.executeUpdate("UPDATE Project SET projectDeadlineDate = '" + dueDate + "' WHERE projectNumber = '"+ proNumber + "'");     			
    		}
    		else if(updateDueDate.equalsIgnoreCase("no") )
    		{
    			System.out.println();
    		}
        	else 
        	{
                while(!updateDueDate.equalsIgnoreCase("yes") || !updateDueDate.equalsIgnoreCase("No"))
                {
                	System.out.print("'" + updateDueDate + "' is invalid answer,Do you want to update the due date of the project ?(yes/no)");
                	updateDueDate = scanner.nextLine();
                	if(updateDueDate.equalsIgnoreCase("yes") )
                	{
                		dueDate = TestingUserInput.validDate(); 
                	}
                	else if(updateDueDate.equalsIgnoreCase("no") )
                	{
                		break;
                	}
                	else 
                	{
                    	System.out.print("'" + updateDueDate + "' is invalid answer,Do you want to update the due date of the project ?(yes/no)");
                    	updateDueDate = scanner.nextLine();
                    	if(updateDueDate.equalsIgnoreCase("yes") )
                    	{
                    		dueDate = TestingUserInput.validDate();                    		 
                    	}
                    	else if(updateDueDate.equalsIgnoreCase("no") )
                    	{
                    		break;
                    	}
                	}
        			//Updating the due date of the project in Project Database
        			rowsAffected = statement.executeUpdate("UPDATE Project SET projectDeadlineDate = '" + dueDate + "' WHERE projectNumber = '"+ proNumber + "'"); 
        			break;
                 }
            }
        System.out.print("Do you want to update the total amount of the fee paid to date?(yes/no) : ");
        String updateAmountPaid = scanner.next();
        String totalPaidToDate="";
        if(updateAmountPaid.equalsIgnoreCase("yes"))
        {       
            totalPaidToDate = TestingUserInput.validNum();
        	//Updating the new total amount of the fee paid to date in Project class
           rowsAffected = statement.executeUpdate("UPDATE Project SET totalAmountPaidToDate  = '" + totalPaidToDate + "' WHERE projectNumber = '"+ proNumber + "'"); 
        }
		else if(updateAmountPaid.equalsIgnoreCase("no"))
		{
			System.out.println();
		}
        else
        	{
              while(!updateAmountPaid.equalsIgnoreCase("yes") || !updateAmountPaid.equalsIgnoreCase("No"))
              {	
                    System.out.print("'" + updateAmountPaid + "'is invalid answer,Do you want to update the total amount of the fee paid to date?(yes/no) : ");
                    updateAmountPaid = scanner.next();
                	if(updateAmountPaid.equalsIgnoreCase("yes"))
            		{
                		totalPaidToDate = TestingUserInput.validNum();                         
            		}
                	else if(updateAmountPaid.equalsIgnoreCase("no"))
                	{
                		break;
                	}
                	else
                	{
                        System.out.print("'" + updateAmountPaid + "'is invalid answer,Do you want to update the total amount of the fee paid to date?(yes/no) : ");
                        updateAmountPaid = scanner.next();
                    	if(updateAmountPaid.equalsIgnoreCase("yes"))
                		{
                    		totalPaidToDate = TestingUserInput.validNum();
                		}
                    	else if(updateAmountPaid.equalsIgnoreCase("no"))
                    	{
                    		break;
                    	}	
                	}
                    //Updating the new total amount of the fee paid to date in Project class
                    rowsAffected = statement.executeUpdate("UPDATE Project SET totalAmountPaidToDate  = '" + totalPaidToDate + "' WHERE projectNumber = '"+ proNumber + "'");       	
                }
        	}
        System.out.print("Do you want to update the contractor 's contact details details?(yes/no) : ");
        String updateContractorDetails = scanner.next();
        
        if(updateContractorDetails.equalsIgnoreCase("yes"))
        { 
            updateContractorDetails(statement,proNumber);  
        }
        else if(updateContractorDetails.equalsIgnoreCase("no"))
        {
			System.out.println(); 	
        }
        	
        else 
        	{
                while(!updateContractorDetails.equalsIgnoreCase("yes") || !updateContractorDetails.equalsIgnoreCase("No"))
                {
                	System.out.print("'" + updateContractorDetails + "' is invalid input,Do you want to update the contractor 's contact details details?(yes/no) : ");
                	updateContractorDetails = scanner.next();
                	if(updateContractorDetails.equalsIgnoreCase("yes"))
                		{ 
                			updateContractorDetails(statement,proNumber);
                			break;
                		}
                    else if(updateContractorDetails.equalsIgnoreCase("no"))
                    {
            			System.out.println("Thank you !!");
                    	break;      	
                    }
                    else 
                    {
                    	System.out.print("'" + updateContractorDetails + "' is invalid input,Do you want to update the contractor 's contact details details?(yes/no) : ");
                    	updateContractorDetails = scanner.next();
                    	if(updateContractorDetails.equalsIgnoreCase("yes"))
                    		{ 
                    			updateContractorDetails(statement,proNumber);
                    			break;
                    		}
                        else if(updateContractorDetails.equalsIgnoreCase("no"))
                        {
                			System.out.println("Thank you !!");
                        	break;      	
                        }                    	
                    }     	
                }
        	}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}     
    }
    
    /**
     * search for a project method for the user to search for a project the want to view
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    public static void searchProject(Connection connection, Statement statement)
    {
        	System.out.println("\nEnter project number to search (eg :'Project1' : ");
        	String proNumber = scanner.next();
        	try
        		{
        			results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
        			while (results.next()) 
        			{
        				System.out.println("FOUND :");
        				System.out.println("Project Number : " + results.getString("projectNumber") + "\nProject Name : " + results.getString("projectName") + "\nBuilding Type : " +
        						            results.getString("buildingType") + "\nPhysical Address : "+ results.getString("physicalAddress")+ "\nErf Number : " + results.getString("erfNumber") + "\nProject Total Fee : R " +
        						            results.getString("ProjectTotalFee") + "\nTotal Amount Paid to date : R "+ results.getString("totalAmountPaidToDate") + "\nProject DeadLine Date : "+ results.getString("projectDeadlineDate")
        						           + "\nProject Status : " + results.getString("projectStatus"));
        			}
        			while (!results.next()) 
        			{
        				System.out.println("Project Number not found in the database :");
        				System.out.println("\nEnter project number to search (eg :'Project1' : ");
        	        	proNumber = scanner.next();
            			results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
            			while (results.next()) 
            			{
            				System.out.println("FOUND :");
            				System.out.println("Project Number : " + results.getString("projectNumber") + "\nProject Name : " + results.getString("projectName") + "\nBuilding Type : " +
            						            results.getString("buildingType") + "\nPhysical Address : "+ results.getString("physicalAddress")+ "\nErf Number : " + results.getString("erfNumber") + "\nProject Total Fee : R " +
            						            results.getString("ProjectTotalFee") + "\nTotal Amount Paid to date : R "+ results.getString("totalAmountPaidToDate") + "\nProject DeadLine Date : "+ results.getString("projectDeadlineDate")
            						           + "\nProject Status : " + results.getString("projectStatus"));
            				return;
            			}
        			}
        		}		
        	catch(SQLException e)
        	{
        		e.printStackTrace();
        	}  	
    }
    
    /**
     * viewAllProjects method for the user to view all projects
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    public static void viewAllProjects(Statement statement) 
    {
    	try 
    	{
			        System.out.println("ALL Projects :");
			        System.out.println();
    				results = statement.executeQuery("SELECT * FROM Project");
    				while (results.next())
    				{
        				System.out.println("Project Number : " + results.getString("projectNumber") + "\nProject Name : " + results.getString("projectName") + "\nBuilding Type : " +
					            results.getString("buildingType") + "\nPhysical Address : "+ results.getString("physicalAddress")+ "\nErf Number : " + results.getString("erfNumber") + "\nProject Total Fee : R " +
					            results.getString("ProjectTotalFee") + "\nTotal Amount Paid to date : R "+ results.getString("totalAmountPaidToDate") + "\nProject DeadLine Date : "+ results.getString("projectDeadlineDate")
					           + "\nProject Status : " + results.getString("projectStatus"));
        				System.out.println();
    				}		
    	}
    	catch(SQLException e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    /**
     * viewAllProjects method for the user to view all projects
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    public static void viewAllProjectsStilltobeCompleted(Connection connection,Statement statement) 
    {
    	try 
    	{
			results = statement.executeQuery("SELECT * FROM Project Where projectStatus = 'not finalised'");
			System.out.println("FOUND :");
			while (results.next()) 
			{
				System.out.println("Project Number : " + results.getString("projectNumber") + "\nProject Name : " + results.getString("projectName") + "\nBuilding Type : " +
						            results.getString("buildingType") + "\nPhysical Address : "+ results.getString("physicalAddress")+ "\nErf Number : " + results.getString("erfNumber") + "\nProject Total Fee : R " +
						            results.getString("ProjectTotalFee") + "\nTotal Amount Paid to date : R "+ results.getString("totalAmountPaidToDate") + "\nProject DeadLine Date : "+ results.getString("projectDeadlineDate")
						           + "\nProject Status : " + results.getString("projectStatus"));
			}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}	
    }
    
    /**
     * viewAllProjects method for the user to view all projects
     * @param connection database connection
     * @param statement statement query to execute
     * @exception SQLException throwing SQL exception 
     */
    public static void viewAllProjectspastdue(Statement statement) 
    {
    	try 
    	{
            Date currentDate=new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateFormated = dateFormatter.format(currentDate);
			results = statement.executeQuery("SELECT * FROM Project Where projectDeadlineDate < '" + dateFormated + "'");
			System.out.println("FOUND :");
			while (results.next()) 
			{
				System.out.println("Project Number : " + results.getString("projectNumber") + "\nProject Name : " + results.getString("projectName") + "\nBuilding Type : " +
						            results.getString("buildingType") + "\nPhysical Address : "+ results.getString("physicalAddress")+ "\nErf Number : " + results.getString("erfNumber") + "\nProject Total Fee : R " +
						            results.getString("ProjectTotalFee") + "\nTotal Amount Paid to date : R "+ results.getString("totalAmountPaidToDate") + "\nProject DeadLine Date : "+ results.getString("projectDeadlineDate")
						           + "\nProject Status : " + results.getString("projectStatus"));
			}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    /**
     * makeChoice method for the user to choose what they want to do
     */
    public static void makeChoice() 
    {
        String choice="";
    	System.out.println("\nWould you like add new project?  To search for a specific project?  To view all existing projects?  To update a project? ");
        System.out.println("Select option below  : \n 'ap' for : Adding new Project\n 'sc' for : Searching for a project\n 'va' for : Viewing all projects \n 'up' for : Updating  a project\n 'fp' for : Finalizing a project\n 'lc' for : List of project still to be completed\n 'lp' for : List of project past due date");
        choice = scanner.next();
        try
        {
    		// Connect to the library_db database, via the jdbc:mysql:channel on localhost (this PC)
    		// Use username "otheruser", password "swordfish".
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PoisePMS?useSSL=true","otheruser","swordfish");
    		// Create a direct line to the database for running our queries
    		statement = connection.createStatement();	
    		// If the user choose to search for a specific project,call search method
    		if(choice.equalsIgnoreCase("sc")) 
    		{
    			searchProject(connection,statement);
    			makeChoice();
    		}
    		// If the user choose to view all projects,call viewAllProjects method
    		else if(choice.equalsIgnoreCase("va")) 
    		{
    			viewAllProjects(statement); 
    			makeChoice();
    		}   
    		/*
    		 * If the user choose to update a specific project,get user to enter project number to update
    		 * if project exists call updateProject method else give a message and ask user to enter project
    		 * number again
    		 */
    		else if(choice.equalsIgnoreCase("up")) 
    		{
    			System.out.println("\nEnter project number to update (eg :'Project1' : ");
    			String proNumber = scanner.next();
    			try
    			{
    				results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
    				while (results.next()) 
    				{
    					updateProject(proNumber,statement);
    					makeChoice();
    				}
    				while (!results.next()) 
    				{
    					System.out.println("Project Number not found in the database :");
    					System.out.println("\nEnter project number to search (eg :'Project1' : ");
    					proNumber = scanner.next();
    					results = statement.executeQuery("SELECT * FROM Project Where ProjectNumber = '" + proNumber + "'");
    					while (results.next()) 
    					{
    						updateProject(proNumber,statement);
    						makeChoice();	
    					}
    				}
    			}
    			catch(SQLException e) 
    			{
    				e.printStackTrace();
    			}	
    		} 
    		// If the user choose to add project,call addProject method
    		else if(choice.equalsIgnoreCase("ap")) 
    		{
    			person = createPersonObject();
    			project = createProjectObject() ;
    			poised = new Poised(project,person); 
    			writtingProjectDataTDatabase(poised,statement);
    			writtingPersonDataToDatabase(poised,statement);
    			makeChoice();   
    		} 
    		// If the user choose to finalize project,call finalize Project method
    		else if (choice.equalsIgnoreCase("fp"))
    		{
    			// Getting a person object to extract customer 's details
    			person = createPersonObject();
    			finalizeProject(statement);
    			makeChoice();
    		}
    		// if the user want to view all project still to be completed
    		else if(choice.equalsIgnoreCase("lc")) 
    		{
    			viewAllProjectsStilltobeCompleted(connection,statement);
    			makeChoice();	
    		}
    		// if the user want to view all project past due date
    		else if(choice.equalsIgnoreCase("lp")) 
    		{
    			viewAllProjectspastdue(statement);
    			makeChoice();	
    		}
    		// If the user entered invalid choice,ask them to re enter choice
    		else
    		{
    			System.out.println(choice + " is an invalid choice !!\n");
    			makeChoice();
    		}
        }
        catch(SQLException e)
        {
        	e.printStackTrace();	
        }
    }
    
    /**
     * main method
     * @param args[]
     */
    public static void main(String [] args)
    {
    	makeChoice(); 
    }
}
