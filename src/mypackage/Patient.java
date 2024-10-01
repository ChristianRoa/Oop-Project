package mypackage;
/**
Creates and defines patients based on their profile and visits
 * @author Renil Khristi, Christian Roa
 */
public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)
    /*
    Creates the patient constructor 
    @param profile of patient
    @param visits of patient
     */
    Patient(Profile profile, Visit visits) {
    	this.profile = profile;
    	this.visits = visits;
    }

    /*
    Gets the profile
    @return profile
     */
    public Profile getProfile() {
    	return profile;
    }

    /*
    Gets the visits
    @return visits
     */
    public Visit getVisits() {
    	return visits;
    }

    /*
    Sets the profile to the desired profile
     */
    public void setProfile(Profile profile) {
    	this.profile = profile;
    }
    
    /*
    Sets the visits to the desired visits
     */
    public void setVisits(Visit visits) {
    	this.visits = visits;
    }

    /*
    Find the cumulative charge a patient will have after all their visits
    @return charge 
     */
    public int charge() {
    	int charge = 0;;
    	while(visits.getNext()!=null) {
    		charge += visits.getAppointment().getProvider().getSpecialty().getCharge();
    		visits = visits.getNext();
    	}
    	return charge;
    }

    /*
    Displays information of the profile and visits
    @return the string of everything in profile and visits
     */
    @Override
    public String toString() {
    	return this.profile.toString() + " " + visits.toString();
    }

    /*
    Compares the profile to the given patient's profile
    @return profile comparison
     */
    @Override
    public int compareTo(Patient obj) {
    return this.profile.compareTo(obj.profile);
    }
  
}
