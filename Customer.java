/*
 *Project Customer Class
 * @author Tebogo Rammutla
 */

/**
  * Customer Class
  */
public class Customer
{
    //Properties of Customer class
    private String name;
    private String surname;
    private String telephoneNumber;
    private String emailAddress;
    private String physicalAddress;
    
    /**
     * Constructor of Customer Class
     * @param name the customer name to set
     * @param surname the customer surname to set
     * @param telephoneNumber the customer telephoneNumber to set
     * @param emailAddress the customer email address to set
     * @param physicalAddress the customer physical address to set
     */
    public Customer(String name,String surname,String telephoneNumber,String emailAddress,String physicalAddress )
    {
    	this.setName(name);
    	this.surname = surname;
    	this.setTelephoneNumber(telephoneNumber);
    	this.setEmailAddress(emailAddress);
    	this.setPhysicalAddress(physicalAddress);   
    
    }
    
    /**
     * toString
     * @return customer string object
     */ 
    public String toString()
    {
        String customer;
                customer= "\nCustomer name : " + getName()
                + "\nCustomer surname : " + surname
                + "\nCustomer telephone number : " + getTelephoneNumber()
                + "\nCustomer email_address : " + getEmailAddress()
                + "\nCustomer physical_address : " + getPhysicalAddress() ;
        
        return customer;
    }
    
    /**
     * getSurname
     * @return String surname
     */ 
    public String getSurname()
    {
        return this.surname;
    }

    /**
     * getName
     * @return name customer name
     */ 
	public String getName() {
		return name;
	}

    /**
     * setCustomerName
     * @param String name
     */ 
	public void setName(String name) {
		this.name = name;
	}

    /**
     * getSurname
     * @return customer surname
     */ 
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

    /**
     * setTelephoneNumber
     * @param String telephoneNumber
     */ 
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

    /**
     * getEmailAddress
     * @return customer emailAddress
     */ 
	public String getEmailAddress() {
		return emailAddress;
	}

    /**
     * setEmailAddress
     * @param String emailAddress
     */ 
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

    /**
     * getPhysicalAddress
     * @return customer physicalAddress
     */ 
	public String getPhysicalAddress() {
		return physicalAddress;
	}

    /**
     * setPhysicalAddress
     * @param String physicalAddress
     */ 
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}   
}
