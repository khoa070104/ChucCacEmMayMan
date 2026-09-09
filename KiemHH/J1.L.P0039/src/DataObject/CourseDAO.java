package DataObject;

import Entity.Course;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDAO {
    private final List<Course> courses = new ArrayList<Course>();
    private final FileManager fileManager;
    private final StudentDAO studentDAO;

    public CourseDAO(String fileName, StudentDAO studentDAO) {
        fileManager = new FileManager(fileName);
        this.studentDAO = studentDAO;
    }

    public void loadDataFromFile() throws IOException {
        courses.clear();
        for (String line : fileManager.readDataFromFile()) {
            String[] fields = line.split(",", -1);
            if (fields.length != 5) continue;
            try {
                Course course = new Course(fields[0].trim().toUpperCase(), fields[1].trim().toUpperCase(),
                        fields[2].trim(), Integer.parseInt(fields[3].trim()),
                        LocalDate.parse(fields[4].trim(), Course.DATE_FORMAT));
                if (!course.getId().isEmpty() && !course.getName().isEmpty()
                        && course.getDurationWeeks() >= 1 && studentDAO.findById(course.getStudentId()) != null
                        && findById(course.getId()) == null) courses.add(course);
            } catch (NumberFormatException | DateTimeParseException ignored) { }
        }
    }

    public Course findById(String id) {
        for (Course course : courses)
            if (course.getId().equalsIgnoreCase(id == null ? "" : id.trim())) return course;
        return null;
    }
    public boolean add(Course course) {
        if (findById(course.getId()) != null) return false;
        courses.add(course);
        return true;
    }
    public List<Course> findByStudentId(String studentId) {
        return courses.stream().filter(c -> c.getStudentId().equalsIgnoreCase(studentId))
                .collect(Collectors.toList());
    }
    public int totalDuration(String studentId) {
        return findByStudentId(studentId).stream().mapToInt(Course::getDurationWeeks).sum();
    }
    public void saveDataToFile() throws IOException {
        fileManager.saveDataToFile(courses.stream().map(Course::toString).collect(Collectors.toList()));
    }
}
