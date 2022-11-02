/*
 *Project Contractor Class
 * @author Tebogo Rammutla
 */

/**
  * Contractor Class
  */
public class Contractor 
{
    // Properties of Contractor class
    private String name;
    private String surname;
    private String telephoneNumber;
    private String emailAddress;
    private String physicalAddress;
    
    /**
     * Constructor of Contractor Class
     * @param name the contractor name to set
     * @param surname the contractor surname to set
     * @param telephoneNumber the contractor telephoneNumber to set
     * @param emailAddress the contractor email address to set
     * @param physicalAddress the contractor physical address to set
     */
    public Contractor(String name,String surname,String telephoneNumber,String emailAddress,String physicalAddress )
    {
        this.setName(name);
        this.setSurname(surname);
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.setPhysicalAddress(physicalAddress);   
    }
    
     /**
     * @return the string emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param string the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return string the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param string the telephoneNumber to set
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
    
    /**
     * toString
     * @return customer string object
     */ 
        public String toString()
    {
        String customer;
                customer = "\nContractor name : " + getName()
                + "\nContractor surname : " + getSurname()
                + "\nContractor telephone number : " + getTelephoneNumber()
                + "\nContractor email_address : " + getEmailAddress()
                + "\nContractor physical_address : " + getPhysicalAddress() + "\n";
        
        return customer;
    }

    /**
    * getContractorName
    * @return String name
    */
	public String getName() {
		return name;
	}

    /**
     * setContractorName
     * @param String name
     */ 
	public void setName(String name) {
		this.name = name;
	}

    /**
     * getContractorSurname
     * @return String surname
     */ 
	public String getSurname() {
		return surname;
	}

    /**
     * setContractorSurname
     * @param String surname
     */ 
	public void setSurname(String surname) {
		this.surname = surname;
	}

    /**
     * getContractorPhysicalAddress
     * @return String physicalAddress
     */
	public String getPhysicalAddress() {
		return physicalAddress;
	}

    /**
     * setphysicalAddress
     * @param String physicalAddress
     */ 
	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
        
    
}
