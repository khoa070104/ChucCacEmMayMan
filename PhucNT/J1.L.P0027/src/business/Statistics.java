package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.StatisticalInfo;
import model.Student;

public class Statistics extends HashMap<String, StatisticalInfo> {

    public Statistics() {
        super();
    }

    public Statistics(List<Student> list) {
        statisticalize(list);
    }

    public void statisticalize(List<Student> list) {
        clear();
        for (Student s : list) {
            String code = s.getMountainCode();
            StatisticalInfo info = get(code);
            if (info == null) {
                info = new StatisticalInfo(code, code);
                put(code, info);
            }
            info.addStudent(s.getTuitionFee());
        }
    }

    public List<StatisticalInfo> toList() {
        return new ArrayList<>(values());
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("No registration statistics available.");
            return;
        }
        System.out.println("Statistics of Registration by Mountain Peak:");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-10s | %-22s | %s%n", "Peak Name", "Number of Participants", "Total Cost");
        System.out.println("-----------------------------------------------------------------");
        for (StatisticalInfo info : values()) {
            System.out.println(info);
        }
        System.out.println("-----------------------------------------------------------------");
    }
}
