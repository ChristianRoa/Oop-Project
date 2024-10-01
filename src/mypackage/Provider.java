package mypackage;
/**
Enum class of the providers and their location & specialty
 * @author Renil Khristi, Christian Roa
 */
public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);
	
    private final Location location;
    private final Specialty specialty;

    /*
    Constructor based on location and specialty
    @param location
    @param specialty
     */
    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }
	
    /*
    Gets the location of the provider
    @return location
     */
    public Location getLocation() {
    	return location;
    }

    /*
    Gets specialty of the provider
    @return specialty
     */
    public Specialty getSpecialty() {
    	return specialty;
    }

}
