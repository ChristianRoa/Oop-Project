package mypackage;
/**
 * @author Renil Khristi
 */
public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    Patient(){
    	this.profile = null;
    	this.visits = null;
    }
    Patient(Profile profile, Visit visits) {
    	this.profile = profile;
    	this.visits = visits;
    }
    
    public Profile getProfile() {
    	return profile;
    }
    
    public Visit getVisits() {
    	return visits;
    }
    
    public void setProfile(Profile profile) {
    	this.profile = profile;
    }
    
    public void setVisits(Visit visits) {
    	this.visits = visits;
    }
    
    public int charge() {
    	int charge = 0;;
    	while(visits.getNext()!=null) {
    		charge += visits.getAppointment().getProvider().getSpecialty().getCharge();
    		visits = visits.getNext();
    	}
    	return charge;
    }
    
    @Override
    public String toString() {
    	return this.profile.toString() + " " + visits.toString();
    }
    
    @Override
    public int compareTo(Patient obj) {
    return this.profile.compareTo(obj.profile);
    }
  
}
