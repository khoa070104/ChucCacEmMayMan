package service;

import data.TextRepository;
import model.Course;
import model.Student;
import util.Input;

import java.io.IOException;
import java.util.*;

public class UniversityManager {
    private static final String STUDENT_FILE = "Students.txt";
    private static final String COURSE_FILE = "Courses.txt";
    private final Input input = new Input();
    private final TextRepository repository = new TextRepository();
    private final Map<String, Student> students = new LinkedHashMap<>();
    private final Map<String, Course> courses = new LinkedHashMap<>();
    private boolean changed;

    public void run() {
        load();
        while (true) {
            menu();
            int choice = input.integer("Select: ", n -> n >= 1 && n <= 12, "Select 1 to 12.");
            switch (choice) {
                case 1 -> listStudents(students.values()); case 2 -> addStudent(); case 3 -> searchStudent();
                case 4 -> updateStudent(); case 5 -> listByMajor(); case 6 -> addCourse();
                case 7 -> listCoursesGrouped(); case 8 -> totalDuration(); case 9 -> removeStudent();
                case 10 -> sortByGpa(); case 11 -> save(); case 12 -> { if (quit()) return; }
            }
        }
    }

    private void menu() {
        System.out.println("\n===== STUDENT AND SHORT COURSE MANAGEMENT =====");
        System.out.println("1. List all students\n2. Add a new student\n3. Search for a student by ID");
        System.out.println("4. Update a student by ID\n5. List all students by major\n6. Add a new course");
        System.out.println("7. List all courses by student\n8. Calculate total study duration\n9. Remove a student");
        System.out.println("10. Sort students by GPA\n11. Save data to files\n12. Quit program");
    }

    private void load() {
        try {
            students.clear(); for (Student s : repository.loadStudents(STUDENT_FILE)) students.put(key(s.getId()), s);
            courses.clear(); for (Course c : repository.loadCourses(COURSE_FILE)) if (students.containsKey(key(c.getStudentId()))) courses.put(key(c.getId()), c);
            changed = false;
        } catch (IOException e) { System.out.println("Unable to load data: " + e.getMessage()); }
    }

    private void listStudents(Collection<Student> source) {
        if (source.isEmpty()) { System.out.println("No students found."); return; }
        System.out.printf("%-10s | %-25s | %-28s | %5s%n", "ID", "Name", "Major", "GPA");
        System.out.println("----------------------------------------------------------------------------");
        for (Student s : source) System.out.printf("%-10s | %-25s | %-28s | %5.2f%n", s.getId(), s.getFullName(), s.getMajor(), s.getGpa());
    }

    private void addStudent() {
        String id = input.text("Student ID: ", s -> s.matches("(?i)STU\\d{4}") && !students.containsKey(key(s)), "ID must be unique and match STU0000.").toUpperCase();
        String name = input.text("Full name: ", UniversityManager::hasTwoWords, "Full name must contain at least two words.");
        String major = input.text("Major: ", s -> !s.isBlank(), "Major cannot be empty.");
        double gpa = input.decimal("GPA: ", n -> n >= 0 && n <= 4, "GPA must be from 0.0 to 4.0.");
        students.put(id, new Student(id, name, major, gpa)); changed = true; System.out.println("Student added successfully.");
    }

    private void searchStudent() {
        Student s = students.get(key(input.text("Student ID: ", x -> !x.isBlank(), "ID is required.")));
        if (s == null) System.out.println("Student ID does not exist!"); else listStudents(List.of(s));
    }

    private void updateStudent() {
        Student s = students.get(key(input.text("Student ID: ", x -> !x.isBlank(), "ID is required.")));
        if (s == null) { System.out.println("Student ID does not exist!"); return; }
        s.setGpa(input.decimal("New GPA: ", n -> n >= 0 && n <= 4, "GPA must be from 0.0 to 4.0."));
        changed = true; System.out.println("Student GPA updated successfully.");
    }

    private void listByMajor() {
        String major = input.text("Major: ", s -> !s.isBlank(), "Major cannot be empty.");
        listStudents(students.values().stream().filter(s -> s.getMajor().equalsIgnoreCase(major)).toList());
    }

    private void addCourse() {
        if (students.isEmpty()) { System.out.println("Add a student first."); return; }
        String id = input.text("Course ID: ", s -> !s.isBlank() && !courses.containsKey(key(s)), "Course ID must be non-empty and unique.").toUpperCase();
        System.out.println("Available students:"); for (Student s : students.values()) System.out.println(s.getId() + " - " + s.getFullName());
        String studentId = input.text("Student ID: ", s -> students.containsKey(key(s)), "Student ID does not exist!").toUpperCase();
        String name = input.text("Course name: ", s -> !s.isBlank(), "Course name cannot be empty.");
        int weeks = input.integer("Duration in weeks: ", n -> n >= 1, "Duration must be at least 1 week.");
        Course course = new Course(id, studentId, name, weeks, input.futureDate("Start date (dd/MM/yyyy): "));
        courses.put(id, course); changed = true; System.out.println("Course added successfully.");
    }

    private void listCoursesGrouped() {
        if (students.isEmpty()) { System.out.println("No students found."); return; }
        for (Student s : students.values()) {
            System.out.println("\n" + s.getId() + " - " + s.getFullName());
            List<Course> assigned = courses.values().stream().filter(c -> c.getStudentId().equalsIgnoreCase(s.getId())).toList();
            if (assigned.isEmpty()) { System.out.println("  No assigned courses."); continue; }
            System.out.printf("  %-10s %-28s %-8s %-12s%n", "Course ID", "Course name", "Weeks", "Start date");
            for (Course c : assigned) System.out.printf("  %-10s %-28s %-8d %-12s%n", c.getId(), c.getName(), c.getDurationWeeks(), c.getStartDate().format(Course.DATE_FORMAT));
        }
    }

    private void totalDuration() {
        String id = input.text("Student ID: ", s -> !s.isBlank(), "ID is required.");
        if (!students.containsKey(key(id))) { System.out.println("Student ID does not exist!"); return; }
        int total = courses.values().stream().filter(c -> c.getStudentId().equalsIgnoreCase(id)).mapToInt(Course::getDurationWeeks).sum();
        System.out.println("Total study duration: " + total + " week(s).");
    }

    private void removeStudent() {
        String id = input.text("Student ID: ", s -> !s.isBlank(), "ID is required.");
        if (!students.containsKey(key(id))) { System.out.println("Student ID does not exist!"); return; }
        if (courses.values().stream().anyMatch(c -> c.getStudentId().equalsIgnoreCase(id))) { System.out.println("Cannot delete: Student is assigned to Courses."); return; }
        students.remove(key(id)); changed = true; System.out.println("Student removed successfully.");
    }

    private void sortByGpa() {
        List<Student> result = new ArrayList<>(students.values());
        result.sort(Comparator.comparingDouble(Student::getGpa).thenComparing(Student::getId));
        listStudents(result);
    }

    private boolean save() {
        try { repository.save(STUDENT_FILE, COURSE_FILE, students.values(), courses.values()); changed = false; System.out.println("Student and course data saved successfully."); return true; }
        catch (IOException e) { System.out.println("Unable to save data: " + e.getMessage()); return false; }
    }

    private boolean quit() {
        if (changed && input.yesNo("Do you want to save the changes before exiting? (Y/N): ") && !save()) return false;
        System.out.println("Goodbye!"); return true;
    }

    private static boolean hasTwoWords(String s) { return s.trim().split("\\s+").length >= 2; }
    private static String key(String s) { return s.trim().toUpperCase(); }
}
