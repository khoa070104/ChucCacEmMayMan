package controller;

import common.Constants;
import common.Messages;
import java.util.ArrayList;
import model.ReportEntry;
import model.Student;
import model.Students;
import view.MainView;

/**
 * Điều phối luồng chương trình giữa View và Model.
 *
 * @author LinhNDM
 */
public class StudentController {

    private final Students studentList;
    private final MainView mainView;

    /**
     * Khởi tạo controller với model và view.
     */
    public StudentController() {
        this.studentList = new Students();
        this.mainView = new MainView();
    }

    /**
     * Chạy chương trình với menu điều hướng chính.
     */
    public void run() {
        int choice;
        while (true) {
            mainView.displayMenu();
            choice = mainView.getMenuChoice();
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSort();
                    break;
                case 3:
                    updateOrDelete();
                    break;
                case 4:
                    report();
                    break;
                case 5:
                    mainView.displayMessage(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    mainView.displayMessage(Messages.ERR_INVALID_INPUT);
            }
        }
    }

    /**
     * Tạo sinh viên mới, bắt buộc tối thiểu MIN_STUDENTS bản ghi.
     */
    public void createStudent() {
        String id;
        String name;
        String semester;
        String course;
        String continueChoice;
        Student student;
        // Thêm tối thiểu số sinh viên yêu cầu
        do {
            do {
                id = mainView.inputStudentId();
                if (studentList.isDuplicateId(id)) {
                    mainView.displayMessage(Messages.ERR_DUP_STUDENT_ID);
                }
            } while (studentList.isDuplicateId(id));
            name = mainView.inputName();
            semester = mainView.inputSemester();
            course = mainView.inputCourse();
            student = new Student(id, name, semester, course);
            studentList.addStudent(student);
            mainView.displayMessage(Messages.MSG_STUDENT_ADDED_SUCCESS);
        } while (studentList.size() < Constants.MIN_STUDENTS);
        // Cho phép thêm sinh viên tùy chọn
        while (true) {
            continueChoice = mainView.inputContinueChoice();
            if (continueChoice.equalsIgnoreCase("N")) {
                break;
            }
            do {
                id = mainView.inputStudentId();
                if (studentList.isDuplicateId(id)) {
                    mainView.displayMessage(Messages.ERR_DUP_STUDENT_ID);
                }
            } while (studentList.isDuplicateId(id));
            name = mainView.inputName();
            semester = mainView.inputSemester();
            course = mainView.inputCourse();
            student = new Student(id, name, semester, course);
            studentList.addStudent(student);
            mainView.displayMessage(Messages.MSG_STUDENT_ADDED_SUCCESS);
        }
    }

    /**
     * Tìm kiếm sinh viên theo tên và hiển thị kết quả đã sắp xếp.
     */
    public void findAndSort() {
        String name;
        ArrayList<Student> resultList;
        if (studentList.isEmpty()) {
            mainView.displayMessage(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }
        name = mainView.inputName();
        resultList = studentList.searchAndSortByName(name);
        if (resultList.isEmpty()) {
            mainView.displayMessage(Messages.ERR_NO_STUDENTS_FOUND);
            return;
        }
        mainView.displaySearchResults(resultList);
    }

    /**
     * Cập nhật hoặc xóa sinh viên theo mã định danh.
     */
    public void updateOrDelete() {
        String id;
        String name;
        String semester;
        String course;
        String choice;
        Student student;
        if (studentList.isEmpty()) {
            mainView.displayMessage(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }
        id = mainView.inputStudentId();
        student = studentList.getStudentById(id);
        if (student == null) {
            mainView.displayMessage(Messages.ERR_STUDENT_NOT_FOUND);
            return;
        }
        mainView.displayFoundStudent(student);
        choice = mainView.inputUpdateOrDeleteChoice();
        if (choice.equalsIgnoreCase("D")) {
            studentList.removeStudentById(id);
            mainView.displayMessage(Messages.MSG_DELETED_SUCCESS);
            return;
        }
        name = mainView.inputName();
        semester = mainView.inputSemester();
        course = mainView.inputCourse();
        studentList.updateStudent(id, name, semester, course);
        mainView.displayMessage(Messages.MSG_UPDATED_SUCCESS);
    }

    /**
     * Tạo và hiển thị báo cáo thống kê sinh viên.
     */
    public void report() {
        ArrayList<ReportEntry> reportList;
        if (studentList.isEmpty()) {
            mainView.displayMessage(Messages.ERR_NO_STUDENTS_ADDED);
            return;
        }
        reportList = studentList.buildReport();
        mainView.displayReport(reportList);
    }
}
