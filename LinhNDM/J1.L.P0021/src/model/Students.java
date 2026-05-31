package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Quản lý danh sách sinh viên và xử lý nghiệp vụ thêm, sửa, xóa, tìm kiếm.
 *
 * @author LinhNDM
 */
public class Students extends ArrayList<Student> {

    /**
     * Tìm sinh viên theo mã định danh.
     *
     * @param studentId mã sinh viên cần tìm
     * @return sinh viên tìm thấy hoặc null nếu không tồn tại
     */
    public Student getStudentById(String studentId) {
        for (Student student : this) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Kiểm tra mã sinh viên đã tồn tại trong hệ thống hay chưa.
     *
     * @param id mã sinh viên cần kiểm tra
     * @return true nếu trùng ID
     */
    public boolean isDuplicateId(String id) {
        return getStudentById(id) != null;
    }

    /**
     * Thêm sinh viên mới sau khi kiểm tra trùng ID.
     *
     * @param student sinh viên cần thêm
     * @return true nếu thêm thành công
     */
    public boolean addStudent(Student student) {
        if (isDuplicateId(student.getId())) {
            return false;
        }
        return add(student);
    }

    /**
     * Xóa sinh viên theo mã định danh.
     *
     * @param studentId mã sinh viên cần xóa
     * @return true nếu xóa thành công
     */
    public boolean removeStudentById(String studentId) {
        Student student;
        student = getStudentById(studentId);
        if (student == null) {
            return false;
        }
        return remove(student);
    }

    /**
     * Cập nhật thông tin sinh viên theo mã định danh.
     *
     * @param studentId mã sinh viên cần cập nhật
     * @param name tên mới
     * @param semester học kỳ mới
     * @param course khóa học mới
     * @return true nếu cập nhật thành công
     */
    public boolean updateStudent(String studentId, String name,
            String semester, String course) {
        Student student;
        student = getStudentById(studentId);
        if (student == null) {
            return false;
        }
        student.setName(name);
        student.setSemester(semester);
        student.setCourse(course);
        return true;
    }

    /**
     * Tìm kiếm sinh viên theo tên (không phân biệt hoa thường).
     *
     * @param name từ khóa tên cần tìm
     * @return danh sách sinh viên khớp tên
     */
    public ArrayList<Student> searchByName(String name) {
        ArrayList<Student> resultList;
        String keyword;
        resultList = new ArrayList<>();
        keyword = name.toLowerCase();
        for (Student student : this) {
            if (student.getName().toLowerCase().contains(keyword)) {
                resultList.add(student);
            }
        }
        return resultList;
    }

    /**
     * Tìm kiếm và sắp xếp sinh viên theo tên.
     *
     * @param name từ khóa tên cần tìm
     * @return danh sách sinh viên đã sắp xếp
     */
    public ArrayList<Student> searchAndSortByName(String name) {
        ArrayList<Student> resultList;
        resultList = searchByName(name);
        Collections.sort(resultList, new StudentNameComparator());
        return resultList;
    }

    /**
     * Tạo báo cáo thống kê số lần đăng ký theo tên và khóa học.
     *
     * @return danh sách dòng báo cáo đã sắp xếp
     */
    public ArrayList<ReportEntry> buildReport() {
        ArrayList<ReportEntry> reportList;
        ReportEntry entry;
        boolean found;
        reportList = new ArrayList<>();
        // Gom nhóm theo tên và khóa học
        for (Student student : this) {
            found = false;
            for (ReportEntry reportEntry : reportList) {
                if (reportEntry.getName().equalsIgnoreCase(student.getName())
                        && reportEntry.getCourse().equalsIgnoreCase(
                                student.getCourse())) {
                    reportEntry.setCount(reportEntry.getCount() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                entry = new ReportEntry(student.getName(),
                        student.getCourse(), 1);
                reportList.add(entry);
            }
        }
        Collections.sort(reportList, new ReportEntryComparator());
        return reportList;
    }
}
