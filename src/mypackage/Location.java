package mypackage;
/**
 * @author Renil Khristi, Christian Roa
 */
public enum Location {
    BRIDGEWATER("Somerset County", "08807"), 
    EDISON("Middlesex County", "08817"), 
    PISCATAWAY("Middlesex County", "08854"), 
    PRINCETON("Mercer County", "08542"),
    MORRISTOWN("Morris County", "07960"),
    CLARK("Union County", "07066");

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
