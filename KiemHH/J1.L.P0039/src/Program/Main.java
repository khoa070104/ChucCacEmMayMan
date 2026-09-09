package Program;

public class Main {
    public static void main(String[] args) {
        try {
            new StudentCourseManagement("Students.txt", "Courses.txt").run();
        } catch (Exception ex) {
            System.out.println("Cannot start the program: " + ex.getMessage());
        }
    }
}
