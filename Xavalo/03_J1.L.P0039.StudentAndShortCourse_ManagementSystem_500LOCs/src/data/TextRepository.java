package data;

import model.Course;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class TextRepository {
    public List<Student> loadStudents(String fileName) throws IOException {
        List<Student> result = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return result;
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] p = line.split(",", 4); if (p.length != 4) continue;
                try { result.add(new Student(p[0].trim(), p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()))); }
                catch (RuntimeException ignored) { System.out.println("Skipped invalid student record: " + line); }
            }
        }
        return result;
    }

    public List<Course> loadCourses(String fileName) throws IOException {
        List<Course> result = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return result;
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] p = line.split(",", 5); if (p.length != 5) continue;
                try { result.add(new Course(p[0].trim(), p[1].trim(), p[2].trim(), Integer.parseInt(p[3].trim()), LocalDate.parse(p[4].trim(), Course.DATE_FORMAT))); }
                catch (RuntimeException ignored) { System.out.println("Skipped invalid course record: " + line); }
            }
        }
        return result;
    }

    public void save(String studentFile, String courseFile, Collection<Student> students, Collection<Course> courses) throws IOException {
        try (PrintWriter writer = new PrintWriter(studentFile, "UTF-8")) {
            for (Student student : students) writer.println(student.toFileLine());
        }
        try (PrintWriter writer = new PrintWriter(courseFile, "UTF-8")) {
            for (Course course : courses) writer.println(course.toFileLine());
        }
    }
}
