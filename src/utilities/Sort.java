package utilities;
import mypackage.Appointment;
import mypackage.Provider;
import mypackage.Imaging;
import mypackage.Technician;

public class Sort {
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

                int countyCompare = a1.getProviderAsProvider().getLocation().getCounty().compareTo(a2.getProviderAsProvider().getLocation().getCounty());
                if (countyCompare > 0 ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) > 0) ||
                        (countyCompare == 0 && a1.getDate().compareTo(a2.getDate()) == 0 && a1.getTimeslot().compareTo(a2.getTimeslot()) > 0)) {
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private static void sortBill(List<Appointment> list) {

    }

    private static void sortProviderProfile(List<Appointment> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Appointment a1 = list.get(j);
                Appointment a2 = list.get(j + 1);

                if(a1.getProvider().getProfile().compareTo(a2.getProvider().getProfile()) > 0){
                    Appointment temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
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
            case 'L', 'O', 'I':
                sortByLocation(list);
                break;
            case 'S':
                sortBill(list);
                break;
            case 'C':
                sortProviderProfile(list);
                break;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }
    private static void sortProviders(List<Provider> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Provider p1 = list.get(j);
                Provider p2 = list.get(j + 1);

                // Compare last names
                int lastNameComparison = p1.getProfile().getLname().compareTo(p2.getProfile().getLname());
                if (lastNameComparison > 0) {
                    // Swap if p1's last name is greater than p2's last name
                    Provider temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                } else if (lastNameComparison == 0) {
                    // If last names are equal, compare locations
                    if (p1.getLocation().compareTo(p2.getLocation()) > 0) {
                        // Swap if p1's location is greater than p2's location
                        Provider temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    public static void provider(List<Provider> list) {
    sortProviders(list);
    }
}
