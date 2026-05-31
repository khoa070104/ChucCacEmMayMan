package model;

import java.util.Comparator;

/**
 * So sánh sinh viên theo tên (không phân biệt hoa thường).
 *
 * @author LinhNDM
 */
public class StudentNameComparator implements Comparator<Student> {

    /**
     * So sánh hai sinh viên theo tên.
     *
     * @param first sinh viên thứ nhất
     * @param second sinh viên thứ hai
     * @return kết quả so sánh theo tên
     */
    @Override
    public int compare(Student first, Student second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
