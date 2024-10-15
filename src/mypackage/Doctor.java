package mypackage;

public class Doctor extends Provider {
    private Specialty specialty;
    private String npi; //National Provider Identification unique to the doctor

    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;

    }

    @Override
    public int rate(){
        return this.specialty.getCharge();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getNpi() {
        return npi;
    }
}
