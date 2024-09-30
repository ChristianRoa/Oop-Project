package mypackage;
/**
 * @author Renil Khristi, Christian Roa
 */
public enum Specialty {
    FAMILY(250), PEDIATRICIAN(300), ALLERGIST(350);
    private final int charge;

    Specialty(int charge) {
        this.charge = charge;
    }
    
    public int getCharge() {
    	return charge;
    }
    
}
