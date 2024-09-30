package mypackage;
/**
 * @author Renil Khristi, Christian Roa
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;
    
    Profile(){
    	fname = "";
    	lname = "";
    	dob = null;
    }
    
    Profile(String fname, String lname, Date dob){
    	this.fname = fname;
    	this.lname = lname;
    	this.dob = dob;
    }
    
    public String getFname() {
    	return fname;
    }
    
    public String getLname() {
    	return lname;
    }
    
    public Date dob() {
    	return dob;
    }

    public void setFname(String fname) {
    	this.fname = fname;
    }
    
    public void setLname(String lname) {
    	this.lname = lname;
    }
    
    public void setDob(Date dob) {
    	this.dob = dob;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Profile) {
    		Profile other = (Profile) obj;
    		return this.fname.equals(other.fname) && this.lname.equals(other.lname) && this.dob.equals(other.dob);

    	}
    	return false;
    }
    
    @Override
    public String toString() {
    	return this.fname + " " + this.lname + " " + this.dob.toString();
    }
    
	@Override
	public int compareTo(Profile obj) {
		int fnameCompare = this.fname.compareTo(obj.fname);
		if (fnameCompare != 0) {
            return fnameCompare;
        }

		int lnameCompare = this.lname.compareTo(obj.lname);
		if (lnameCompare != 0) {
			return lnameCompare;
		}

        int dobCompare = this.dob.compareTo(obj.dob);
        //if (dobCompare != 0) {
            return dobCompare;
        
	}
	
	 public static void main(String[] args) {
		 Date testDate = new Date(2024, 6, 21);
		 Date testDate2 = new Date(2023, 5, 22);
	     Profile testProf = new Profile("John", "Parker", testDate);
	     Profile testProf2 = new Profile("Sally", "Linden", testDate2);

	     
	    }
    
}
