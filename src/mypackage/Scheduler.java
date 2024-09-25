package mypackage;

import java.util.Scanner;
/**
 * @author Christian Roa
 */
public class Scheduler {
    private Scanner scanner; // reads user input

    public Scheduler() {
        scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Scheduler is running.");
        String command;

        while(true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if(command.isEmpty()) {
                continue;
            }

            if(command.equals("Q")) {
                System.out.println("Scheduler terminated");
                break;
            }
        }
        scanner.close();
    }
}
