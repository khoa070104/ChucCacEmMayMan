package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private final String id;
    private final String studentId;
    private final String name;
    private final int durationWeeks;
    private final LocalDate startDate;

    public Course(String id, String studentId, String name, int durationWeeks, LocalDate startDate) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.durationWeeks = durationWeeks;
        this.startDate = startDate;
    }

    public String getId() { return id; }
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getDurationWeeks() { return durationWeeks; }
    public LocalDate getStartDate() { return startDate; }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %s", id, studentId, name,
                durationWeeks, startDate.format(DATE_FORMAT));
    }
}
