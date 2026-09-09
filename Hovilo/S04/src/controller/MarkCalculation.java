package controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Student;

/** Performs student creation, classification, and statistics. @author Ho Vi Lo @since 09/09/2026 */
public class MarkCalculation {
    /**
     * Creates a student object.
     * @param name student name @param classes class name @param maths maths mark
     * @param chemistry chemistry mark @param physics physics mark
     * @return newly created student
     */
    public Student createStudent(String name, String classes, double maths,
            double chemistry, double physics) {
        return new Student(name, classes, maths, chemistry, physics);
    }

    /**
     * Calculates each average and assigns A, B, C, or D.
     * Algorithm: calculate the mean, then compare it with descending thresholds.
     * @param students unclassified students @return the same list after classification
     */
    public List<Student> averageStudent(List<Student> students) {
        for (Student student : students) {
            double average = student.calculateAverage();
            student.setAverage(average);
            if (average > 7.5) student.setType("A");
            else if (average >= 6) student.setType("B");
            else if (average >= 4) student.setType("C");
            else student.setType("D");
        }
        return students;
    }

    /**
     * Calculates the percentage of every classification.
     * @param students classified students @return ordered percentages keyed A through D
     */
    public Map<String, Double> getPercentTypeStudent(List<Student> students) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (String type : new String[]{"A", "B", "C", "D"}) {
            int count = 0;
            for (Student student : students) if (type.equals(student.getType())) count++;
            result.put(type, students.isEmpty() ? 0 : count * 100.0 / students.size());
        }
        return result;
    }
}
