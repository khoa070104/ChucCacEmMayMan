package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Mountain;
import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class Students extends ArrayList<Student> {

    private static final String TABLE_HEADER = "----------------------------------------------------------------\n"
            + "Student ID | Name           | Phone         | Peak Code | Fee\n"
            + "----------------------------------------------------------------";
    private static final String TABLE_FOOTER = "----------------------------------------------------------------";

    private final String pathFile;
    private boolean saved;

    public Students(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Student searchById(String id) {
        for (Student s : this) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public boolean isDuplicateId(String id) {
        return searchById(id) != null;
    }

    public void addStudent(Student x) {
        super.add(x);
        saved = false;
    }

    public void addNewRegistration(Inputter inputter, Mountains mountains) {
        String id;
        do {
            id = inputter.inputAndLoop("Student ID: ", Acceptable.STUDENT_ID);
            if (isDuplicateId(id)) {
                System.out.println("Student ID already exists. Please enter another ID.");
            }
        } while (isDuplicateId(id));

        String name = inputter.inputAndLoop("Student name: ", Acceptable.NAME_VALID);
        String phone = inputter.inputAndLoop("Phone number [10 digits]: ", Acceptable.PHONE_VALID);
        String email = inputter.inputAndLoop("Email address: ", Acceptable.EMAIL_VALID);

        String mountainCode;
        do {
            mountainCode = inputter.inputAndLoop("Mountain peak code [MT01-MT13]: ", Acceptable.MOUNTAIN_CODE_VALID);
            if (!mountains.isValidMountainCode(mountainCode)) {
                System.out.println("Invalid mountain code. Please choose from the list.");
            }
        } while (!mountains.isValidMountainCode(mountainCode));

        double fee = Student.calculateTuition(phone);
        Student student = new Student(id.toUpperCase(), name, phone, email, mountainCode.toUpperCase(), fee);
        addStudent(student);
        System.out.println("Registration successful!");
    }

    public void update(Inputter inputter, Mountains mountains) {
        String id = inputter.inputAndLoop("Enter Student ID to update: ", Acceptable.STUDENT_ID);
        Student student = searchById(id);
        if (student == null) {
            System.out.println("This student has not registered yet.");
            return;
        }

        String name = inputter.inputOptional("New name (blank to keep): ", Acceptable.NAME_VALID);
        String phone = inputter.inputOptional("New phone (blank to keep): ", Acceptable.PHONE_VALID);
        String email = inputter.inputOptional("New email (blank to keep): ", Acceptable.EMAIL_VALID);
        String mountainCode = inputter.inputOptional("New mountain code (blank to keep): ", Acceptable.MOUNTAIN_CODE_VALID);

        if (!name.isEmpty()) {
            student.setName(name);
        }
        if (!phone.isEmpty()) {
            student.setPhone(phone);
            student.setTuitionFee(Student.calculateTuition(phone));
        }
        if (!email.isEmpty()) {
            student.setEmail(email);
        }
        if (!mountainCode.isEmpty()) {
            if (!mountains.isValidMountainCode(mountainCode)) {
                System.out.println("Invalid mountain code. Update cancelled for mountain field.");
            } else {
                student.setMountainCode(mountainCode.toUpperCase());
            }
        }
        saved = false;
        System.out.println("Registration information updated successfully.");
    }

    public void delete(String id, Inputter inputter, Mountains mountains) {
        Student student = searchById(id);
        if (student == null) {
            System.out.println("This student has not registered yet.");
            return;
        }

        Mountain mountain = mountains.get(student.getMountainCode());
        String mountainName = mountain != null ? mountain.getMountain() : student.getMountainCode();

        System.out.println("Student Details:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("Student ID: %s%n", student.getId());
        System.out.printf("Name      : %s%n", student.getName());
        System.out.printf("Phone     : %s%n", student.getPhone());
        System.out.printf("Mountain  : %s%n", mountainName);
        System.out.printf("Fee       : %,.0f%n", student.getTuitionFee());
        System.out.println("-----------------------------------------------------");

        String confirm = inputter.inputYesNo("Are you sure you want to delete this registration? (Y/N): ");
        if (confirm.equals("Y")) {
            remove(student);
            saved = false;
            System.out.println("The registration has been successfully deleted.");
        }
    }

    public List<Student> searchByName(String name) {
        List<Student> result = new ArrayList<>();
        String keyword = name.toLowerCase();
        for (Student s : this) {
            if (s.getName().toLowerCase().contains(keyword)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> filterByCampusCode(String campusCode) {
        List<Student> result = new ArrayList<>();
        String prefix = campusCode.toUpperCase();
        for (Student s : this) {
            if (s.getId().toUpperCase().startsWith(prefix)) {
                result.add(s);
            }
        }
        return result;
    }

    public void showAll() {
        showAll(this, "Registered Students:", "No students have registered yet.");
    }

    public void showAll(List<Student> list) {
        showAll(list, "Registered Students:", "No students have registered yet.");
    }

    public void showAll(List<Student> list, String title, String emptyMessage) {
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        System.out.println(title);
        System.out.println(TABLE_HEADER);
        for (Student s : list) {
            System.out.println(s);
        }
        System.out.println(TABLE_FOOTER);
    }

    public void showCampusList(List<Student> list, String campusCode) {
        String campusName = getCampusName(campusCode);
        String title = String.format("Registered Students Under %s Campus (%s):",
                campusName, campusCode.toUpperCase());
        showAll(list, title, "No students have registered under this campus.");
    }

    public void statisticalizeByMountainPeak() {
        Statistics stats = new Statistics(this);
        stats.show();
    }

    public static String getCampusName(String code) {
        switch (code.toUpperCase()) {
            case "CE":
                return "Can Tho";
            case "DE":
                return "Da Nang";
            case "HE":
                return "Ha Noi";
            case "SE":
                return "Ho Chi Minh";
            case "QE":
                return "Quy Nhon";
            default:
                return code.toUpperCase();
        }
    }

    @SuppressWarnings("unchecked")
    public void readFromFile() {
        File file = new File(pathFile);
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<Student> loaded = (List<Student>) ois.readObject();
            clear();
            addAll(loaded);
            saved = true;
        } catch (Exception e) {
            System.out.println("Error loading registration data: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathFile))) {
            oos.writeObject(new ArrayList<>(this));
            saved = true;
            System.out.println("Registration data has been successfully saved to `" + pathFile + "`.");
        } catch (Exception e) {
            System.out.println("Error saving registration data: " + e.getMessage());
        }
    }
}
