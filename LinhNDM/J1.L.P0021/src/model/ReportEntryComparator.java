package model;

import java.util.Comparator;

/**
 * So sánh các dòng báo cáo theo tên sinh viên rồi theo khóa học.
 *
 * @author LinhNDM
 */
public class ReportEntryComparator implements Comparator<ReportEntry> {

    /**
     * So sánh hai dòng báo cáo.
     *
     * @param first dòng báo cáo thứ nhất
     * @param second dòng báo cáo thứ hai
     * @return kết quả so sánh
     */
    @Override
    public int compare(ReportEntry first, ReportEntry second) {
        int byName;
        byName = first.getName().compareToIgnoreCase(second.getName());
        if (byName != 0) {
            return byName;
        }
        return first.getCourse().compareToIgnoreCase(second.getCourse());
    }
}
