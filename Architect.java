/*
 *Project Architect Class
 * @author Tebogo Rammutla
 */

/**
  * Architect Class
  */
public class Architect 
{
    //Properties of Architect class
    private String name;
    private String surname;
    private String telephoneNumber;
    private String emailAddress;
    private String physicalAddress;
    
    /**
     * Constructor of Architect Class
     * @param name the architect name to set
     * @param surname the architect surname to set
     * @param telephoneNumber the architect telephoneNumber to set
     * @param emailAddress the architect email address to set
     * @param physicalAddress the architect physical address to set
     */
    public Architect(String name,String surname,String telephoneNumber,String emailAddress,String physicalAddress )
    {        
        this.setName(name);
        this.setSurname(surname);
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
        
        String customer = "\nArchitect name : " + getName()
                + "\nArchitect surname : " + getSurname()
                + "\nArchitect telephone number : " + getTelephoneNumber()
                + "\nArchitect email_address : "  + getEmailAddress()
                + "\nArchitect physical_address : " + getPhysicalAddress() + "\n";
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
     * @return name architect name
     */ 
	public String getName() {
		return name;
	}

    /**
     * setArchitectName
     * @param String name
     */ 
	public void setName(String name) {
		this.name = name;
	}

    /**
     * setArchitectSurname
     * @param String surname
     */ 
	public void setSurname(String surname) {
		this.surname = surname;
	}

    /**
     * getTelephoneNumber
     * @return String architect telephoneNumber
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
     * @return architect emailAddress
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
     * @return architect physicalAddress
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
