package mypackage;
import java.util.Calendar;
/**
 * @author Christian Roa
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    /** A parameterized constructor that takes a string in a form of "mm/dd/yyyy" */
    public Date(int year, int month, int day) {
       this.year = year;
       this.month = month;
       this.day = day;
    }

    /** Getters */
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /** Setters */
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /** This method compares 2 dates */
    @Override
    public int compareTo(Date date) {
        if(this.year != date.year){
            return this.year - date.year;
        }
        else if(this.month != date.month){
            return this.month - date.month;
        }
        else {
            return this.day - date.day;
        }
    }

    /** turns date object to string */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /** return truth value if the objects date is a weekday */
    public boolean isWeekday(){
        Calendar cal = Calendar.getInstance();
        cal.set(this.year, this.month-1, this.day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }

    /** return today's date */
    public static Date today() {
        Calendar rightNow = Calendar.getInstance();
        return new Date(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH), rightNow.get(Calendar.DAY_OF_MONTH));
    }

    /** checks if date of object is before today or not */
    public boolean isBeforeToday(){
        Calendar rightNow = Calendar.getInstance();
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month-1, this.day);
        return thisDate.before(rightNow);
    }

    /** checks if date of object is between now and six months from now */
    public boolean isWithinSixMonths(Date date){
        Calendar rightNow = Calendar.getInstance();
        Calendar sixMonthsFromNow = Calendar.getInstance();
        sixMonthsFromNow.add(Calendar.MONTH, 6);
        Calendar thisDate = Calendar.getInstance();
        return !thisDate.after(sixMonthsFromNow) && thisDate.after(rightNow);
    }

    /** checks if date of object is an acceptable/valid date */
    public boolean isValidDate(){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setLenient(false);
        rightNow.set(this.year, this.month, this.day);
        int validYear = rightNow.get(Calendar.YEAR);
        int validMonth = rightNow.get(Calendar.MONTH);
        int validDay = rightNow.get(Calendar.DAY_OF_MONTH);

        return validYear == year && validMonth == month && validDay == day;
    }

    /** testbed for class */
    public static void main(String[] args) {
        Date test = new Date(2024, 6, 21);
        System.out.println(test.isValidDate());
        System.out.println(test.today());
    }

}

