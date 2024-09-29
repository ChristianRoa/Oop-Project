package mypackage;

import java.util.Calendar;

/**
 * @author Christian Roa
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * A parameterized constructor that takes a string in a form of "mm/dd/yyyy"
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Getters
     */
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /**
     * Setters
     */
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * This method compares 2 dates
     */
    @Override
    public int compareTo(Date date) {
        if (this.year != date.year) {
            return this.year - date.year;
        } else if (this.month != date.month) {
            return this.month - date.month;
        } else {
            return this.day - date.day;
        }
    }

    /**
     * Check if two Date objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;
            return date.year == year && date.month == month && date.day == day;
        }
        return false;
    }

    /**
     * Converts the date object to a string in the format mm/dd/yyyy
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Return true if the date is a weekday
     */
    public boolean isWeekday() {
        Calendar cal = Calendar.getInstance();
        cal.set(this.year, this.month - 1, this.day);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY;
    }

    /**
     * Return today's date
     */
    public static Date today() {
        Calendar rightNow = Calendar.getInstance();
        return new Date(rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH) + 1, rightNow.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Helper method that Checks if the date is before today
     */
    private boolean isBeforeToday() {
        Calendar rightNow = Calendar.getInstance();
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(this.year, this.month - 1, this.day);
        return thisDate.before(rightNow);
    }

    /**
     * Helper method that Checks if the date is within six months from today
     */
    private boolean isWithinSixMonths(Date date) {
        Calendar rightNow = Calendar.getInstance();
        Calendar sixMonthsFromNow = Calendar.getInstance();
        sixMonthsFromNow.add(Calendar.MONTH, 6);
        Calendar thisDate = Calendar.getInstance();
        thisDate.set(date.year, date.month - 1, date.day);
        return thisDate.after(rightNow) && !thisDate.after(sixMonthsFromNow);
    }

    /**
     * Check if the date is valid
     */
    public boolean isValidDate() {
        Calendar dateToValidate = Calendar.getInstance();
        dateToValidate.setLenient(false);

        try {
            dateToValidate.set(this.year, this.month - 1, this.day);
            dateToValidate.getTime();
        } catch (Exception e) {
            return false;
        }
        if (this.isBeforeToday()) {
            return false;
        }
        if (!this.isWeekday()) {
            return false;
        }
        if (!this.isWithinSixMonths(this)) {
            return false;
        }
        return true;
    }


    /** testbed for the class */
    public static void main(String[] args) {
        testValid1(); //testing tomm
        testValid2(); // testing valid date within 6 months
        testValid3(); // testing leap year date in non leap year
        testValid4(); // testing before date
        testValid5(); // testing after date
        testValid6(); // testing within 6 months but on a weekend
    }
    private static void testValid1() {
        Date date = new Date(2024, 9, 30);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
    private static void testValid2() {
        Date date = new Date(2025, 2, 28);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
    private static void testValid3() {
        Date date = new Date(2025, 2, 29);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
    private static void testValid4() {
        Date date = new Date(2024, 2, 29);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
    private static void testValid5() {
        Date date = new Date(2028, 5, 30);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
    private static void testValid6() {
        Date date = new Date(2024, 10, 5);
        System.out.println(date.toString() + " " + date.isValidDate());
    }
}
