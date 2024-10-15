package mypackage;
import java.util.Scanner;
/**
Enum class of the providers and their location & specialty
 * @author Renil Khristi, Christian Roa
 */
public abstract class Provider extends Person {
    private Location location;

    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract int rate();
    public int getRate() {
        return rate();  // Calls the overridden method in the subclass
    }
    @Override
    public String toString() {
        return super.toString() + ", " + location + ", " + location.getCounty() + " " + location.getZip();
    }
}
