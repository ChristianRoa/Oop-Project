package mypackage;

import utilities.Date;

/**
Creates a class for profiles that are composed of the date along with the first and last name of the patient
 * @author Renil Khristi, Christian Roa
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     Constructs a Profile object with the specified first name, last name, and date.
     @param fname the first name of the patient
     @param lname the last name of the patient
     @param dob the date of birth
     */
    Profile(String fname, String lname, Date dob){
    	this.fname = fname;
    	this.lname = lname;
    	this.dob = dob;
    }

    /*
    Gets the first name of the patient
    @return the first name 
     */
    public String getFname() {
    	return fname;
    }

    /*
    Gets the last name of the patient
    @return the last name 
     */
    public String getLname() {
    	return lname;
    }
	
    /*
    Gets the date of birth
    @return the date 
     */
    public Date dob() {
    	return dob;
    }
	
    /*
    Sets the first name of the patient
    @param the patient's first name
     */
    public void setFname(String fname) {
    	this.fname = fname;
    }
	
    /*
    Sets the last name of the patient
    @param the patient's last name
     */
    public void setLname(String lname) {
    	this.lname = lname;
    }
	
    /*
    Sets the date of birth to the desires date of birth
    @param the date of birth
     */
    public void setDob(Date dob) {
    	this.dob = dob;
    }

    /*
    Determines if the first name, last name, and date are the same 
    @param an object that should represent a patient
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Profile) {
    		Profile other = (Profile) obj;
    		return this.fname.equals(other.fname) && this.lname.equals(other.lname) && this.dob.equals(other.dob);

    	}
    	return false;
    }
    
    /*
    Displays the instance variables 
    @return the first name, last name, and date
     */
    @Override
    public String toString() {
    	return this.fname + " " + this.lname + " " + this.dob.toString();
    }
    
	@Override
	public int compareTo(Profile obj) {
		int fnameCompare = this.fname.compareTo(obj.fname);
		if (fnameCompare > 0) {
            return 1;
        }
		else if (fnameCompare < 0) {
            return -1;
        }
		else if (fnameCompare == 0) {
            return 0;
        }

		int lnameCompare = this.lname.compareTo(obj.lname);
		if (lnameCompare > 0) {
			return 1;
		}
		else if (lnameCompare < 0) {
			return -1;
		}
		else if (lnameCompare != 0) {
			return 0;
		}

        int dobCompare = this.dob.compareTo(obj.dob);
            return dobCompare;
        
	}
	/*
 	The main method that serves as a testbed for the compareTo method.
	 */
	 public static void main(String[] args) {
		 
		 Date date1 = new Date(2024,2,20);
		 Date date2 = new Date(2023,1,19);
		 Date date3 = new Date(2022,3, 21);
		 Profile profile1 = new Profile("John", "Smith", date1); 
		 Profile profile2 = new Profile("Jane", "Doe", date2);
		 Profile profile3 = new Profile("John", "Smith", date1);
		 Profile profile4 = new Profile("Lol", "Smith", date3);
		 
		 System.out.println(profile1.compareTo(profile2));
		 System.out.println(profile1.compareTo(profile3));
		 System.out.println(profile2.compareTo(profile3));
		 System.out.println(profile1.compareTo(profile4));
		 System.out.println(profile2.compareTo(profile4));
		 System.out.println(profile4.compareTo(profile3));
		 System.out.println(profile4.compareTo(profile2));
	     
	    }
    
}
