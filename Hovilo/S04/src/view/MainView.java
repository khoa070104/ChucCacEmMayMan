package view;

import controller.Inputter;
import controller.MarkCalculation;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Displays the student management program. @author Ho Vi Lo @since 09/09/2026 */
public class MainView {
    private final Inputter inputter = new Inputter();
    private final MarkCalculation calculation = new MarkCalculation();

    /** Reads students, classifies them, and displays all results. */
    public void run() {
        List<Student> students = new ArrayList<>();
        System.out.println("====== Management Student Program ======");
        do {
            String name = inputter.readRequired("Name: ");
            String classes = inputter.readRequired("Classes: ");
            double maths = inputter.readMark("Maths");
            double chemistry = inputter.readMark("Chemistry");
            double physics = inputter.readMark("Physics");
            students.add(calculation.createStudent(name, classes, maths, chemistry, physics));
        } while (inputter.readYesNo("Do you want to enter more student information? (Y/N): "));
        calculation.averageStudent(students);
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println("------ Student " + (i + 1) + " Info ------");
            System.out.println("Name: " + student.getName());
            System.out.println("Classes: " + student.getClassName());
            System.out.printf("AVG: %.1f%n", student.getAverage());
            System.out.println("Type: " + student.getType());
        }
        System.out.println("-------- Classification Info --------");
        for (Map.Entry<String, Double> entry :
                calculation.getPercentTypeStudent(students).entrySet())
            System.out.printf("%s: %.1f%%%n", entry.getKey(), entry.getValue());
    }
}
