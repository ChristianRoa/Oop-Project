package mypackage;
/**
 * @author Christian Roa
 */
public class List {
    private Appointment[] appointments;
    private int size; // number of appointments in the array
    private int capacityIncrement = 4; // number capacity is incremented by
    private static int notFound = -1; // A constant used to define something that was not found

    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;
    }

    /** helper method that looks for specific appointment in the appointment array */
    private int find(Appointment appointment){
        for (int i = 0; i < this.size; i++) {
            if(this.appointments[i].equals(appointment)){
                return i;
            }
        }
        return notFound;
    }

    /** helper method to increase the capacity by 4 */
    private void grow(){
        Appointment[] newAppointments = new Appointment[this.appointments.length + capacityIncrement];
        for (int i = 0; i < this.size; i++) {
            newAppointments[i] = this.appointments[i];
        }
    }

    /** method to check if the appointment list contains a specific appointment*/
    public boolean contains(Appointment appointment){
        int appointmentCheck = find(appointment);
        return appointmentCheck != notFound;
    }

    /** checks if array is full and calls grow to increase capacity as needed. Then adds appointment to the end of the array*/
    public void add(Appointment appointment){
       if(this.appointments.length == this.size){
           this.grow();
       }
       this.appointments[this.size++] = appointment;
    }

    /** if appointment is found removes it from the list */
    public void remove(Appointment appointment){
        int index;
        if(find(appointment) != notFound){
            index = find(appointment);
        }
        else{
            return;
        }
        for (int i = index; i < this.size-1; i++) {
            appointments[i] = this.appointments[i+1];
        }
        this.appointments[this.size-1] = null;
    }

    /** helper bubblesort implemntation */
    public void bubbleSort(Appointment[] appointments){
        boolean swapped;
        int n = appointments.length;

        for (int i = 0; i < n-1; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                if(appointments[j].compareTo(appointments[j+1]) > 0){
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j+1];
                    appointments[j+1] = temp;
                    swapped = true;
                }
            }

            if(!swapped){
                break;
            }
        }
    }

    /** ordered by patient profile, date/timeslot */
    public void printByPatient(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /** ordered by county, date/timeslot */
    public void printByLocation(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /** ordered by date/timeslot, provider name */
    public void printByAppointment(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

}
package mypackage;
/**
 * @author Christian Roa
 */
public class List {
    private Appointment[] appointments;
    private int size; // number of appointments in the array
    private int capacityIncrement = 4; // number capacity is incremented by
    private static int notFound = -1; // A constant used to define something that was not found

    public List() {
        this.appointments = new Appointment[4];
        this.size = 0;
    }

    /** helper method that looks for specific appointment in the appointment array */
    private int find(Appointment appointment){
        for (int i = 0; i < this.size; i++) {
            if(this.appointments[i].equals(appointment)){
                return i;
            }
        }
        return notFound;
    }

    /** helper method to increase the capacity by 4 */
    private void grow(){
        Appointment[] newAppointments = new Appointment[this.appointments.length + capacityIncrement];
        for (int i = 0; i < this.size; i++) {
            newAppointments[i] = this.appointments[i];
        }
        this.appointments = newAppointments;
    }

    /** method to check if the appointment list contains a specific appointment*/
    public boolean contains(Appointment appointment){
        int appointmentCheck = find(appointment);
        return appointmentCheck != notFound;
    }

    /** checks if array is full and calls grow to increase capacity as needed. Then adds appointment to the end of the array*/
    public void add(Appointment appointment){
       if(this.appointments.length == this.size){
           this.grow();
       }
       this.appointments[this.size++] = appointment;
    }

    /** if appointment is found removes it from the list */
    public void remove(Appointment appointment){
        int index;
        if(find(appointment) != notFound){
            index = find(appointment);
        }
        else{
            return;
        }
        for (int i = index; i < this.size-1; i++) {
            appointments[i] = this.appointments[i+1];
        }
        this.appointments[this.size-1] = null;
    }

    /** helper bubblesort implemntation */
    public void bubbleSort(Appointment[] appointments){
        boolean swapped;
        int n = appointments.length;

        for (int i = 0; i < n-1; i++) {
            swapped = false;
            for (int j = 0; j < n-i-1; j++) {
                if(appointments[j].compareTo(appointments[j+1]) > 0){
                    Appointment temp = appointments[j];
                    appointments[j] = appointments[j+1];
                    appointments[j+1] = temp;
                    swapped = true;
                }
            }

            if(!swapped){
                break;
            }
        }
    }

    /** ordered by patient profile, date/timeslot */
    public void printByPatient(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /** ordered by county, date/timeslot */
    public void printByLocation(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

    /** ordered by date/timeslot, provider name */
    public void printByAppointment(){
        bubbleSort(this.appointments);
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.appointments[i].toString());
        }
    }

}
