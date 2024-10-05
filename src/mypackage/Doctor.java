package mypackage;

public class Doctor extends Provider {
    private Specialty specialty;
    private String npi; //National Provider Identification unique to the doctor

    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;

    }

    public void rate(){

    }


}
