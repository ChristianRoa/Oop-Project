package mypackage;
/**
Enum class that represents the location and specifically the county and zip of the provider
 * @author Renil Khristi, Christian Roa
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    /*
    Creates the constuctor 
    @param county 
    @param zip
     */
    private Location(String county, String zip){
        this.county = county;
        this.zip = zip;
    }

    /*
    Gets the county 
    @return the county
     */
    public String getCounty() {
    	return county;
    }

    /*
    Gets the zip
    @return the zip
     */
    public String getZip() {
    	return zip;
    }    
}
