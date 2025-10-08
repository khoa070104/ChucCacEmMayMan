package view;

import controller.DoctorHash;
import controller.Inputter;
import model.Doctor;
import common.Constants;
import common.Messages;
import java.util.HashMap;

public class MainView {
    private DoctorHash doctorHash;

    public MainView() {
        this.doctorHash = new DoctorHash();
    }

    public void displayMenu() {
        System.out.println("\n========= Doctor Management ==========");
        System.out.println("1. Add Doctor");
        System.out.println("2. Update Doctor");
        System.out.println("3. Delete Doctor");
        System.out.println("4. Search Doctor");
        System.out.println("5. Exit");
        System.out.println("=====================================");
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    updateDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    searchDoctor();
                    break;
                case 5:
                    System.out.println(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private int getMenuChoice() {
        int choice;
        while (true) {
            try {
                choice = Inputter.inputInt("> Choose: ");
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println(Messages.ERR_INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private void addDoctor() {
        System.out.println("\n--------- Add Doctor ----------");
        
        String code = Inputter.inputRequired("Enter Code: ");
        String name = Inputter.inputRequired("Enter Name: ");
        String specialization = Inputter.inputRequired("Enter Specialization: ");
        int availability = Inputter.inputInt("Enter Availability: ");
        
        Doctor newDoctor = new Doctor(code, name, specialization, availability);
        
        try {
            doctorHash.addDoctor(newDoctor);
            System.out.println(Messages.MSG_DOCTOR_ADDED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateDoctor() {
        System.out.println("\n--------- Update Doctor -------");
        
        String code = Inputter.inputRequired("Enter Code: ");
        
        // Check if doctor exists
        Doctor existingDoctor = doctorHash.getDoctorByCode(code);
        if (existingDoctor == null) {
            System.out.println(Messages.ERR_DOCTOR_CODE_NOT_EXIST);
            return;
        }
        
        // Show current information
        System.out.println("Current information:");
        System.out.println("Name: " + existingDoctor.getName());
        System.out.println("Specialization: " + existingDoctor.getSpecialization());
        System.out.println("Availability: " + existingDoctor.getAvailability());
        
        // Get new information (optional fields)
        String newName = Inputter.inputOptional("Enter Name (leave empty to keep current): ");
        String newSpecialization = Inputter.inputOptional("Enter Specialization (leave empty to keep current): ");
        int newAvailability = Inputter.inputIntOptional("Enter Availability (leave empty to keep current): ");
        
        // Create updated doctor
        Doctor updatedDoctor = new Doctor(
            code,
            newName.isEmpty() ? existingDoctor.getName() : newName,
            newSpecialization.isEmpty() ? existingDoctor.getSpecialization() : newSpecialization,
            newAvailability == -1 ? existingDoctor.getAvailability() : newAvailability
        );
        
        try {
            doctorHash.updateDoctor(updatedDoctor);
            System.out.println(Messages.MSG_DOCTOR_UPDATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteDoctor() {
        System.out.println("\n--------- Delete Doctor -------");
        
        String code = Inputter.inputRequired("Enter Code: ");
        
        Doctor doctorToDelete = new Doctor(code, "", "", 0);
        
        try {
            doctorHash.deleteDoctor(doctorToDelete);
            System.out.println(Messages.MSG_DOCTOR_DELETED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchDoctor() {
        System.out.println("\n--------- Search Doctor -------");
        
        String searchText = Inputter.inputRequired("Enter text: ");
        
        try {
            HashMap<String, Doctor> results = doctorHash.searchDoctor(searchText);
            
            if (results.isEmpty()) {
                System.out.println(Messages.ERR_NO_DOCTORS_FOUND);
            } else {
                System.out.println("--------- Result ------------");
                System.out.println(Constants.TABLE_HEADER);
                for (Doctor doctor : results.values()) {
                    System.out.println(doctor.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
