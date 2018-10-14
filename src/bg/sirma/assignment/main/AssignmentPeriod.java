package bg.sirma.assignment.main;

import java.util.Scanner;

public class AssignmentPeriod {

    public static void main(String[] args) {
        System.out.println("To run console program Press enter.");
        System.out.println("To run graphically the program enter the path to the file!");
        Scanner scan = new Scanner(System.in);

        String filePath  = scan.nextLine();
        ProgramLauncher launcher = new ProgramLauncher(filePath.trim());
        launcher.launchProgramMode();
    }
}
