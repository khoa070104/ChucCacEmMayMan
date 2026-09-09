package DataObject;

import Entity.Student;

import Utilities.DataValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDAO {
    private final List<Student> students = new ArrayList<Student>();
    private final FileManager fileManager;

    public StudentDAO(String fileName) {
        fileManager = new FileManager(fileName);
    }

    public void loadDataFromFile() throws IOException {
        students.clear();
        for (String line : fileManager.readDataFromFile()) {
            String[] fields = line.split(",", -1);
            if (fields.length != 4) continue;
            try {
                Student student =
                        new Student(
                                fields[0].trim().toUpperCase(),
                                fields[1].trim(),
                                fields[2].trim(),
                                Double.parseDouble(fields[3].trim()));
                if (DataValidation.isStudentId(student.getId())
                        && DataValidation.isFullName(student.getFullName())
                        && DataValidation.isNotBlank(student.getMajor())
                        && DataValidation.isGpa(student.getGpa())
                        && findById(student.getId()) == null) {
                    students.add(student);
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    public List<Student> getStudents() {
        return new ArrayList<Student>(students);
    }

    public Student findById(String id) {
        for (Student student : students)
            if (student.getId().equalsIgnoreCase(id == null ? "" : id.trim())) return student;
        return null;
    }

    public boolean add(Student student) {
        if (findById(student.getId()) != null) return false;
        students.add(student);
        return true;
    }

    public boolean remove(String id) {
        Student student = findById(id);
        return student != null && students.remove(student);
    }

    public List<Student> findByMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major.trim()))
                .collect(Collectors.toList());
    }

    public List<Student> sortedByGpa() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).thenComparing(Student::getId))
                .collect(Collectors.toList());
    }

    public void saveDataToFile() throws IOException {
        fileManager.saveDataToFile(
                students.stream().map(Student::toString).collect(Collectors.toList()));
    }
}
