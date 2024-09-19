package mypackage;
/**
 * @author Christian Roa
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    @Override
    public int compareTo(Date obj) {
        return obj.day - this.day;
    }

    private boolean isLeapYear(){
        if(this.year % QUADRENNIAL == 0){
            if(this.year % CENTENNIAL == 0){
                return this.year % QUATERCENTENNIAL == 0;
            }
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }

    private boolean is31DayMonth(){
        int january = 0;
        int march = 2;
        int may = 4;
        int july = 6;
        int august = 7;
        int october = 9;
        int december = 11;
        return this.month == january || this.month == march || this.month == may || this.month == july || this.month == august || this.month == october || this.month == december;
    }

    private boolean isValidMonth() {
        int numOfMonths = 11;
        return this.month >= 0 && this.month <= numOfMonths;
    }

    private boolean isValidDay(){
        int maxDays;
        int february = 1;
        if(this.month == february){
            if(this.isLeapYear()){
                maxDays = 29;
            }
            else{
                maxDays = 28;
            }
            return this.day >= 0 && this.day <= maxDays;
        }
        else if(this.is31DayMonth()){
            maxDays = 31;
            return this.day >= 0 && this.day <= maxDays;
        }
        else{
            maxDays = 30;
            return this.day >= 0 && this.day <= maxDays;
        }
    }

    public boolean isValid(){
        return isValidMonth() && isValidDay();
    }
}

