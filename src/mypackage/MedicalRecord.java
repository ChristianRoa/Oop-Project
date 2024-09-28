package mypackage;
/**
 * @author Renil Khristi
 */
public class MedicalRecord {
	
    private Patient[] patients;
    private int size;
    
    public MedicalRecord() {
    	this.patients = new Patient[0];
    	this.size = 0;    	
    }
    
    public MedicalRecord(int size) {
    	this.patients = new Patient[size];
    	this.size = size;
    }
    
    public boolean add(Patient patient) {
    	if(size < patients.length) {
    		patients[size] = patient;
    		size++;
    		return true;
    	}
    	return false;
    }
    
    public int getSize() {
    	return size;
    }
    
    public Patient getPatient(int i) {
    	if(i >= 0 && i < size) { 
    		return patients[i];
    	}
    	return null;
    }
}
