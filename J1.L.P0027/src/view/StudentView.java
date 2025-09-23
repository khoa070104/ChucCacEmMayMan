package view;

import controller.Inputter;
import controller.StudentController;
import java.util.List;
import model.Student;

/**
 *
 * @author NCPC
 */
public class StudentView {
    
    private static void newRegister(StudentController controller) {
        controller.addNewRegistration();
    }
    
    
    
    public static void menu(){
        System.out.println("1. New Registration: Add a new student registration.\n" +
                            "2. Update Registration Information: Modify existing registration details.\n" +
                            "3. Display Registered List: Show the list of all registered students.\n" +
                            "4. Delete Registration Information: Remove a student's registration record.\n" +
                            "5. Search Participants by Name: Find registered students based on their names.\n" +
                            "6. Filter Data by Campus: Display registrations specific to a campus.\n" +
                            "7. Statistics of Registration Numbers by Location: Generate statistics on the number of registrations\n" +
                            "for each location.\n" +
                            "8. Save Data to File: Store registration data in a file.\n" +
                            "9. Exit the Program: End the program execution\n");
    }
    
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        String choice;
        
        do{
            boolean flag = false;
            menu();
            choice = Inputter.input("Enter your choice: ");
            switch (choice) {
                case "1":
                    newRegister(controller);
                    flag = false;
                    break;
                case "2":
                    updateRegister(controller);
                    flag = false;
                    break;
                case "3":
                    displayRegister(controller);
                    break;
                case "4":
                    deleteRegister(controller);
                    flag = false;
                    break;
                case "5":
                    searchByName(controller);
                    break;
                case "6":
                    filterRegister(controller);
                    break;
                case "7":
                    statistRegister(controller);
                    break;
                case "8":
                    flag = saveFile(controller);
                    break;
                case "9":
                    if(flag == false){
                        String confirm = Inputter.inputRequired("You have unsaved changes. Are you sure you want to exit without saving? (Y/N)", Inputter.YES_NO_VALIDATE);
                        if(confirm == "Y"){
                            saveFile(controller);
                        }
                    } else{
                        System.out.println("Good bye!");
                    }
                    break;
                default:
                    System.out.println("This function is not available.");
                    break;
            }
        
        
        } while(choice.equals("9") == false);
    }
    
    // 1 
    // 8 => flag = true
    // 1 => false

    private static void displayRegister(StudentController controller) {
        controller.printList(controller.getListStudent() , "No students have registered yet.");
    }

    private static void updateRegister(StudentController controller) {
        controller.updateRegistration();
    }

    private static void deleteRegister(StudentController controller) {
        controller.deleteRegister();
    }

    private static void filterRegister(StudentController controller) {
        List<Student> res = controller.filterByCampus();
        controller.printList(res, "This student has not registered yet.");
       
    }

    private static void statistRegister(StudentController controller) {
        controller.printStatist();

    }

    private static void searchByName(StudentController controller) {
        controller.printList(controller.searchByName(),"No one matches the search criteria!");
    }

    private static boolean saveFile(StudentController controller) {
        controller.saveToFile("student.dat");
        return true;
    }

}
