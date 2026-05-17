package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Student;
import common.Constants;
import common.Messages;

public class StudentController {
    private final ArrayList<Student> listStd = new ArrayList<>();

    public Student getStudentById(String studentId) {
        for (Student student : listStd) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    private boolean isDuplicateId(String id) {
        return getStudentById(id) != null;
    }

    private String inputCourse() {
        System.out.println("Select course:");
        for (int i = 0; i < Constants.COURSES.length; i++) {
            System.out.println((i + 1) + ". " + Constants.COURSES[i]);
        }
        while (true) {
            String choice = Inputter.inputRequired("Enter course (1-3): ");
            try {
                int index = Integer.parseInt(choice);
                if (index >= 1 && index <= Constants.COURSES.length) {
                    return Constants.COURSES[index - 1];
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println(Messages.ERR_INVALID_INPUT);
        }
    }

    public void createStudent() {
        do {
            String id;
            do {
                id = Inputter.inputRequired("Enter ID: ");
                if (isDuplicateId(id)) {
                    System.out.println(Messages.ERR_DUP_STUDENT_ID);
                }
            } while (isDuplicateId(id));

            String name = Inputter.inputRequired("Enter name: ");
            String semester = Inputter.inputRequired("Enter semester: ");
            String course = inputCourse();

            listStd.add(new Student(id, name, semester, course));
            System.out.println(Messages.MSG_STUDENT_ADDED_SUCCESS);
        } while (listStd.size() < Constants.MIN_STUDENTS);

        while (true) {
            String choice = Inputter.inputRequired(Messages.MSG_CONTINUE_ADDING, Constants.YES_NO_VALIDATE);
            if (choice.equalsIgnoreCase("N")) {
                break;
            }

            String id;
            do {
                id = Inputter.inputRequired("Enter ID: ");
                if (isDuplicateId(id)) {
                    System.out.println(Messages.ERR_DUP_STUDENT_ID);
                }
            } while (isDuplicateId(id));

            String name = Inputter.inputRequired("Enter name: ");
            String semester = Inputter.inputRequired("Enter semester: ");
            String course = inputCourse();

            listStd.add(new Student(id, name, semester, course));
            System.out.println(Messages.MSG_STUDENT_ADDED_SUCCESS);
        }
    }

    public void findAndSort() {
        if (listStd.isEmpty()) {
            System.out.println(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }

        String name = Inputter.inputRequired("Enter name: ");
        ArrayList<Student> result = new ArrayList<>();

        for (Student student : listStd) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            System.out.println(Messages.ERR_NO_STUDENTS_FOUND);
            return;
        }

        Collections.sort(result, new StudentNameComparator());

        System.out.println(Messages.MSG_SEARCH_RESULTS);
        System.out.printf("%-20s | %-10s | %-15s%n", "Student name", "Semester", "Course Name");
        System.out.println(Constants.TABLE_SEPARATOR);
        for (Student student : result) {
            System.out.printf("%-20s | %-10s | %-15s%n",
                    student.getName(), student.getSemester(), student.getCourse());
        }
    }

    public void updateOrDelete() {
        if (listStd.isEmpty()) {
            System.out.println(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }

        String id = Inputter.inputRequired("Enter ID: ");
        Student student = getStudentById(id);
        if (student == null) {
            System.out.println(Messages.ERR_STUDENT_NOT_FOUND);
            return;
        }

        System.out.printf("Found: %s | %s | %s | %s%n",
                student.getId(), student.getName(), student.getSemester(), student.getCourse());

        String choice = Inputter.inputRequired(Messages.MSG_UPDATE_DELETE, Constants.UPDATE_DELETE_VALIDATE);
        if (choice.equalsIgnoreCase("D")) {
            listStd.remove(student);
            System.out.println(Messages.MSG_DELETED_SUCCESS);
            return;
        }

        String name = Inputter.inputRequired("Enter name: ");
        String semester = Inputter.inputRequired("Enter semester: ");
        String course = inputCourse();

        student.setName(name);
        student.setSemester(semester);
        student.setCourse(course);
        System.out.println(Messages.MSG_UPDATED_SUCCESS);
    }

    public void report() {
        if (listStd.isEmpty()) {
            System.out.println(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }

        Map<String, Integer> totals = new HashMap<>();
        for (Student student : listStd) {
            String key = student.getName() + "|" + student.getCourse();
            totals.put(key, totals.getOrDefault(key, 0) + 1);
        }

        ArrayList<String> keys = new ArrayList<>(totals.keySet());
        Collections.sort(keys, (k1, k2) -> {
            String[] p1 = k1.split("\\|", 2);
            String[] p2 = k2.split("\\|", 2);
            int byName = p1[0].compareToIgnoreCase(p2[0]);
            if (byName != 0) {
                return byName;
            }
            return p1[1].compareToIgnoreCase(p2[1]);
        });

        System.out.println(Messages.MSG_REPORT_HEADER);
        for (String key : keys) {
            String[] parts = key.split("\\|", 2);
            System.out.printf("%s | %s | %d%n", parts[0], parts[1], totals.get(key));
        }
    }
}
