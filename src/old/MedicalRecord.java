package old;

import mypackage.Patient;

/**
Creates a medical record comprising of a bag of patients 
 * @author Renil Khristi, Christian Roa
 */
public class MedicalRecord {
	
    private Patient[] patients;
    private int size;

    /*
    Constructor to create an instance of medical record 
    @param size 
     */
    public MedicalRecord(int size) {
    	this.patients = new Patient[size];
    	this.size = size;
    }

    /*
    Added patients if it is possible to the bag
    @return true if the patients passed in is less than the size false if not
     */
    public boolean add(Patient patient) {
    	if(size < patients.length) {
    		patients[size] = patient;
    		size++;
    		return true;
    	}
    	return false;
    }
    /*
    Gets the size
    @return the size
     */
    public int getSize() {
    	return size;
    }

    /*
    Gets the patient at a specific index
    @return patient at desired index and null if that's not possible
     */
    public Patient getPatient(int i) {
    	if(i >= 0 && i < size) { 
    		return patients[i];
    	}
    	return null;
    }
}
