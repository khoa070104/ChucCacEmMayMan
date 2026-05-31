package view;

import common.Constants;
import common.Messages;
import java.util.ArrayList;
import model.ReportEntry;
import model.Student;

/**
 * Hiển thị giao diện và thu thập dữ liệu nhập từ người dùng.
 *
 * @author LinhNDM
 */
public class MainView {

    /**
     * Hiển thị menu chính của chương trình.
     */
    public void displayMenu() {
        System.out.println(Messages.MSG_WELCOME);
        System.out.println();
        System.out.println("1. Create");
        System.out.println("2. Find and Sort");
        System.out.println("3. Update/Delete");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println();
        System.out.println(Messages.MSG_MENU_GUIDE);
    }

    /**
     * Nhập lựa chọn menu và validate phạm vi hợp lệ.
     *
     * @return số lựa chọn từ 1 đến 5
     */
    public int getMenuChoice() {
        String inputValue;
        int choice;
        while (true) {
            try {
                inputValue = Inputter.inputRequired(Constants.LABEL_YOUR_CHOICE);
                choice = Integer.parseInt(inputValue);
                if (choice >= Constants.MIN_MENU_CHOICE
                        && choice <= Constants.MAX_MENU_CHOICE) {
                    return choice;
                }
                System.out.println(Messages.ERR_MENU_RANGE);
            } catch (NumberFormatException exception) {
                System.out.println(Messages.ERR_INVALID_NUMBER);
            }
        }
    }

    /**
     * Nhập mã sinh viên.
     *
     * @return mã sinh viên đã nhập
     */
    public String inputStudentId() {
        return Inputter.inputRequired(Constants.LABEL_ENTER_ID);
    }

    /**
     * Nhập tên sinh viên.
     *
     * @return tên sinh viên đã nhập
     */
    public String inputName() {
        return Inputter.inputRequired(Constants.LABEL_ENTER_NAME);
    }

    /**
     * Nhập học kỳ.
     *
     * @return học kỳ đã nhập
     */
    public String inputSemester() {
        return Inputter.inputRequired(Constants.LABEL_ENTER_SEMESTER);
    }

    /**
     * Nhập khóa học từ danh sách có sẵn.
     *
     * @return tên khóa học đã chọn
     */
    public String inputCourse() {
        String choice;
        int index;
        int courseIndex;
        System.out.println(Constants.LABEL_SELECT_COURSE);
        for (courseIndex = 0; courseIndex < Constants.COURSES.length; courseIndex++) {
            System.out.println((courseIndex + 1) + ". " + Constants.COURSES[courseIndex]);
        }
        while (true) {
            choice = Inputter.inputRequired(Constants.LABEL_ENTER_COURSE);
            try {
                index = Integer.parseInt(choice);
                if (index >= Constants.MIN_COURSE_INDEX
                        && index <= Constants.COURSES.length) {
                    return Constants.COURSES[index - 1];
                }
            } catch (NumberFormatException exception) {
                // Bỏ qua, hiển thị thông báo lỗi bên dưới
            }
            System.out.println(Messages.ERR_INVALID_INPUT);
        }
    }

    /**
     * Nhập lựa chọn tiếp tục thêm sinh viên.
     *
     * @return Y hoặc N
     */
    public String inputContinueChoice() {
        return Inputter.inputRequired(Messages.MSG_CONTINUE_ADDING,
                Constants.YES_NO_VALIDATE);
    }

    /**
     * Nhập lựa chọn cập nhật hoặc xóa sinh viên.
     *
     * @return U hoặc D
     */
    public String inputUpdateOrDeleteChoice() {
        return Inputter.inputRequired(Messages.MSG_UPDATE_DELETE,
                Constants.UPDATE_DELETE_VALIDATE);
    }

    /**
     * Hiển thị một thông báo ra màn hình.
     *
     * @param message nội dung thông báo
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Hiển thị thông tin sinh viên tìm thấy.
     *
     * @param student sinh viên cần hiển thị
     */
    public void displayFoundStudent(Student student) {
        System.out.printf(Messages.MSG_FOUND_STUDENT + "%n",
                student.getId(), student.getName(),
                student.getSemester(), student.getCourse());
    }

    /**
     * Hiển thị kết quả tìm kiếm sinh viên.
     *
     * @param studentList danh sách sinh viên tìm được
     */
    public void displaySearchResults(ArrayList<Student> studentList) {
        int index;
        Student student;
        System.out.println(Messages.MSG_SEARCH_RESULTS);
        System.out.printf(Messages.MSG_SEARCH_TABLE_HEADER,
                "Student name", "Semester", "Course Name");
        System.out.println(Constants.TABLE_SEPARATOR);
        for (index = 0; index < studentList.size(); index++) {
            student = studentList.get(index);
            System.out.printf(Messages.MSG_SEARCH_TABLE_ROW,
                    student.getName(), student.getSemester(), student.getCourse());
        }
    }

    /**
     * Hiển thị báo cáo thống kê sinh viên.
     *
     * @param reportList danh sách dòng báo cáo
     */
    public void displayReport(ArrayList<ReportEntry> reportList) {
        int index;
        ReportEntry entry;
        System.out.println(Messages.MSG_REPORT_HEADER);
        for (index = 0; index < reportList.size(); index++) {
            entry = reportList.get(index);
            System.out.printf(Messages.MSG_REPORT_ROW,
                    entry.getName(), entry.getCourse(), entry.getCount());
        }
    }
}
