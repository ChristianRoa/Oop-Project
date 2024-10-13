package utilities;
import mypackage.Appointment;
import mypackage.Provider;
import mypackage.Imaging;
public class Sort {

    // Bubble sort appointments by date, time, then provider's name
    // Bubble sort appointments by date, time, then provider's name
    private static void sortByApp(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                // Compare by date
                int dateCompare = a1.getDate().compareTo(a2.getDate());
                if (dateCompare > 0 ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0) ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getLname().compareTo(a2.getProviderAsProvider().getProfile().getLname()) > 0) ||
                        (dateCompare == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getLname().compareTo(a2.getProviderAsProvider().getProfile().getLname()) == 0 &&
                                a1.getProviderAsProvider().getProfile().getFname().compareTo(a2.getProviderAsProvider().getProfile().getFname()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }


    // Bubble sort appointments by patient details
    private static void sortByPatient(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                // Compare patients by last name, first name, then date of birth
                int lastNameCompare = a1.getPatient().getProfile().getLname().compareTo(a2.getPatient().getProfile().getLname());
                if (lastNameCompare > 0 ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) == 0 &&
                                a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (lastNameCompare == 0 && a1.getPatient().getProfile().getFname().compareTo(a2.getPatient().getProfile().getFname()) == 0 &&
                                a1.getPatient().getProfile().dob().compareTo(a2.getPatient().getProfile().dob()) == 0 &&
                                a1.getDate().compareTo(a2.getDate()) == 0 &&
                                a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }


    private static void sortByLocation(List<Appointment> list){
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                // Compare providers by county
                int countyCompare = a1.getProviderAsProvider().getLocation().getCounty().compareTo(a2.getProviderAsProvider().getLocation().getCounty());
                if (countyCompare > 0 ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    // Swap if county comparison or other criteria indicate a1 > a2
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortBill(List<Appointment> list) {

    }

    private static void sortOfficeByLocation(List<Appointment> list) {
        // Create a temporary list to hold Imaging appointments
        List<Appointment> officeList = new List<>();

        // Filter for Imaging appointments
        for (Appointment appointment : list) {
            if (!(appointment instanceof Imaging)) {
                officeList.add(appointment);
            }
        }
        int n = officeList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = officeList.get(j);
                Appointment a2 = officeList.get(j + 1);

                // Compare by provider's county, date, then timeslot
                int countyCompare = a1.getProviderAsProvider().getLocation().getCounty().compareTo(a2.getProviderAsProvider().getLocation().getCounty());
                if (countyCompare > 0 ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Appointment temp = officeList.get(j);
                    officeList.set(j, officeList.get(j + 1));
                    officeList.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortImagingByLocation(List<Appointment> list) {
        // Create a temporary list to hold Imaging appointments
        List<Imaging> imagingList = new List<>();

        // Filter for Imaging appointments
        for (Appointment appointment : list) {
            if (appointment instanceof Imaging) {
                imagingList.add((Imaging) appointment);
            }
        }
        int n = imagingList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Imaging a1 = imagingList.get(j);
                Imaging a2 = imagingList.get(j + 1);

                // Compare by provider's county, date, then timeslot
                int countyCompare = a1.getProviderAsProvider().getLocation().getCounty().compareTo(a2.getProviderAsProvider().getLocation().getCounty());
                if (countyCompare > 0 ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    // Swap if any comparison indicates a1 > a2
                    Imaging temp = imagingList.get(j);
                    imagingList.set(j, imagingList.get(j + 1));
                    imagingList.set(j + 1, temp);
                }
            }
        }
    }

    public static void appointment(List<Appointment> list, char key){
        switch(key){
            case 'A':
                sortByApp(list);
                break;
            case 'P':
                sortByPatient(list);
                break;
            case 'L':
                sortByLocation(list);
                break;
            case 'S':
                sortBill(list);
                break;
            case 'O':
                sortOfficeByLocation(list);
                break;
            case 'I':
                sortImagingByLocation(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }


    public static void provider(List<Provider> list){

    }
}
