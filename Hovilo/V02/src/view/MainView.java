package view;
import controller.DoctorHash;
import controller.Inputter;
import java.util.Map;
import model.Doctor;
/** Displays and controls the doctor management menu. @author Ho Vi Lo @since 09/09/2026 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final DoctorHash manager = new DoctorHash();
    /** Runs menu choices until Exit is selected. */
    public void run() {
        while (true) {
            System.out.println("1. Add doctor\n2. Update doctor\n3. Delete doctor\n4. Search doctor\n5. Exit");
            int choice = inputter.readChoice();
            try {
                if (choice == 1) add(); else if (choice == 2) update(); else if (choice == 3) delete();
                else if (choice == 4) search(); else if (choice == 5) return; else System.out.println("Please choose from 1 to 5.");
            } catch (Exception exception) { System.out.println(exception.getMessage()); }
        }
    }
    /** @throws Exception when manager rejects the new doctor */
    private void add() throws Exception { manager.addDoctor(new Doctor(inputter.readRequired("Code: "), inputter.readRequired("Name: "), inputter.readRequired("Specialization: "), inputter.readAvailability("Availability: ", false))); System.out.println("Doctor added successfully."); }
    /** @throws Exception when doctor does not exist or data is invalid */
    private void update() throws Exception { String code = inputter.readRequired("Code: "); String name = inputter.readLine("New name (blank to keep): "); String specialty = inputter.readLine("New specialization (blank to keep): "); int availability = inputter.readAvailability("New availability (blank to keep): ", true); manager.updateDoctor(new Doctor(code, name, specialty, availability)); System.out.println("Doctor updated successfully."); }
    /** @throws Exception when doctor cannot be deleted */
    private void delete() throws Exception { String code = inputter.readRequired("Code: "); manager.deleteDoctor(new Doctor(code, "", "", 0)); System.out.println("Doctor deleted successfully."); }
    /** @throws Exception when database does not exist */
    private void search() throws Exception { Map<String, Doctor> result = manager.searchDoctor(inputter.readLine("Search text: ")); System.out.printf("%-10s %-20s %-20s %s%n", "Code", "Name", "Specialization", "Availability"); for (Doctor doctor : result.values()) System.out.printf("%-10s %-20s %-20s %d%n", doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability()); }
}
