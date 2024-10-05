package mypackage;
/**
 * @author Christian Roa, Renil Khristi
 */
public class Timeslot implements Comparable<Timeslot> {
    private final int hour;
    private final int minute;

    // Valid timeslots
    private static final int[][] validTimeslots = {
            {9, 0}, {9, 30}, {10, 0}, {10, 30}, {11, 0}, {11, 30},
            {14, 0}, {14, 30}, {15, 0}, {15, 30}, {16, 0}, {16, 30}
    };

    private Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    public boolean validTimeslot(int hour, int minute){
        for(int[] timeslot: validTimeslots){
            if(hour == timeslot[0] && minute == timeslot[1]){
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Timeslot other) {
        if (this.hour != other.hour) {
            return this.hour - other.hour;
        } else {
            return this.minute - other.minute;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
