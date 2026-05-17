package dispatcher;

import business.Mountains;
import business.Students;
import java.util.List;
import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class Main {

    private static final String MOUNTAIN_FILE = "data/MountainList.csv";
    private static final String REGISTRATION_FILE = "registrations.dat";

    private final Inputter inputter;
    private final Mountains mountains;
    private final Students students;

    public Main() {
        inputter = new Inputter();
        mountains = new Mountains(MOUNTAIN_FILE);
        students = new Students(REGISTRATION_FILE);
        mountains.readFromFile();
        students.readFromFile();
    }

    private void showMenu() {
        System.out.println();
        System.out.println("========== MOUNTAIN HIKING CHALLENGE REGISTRATION ==========");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search Participants by Name");
        System.out.println("6. Filter Data by Campus");
        System.out.println("7. Statistics of Registration Numbers by Location");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit the Program");
        System.out.println("============================================================");
    }

    public void run() {
        String choice;
        do {
            showMenu();
            choice = inputter.getString("Enter your choice [1-9]: ");
            switch (choice) {
                case "1":
                    students.addNewRegistration(inputter, mountains);
                    break;
                case "2":
                    students.update(inputter, mountains);
                    break;
                case "3":
                    students.showAll();
                    break;
                case "4": {
                    String id = inputter.inputAndLoop("Enter Student ID: ", Acceptable.STUDENT_ID);
                    students.delete(id, inputter, mountains);
                    break;
                }
                case "5": {
                    String name = inputter.getString("Enter name or partial name: ");
                    List<Student> found = students.searchByName(name);
                    if (found.isEmpty()) {
                        System.out.println("No one matches the search criteria!");
                    } else {
                        students.showAll(found, "Matching Students:", "No one matches the search criteria!");
                    }
                    break;
                }
                case "6": {
                    String campus = inputter.inputAndLoop("Enter campus code [HE-CE-DE-SE-QE]: ",
                            Acceptable.CAMPUS_CODE_VALID);
                    List<Student> filtered = students.filterByCampusCode(campus);
                    students.showCampusList(filtered, campus);
                    break;
                }
                case "7":
                    students.statisticalizeByMountainPeak();
                    break;
                case "8":
                    students.saveToFile();
                    break;
                case "9":
                    if (!handleExit()) {
                        choice = "0";
                    }
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        } while (!choice.equals("9"));
    }

    private boolean handleExit() {
        if (!students.isSaved()) {
            String confirm = inputter.inputYesNo(
                    "Do you want to save the changes before exiting? (Y/N): ");
            if (confirm.equals("Y")) {
                students.saveToFile();
                return true;
            }
            String forceExit = inputter.inputYesNo(
                    "You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
            return forceExit.equals("Y");
        }
        System.out.println("Goodbye!");
        return true;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
