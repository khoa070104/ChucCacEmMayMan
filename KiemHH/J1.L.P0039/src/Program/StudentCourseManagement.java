package Program;

import DataObject.CourseDAO;
import DataObject.StudentDAO;
import Entity.Course;
import Entity.Student;
import Utilities.DataInput;
import Utilities.DataValidation;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentCourseManagement {
    private static final String LINE = "------------------------------------------------------------------------------------------";
    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;
    private boolean changed;

    public StudentCourseManagement(String studentFile, String courseFile) throws IOException {
        studentDAO = new StudentDAO(studentFile);
        courseDAO = new CourseDAO(courseFile, studentDAO);
        studentDAO.loadDataFromFile();
        courseDAO.loadDataFromFile();
    }

    public void run() {
        while (true) {
            showMenu();
            switch (DataInput.getInteger("Please enter your choice: ")) {
                case 1: printStudents(studentDAO.getStudents()); break;
                case 2: addStudent(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: listStudentsByMajor(); break;
                case 6: addCourse(); break;
                case 7: listCoursesGrouped(); break;
                case 8: calculateTotalDuration(); break;
                case 9: removeStudent(); break;
                case 10: printStudents(studentDAO.sortedByGpa()); break;
                case 11: save(); break;
                case 12: if (quit()) return; break;
                default: System.out.println("Invalid choice. Please choose from 1 to 12.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n========== STUDENT AND SHORT COURSE MANAGEMENT ==========");
        System.out.println("1. List all students");
        System.out.println("2. Add a new student");
        System.out.println("3. Search for a student by ID");
        System.out.println("4. Update a student by ID");
        System.out.println("5. List all students by major");
        System.out.println("6. Add a new course");
        System.out.println("7. List all courses by student (grouped)");
        System.out.println("8. Calculate total study duration by student ID");
        System.out.println("9. Remove a student by ID");
        System.out.println("10. Sort students by GPA");
        System.out.println("11. Save data to files");
        System.out.println("12. Quit program");
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) { System.out.println("No students found."); return; }
        System.out.println(LINE);
        System.out.printf("%-9s | %-25s | %-30s | %4s%n", "ID", "Name", "Major", "GPA");
        System.out.println(LINE);
        for (Student s : students)
            System.out.printf("%-9s | %-25s | %-30s | %4.1f%n", s.getId(), s.getFullName(), s.getMajor(), s.getGpa());
        System.out.println(LINE);
    }

    private void addStudent() {
        String id = DataInput.getString("Student ID (STU0000): ").toUpperCase();
        if (!DataValidation.isStudentId(id)) { System.out.println("Invalid student ID format."); return; }
        if (studentDAO.findById(id) != null) { System.out.println("Student ID already exists!"); return; }
        String name = DataInput.getString("Full name: ");
        if (!DataValidation.isFullName(name)) { System.out.println("Full name must contain at least two words."); return; }
        String major = DataInput.getString("Major: ");
        if (!DataValidation.isNotBlank(major)) { System.out.println("Major cannot be empty."); return; }
        double gpa = DataInput.getDouble("GPA: ");
        if (!DataValidation.isGpa(gpa)) { System.out.println("GPA must be between 0.0 and 4.0."); return; }
        studentDAO.add(new Student(id, name, major, gpa));
        changed = true;
        System.out.println("Student added successfully.");
    }

    private Student askForStudent() {
        Student student = studentDAO.findById(DataInput.getString("Student ID: "));
        if (student == null) System.out.println("Student ID does not exist!");
        return student;
    }

    private void searchStudent() {
        Student student = askForStudent();
        if (student != null) printStudents(java.util.Collections.singletonList(student));
    }

    private void updateStudent() {
        Student student = askForStudent();
        if (student == null) return;
        double gpa = DataInput.getDouble("New GPA: ");
        if (!DataValidation.isGpa(gpa)) { System.out.println("GPA must be between 0.0 and 4.0."); return; }
        student.setGpa(gpa);
        changed = true;
        System.out.println("Student updated successfully.");
    }

    private void listStudentsByMajor() {
        String major = DataInput.getString("Major: ");
        if (!DataValidation.isNotBlank(major)) { System.out.println("Major cannot be empty."); return; }
        printStudents(studentDAO.findByMajor(major));
    }

    private void addCourse() {
        if (studentDAO.getStudents().isEmpty()) { System.out.println("No students are available."); return; }
        printStudents(studentDAO.getStudents());
        String id = DataInput.getString("Course ID: ").toUpperCase();
        if (!DataValidation.isNotBlank(id)) { System.out.println("Course ID cannot be empty."); return; }
        if (courseDAO.findById(id) != null) { System.out.println("Course ID already exists!"); return; }
        Student student = askForStudent();
        if (student == null) return;
        String name = DataInput.getString("Course name: ");
        if (!DataValidation.isNotBlank(name)) { System.out.println("Course name cannot be empty."); return; }
        int duration = DataInput.getInteger("Duration (weeks): ");
        if (!DataValidation.isPositive(duration)) { System.out.println("Duration must be at least 1 week."); return; }
        LocalDate startDate = DataInput.getDate("Start date (dd/MM/yyyy): ");
        if (!DataValidation.isFutureDate(startDate)) { System.out.println("Start date must be a future date."); return; }
        courseDAO.add(new Course(id, student.getId(), name, duration, startDate));
        changed = true;
        System.out.println("Course added successfully.");
    }

    private void listCoursesGrouped() {
        for (Student student : studentDAO.getStudents()) {
            System.out.printf("%n%s - %s%n", student.getId(), student.getFullName());
            List<Course> courses = courseDAO.findByStudentId(student.getId());
            if (courses.isEmpty()) { System.out.println("  No courses assigned."); continue; }
            System.out.printf("  %-10s | %-25s | %-8s | %-10s%n", "Course ID", "Course Name", "Weeks", "Start Date");
            for (Course c : courses)
                System.out.printf("  %-10s | %-25s | %-8d | %-10s%n", c.getId(), c.getName(),
                        c.getDurationWeeks(), c.getStartDate().format(Course.DATE_FORMAT));
        }
    }

    private void calculateTotalDuration() {
        Student student = askForStudent();
        if (student != null) System.out.printf("Total study duration for %s: %d week(s).%n",
                student.getId(), courseDAO.totalDuration(student.getId()));
    }

    private void removeStudent() {
        Student student = askForStudent();
        if (student == null) return;
        if (!courseDAO.findByStudentId(student.getId()).isEmpty()) {
            System.out.println("Cannot delete: Student is assigned to Courses.");
            return;
        }
        studentDAO.remove(student.getId());
        changed = true;
        System.out.println("Student removed successfully.");
    }

    private void save() {
        try {
            studentDAO.saveDataToFile();
            courseDAO.saveDataToFile();
            changed = false;
            System.out.println("Data saved successfully.");
        } catch (IOException ex) { System.out.println("Cannot save data: " + ex.getMessage()); }
    }

    private boolean quit() {
        if (changed && DataInput.getYesNo("Do you want to save the changes before exiting? (Y/N): ")) save();
        System.out.println("Goodbye!");
        return true;
    }
}
