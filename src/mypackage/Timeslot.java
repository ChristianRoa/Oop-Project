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

    public static Timeslot getTimeSlot(String indexStr) {
        int index;
        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            return null;
        }
        if (index < 1 || index > validTimeslots.length) {
            return null;
        }
        int[] timeslot = validTimeslots[index - 1];
        return new Timeslot(timeslot[0], timeslot[1]);
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Timeslot other = (Timeslot) obj;
        return this.hour == other.hour && this.minute == other.minute;
    }

}
