package data;

import model.Course;
import model.Student;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class TextRepository {
    public List<Student> loadStudents(String fileName) throws IOException {
        List<Student> result = new ArrayList<>();
        Path path = Path.of(fileName); if (!Files.exists(path)) return result;
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            String[] p = line.split(",", 4); if (p.length != 4) continue;
            try { result.add(new Student(p[0].trim(), p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()))); }
            catch (RuntimeException ignored) { System.out.println("Skipped invalid student record: " + line); }
        }
        return result;
    }

    public List<Course> loadCourses(String fileName) throws IOException {
        List<Course> result = new ArrayList<>();
        Path path = Path.of(fileName); if (!Files.exists(path)) return result;
        for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
            String[] p = line.split(",", 5); if (p.length != 5) continue;
            try { result.add(new Course(p[0].trim(), p[1].trim(), p[2].trim(), Integer.parseInt(p[3].trim()), LocalDate.parse(p[4].trim(), Course.DATE_FORMAT))); }
            catch (RuntimeException ignored) { System.out.println("Skipped invalid course record: " + line); }
        }
        return result;
    }

    public void save(String studentFile, String courseFile, Collection<Student> students, Collection<Course> courses) throws IOException {
        List<String> studentLines = students.stream().map(Student::toFileLine).toList();
        List<String> courseLines = courses.stream().map(Course::toFileLine).toList();
        Files.write(Path.of(studentFile), studentLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(Path.of(courseFile), courseLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
