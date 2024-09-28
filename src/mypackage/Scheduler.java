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

    private void processCommand(String commandLine) {
        String[] commands = commandLine.split(",");
        String command = commands[0];

        if (command.equals("S")) {
        }
        else if (command.equals("C")) {}
        else if (command.equals("R")) {}
        else if (command.equals("PA")) {}
        else if (command.equals("PP")) {}
        else if (command.equals("PL")) {}
        else if (command.equals("PS")) {}
    }
}
