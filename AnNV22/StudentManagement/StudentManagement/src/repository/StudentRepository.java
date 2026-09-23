package repository;

import constants.Message;
import dto.StudentRequestDTO;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentRepository {

    // danh sach luu tru toan bo sinh vien
    private List<Student> studentList;

    // khoi tao danh sach rong
    public StudentRepository() {
        studentList = new ArrayList<>();
    }

    // ================= ADD =================
    // ham nay chi lam nhiem vu them sinh vien, KHONG check trung
    // Trach nhiem 1: CHI them sinh vien moi vao danh sach
    public boolean addStudent(Student student) {
        return studentList.add(student);
    }

    // ================= UPDATE =================
    // cap nhat thong tin sinh vien va them course moi
    public boolean updateStudent(String id, StudentRequestDTO dto) throws Exception {

        // tim sinh vien theo id
        Student student = findById(id);

        // neu khong ton tai thi khong update duoc
        if (student == null) {
            throw new Exception(Message.STUDENT_NOT_EXIST);
        }

        // Khong cho update thanh mot ban ghi da ton tai.
        checkDuplicateForUpdate(student, dto);
        boolean sameName = student.getName().equalsIgnoreCase(dto.getName());
        boolean sameSemester = student.getSemester().equalsIgnoreCase(dto.getSemester());
        boolean sameCourse = student.getCourse() == dto.getCourse();
        if (sameName && sameSemester && sameCourse) {
            throw new Exception(Message.NOTHING_CHANGE);
        }

        // Cac ban ghi cung ID phai luon co cung ten sinh vien.
        for (Student current : studentList) {
            if (current.getId().equalsIgnoreCase(id)) {
                current.updateInfo(
                        dto.getName(), current.getSemester(), current.getCourse());
            }
        }

        // Update thay the semester/course cua ban ghi tim thay, khong them ban ghi moi.
        student.updateInfo(dto.getName(), dto.getSemester(), dto.getCourse());

        return true;
    }

    // ================= CHECK DUPLICATE =================
    // ham nay chi chiu trach nhiem kiem tra trung du lieu
    public void checkDuplicate(Student student, StudentRequestDTO dto) throws Exception {

        // neu id trung nhung ten khac -> loi
        if (!student.getName().equalsIgnoreCase(dto.getName())) {
            throw new Exception(Message.DUPLICATE_ID);
        }

        if (isDuplicateRecord(null, dto)) {
            throw new Exception(Message.DUPLICATE_COURSE);
        }
    }

    private void checkDuplicateForUpdate(Student target, StudentRequestDTO dto) throws Exception {
        if (isDuplicateRecord(target, dto)) {
            throw new Exception(Message.DUPLICATE_COURSE);
        }
    }

    private boolean isDuplicateRecord(Student ignoredStudent, StudentRequestDTO dto) {
        for (Student current : studentList) {
            if (current != ignoredStudent
                    && current.getId().equalsIgnoreCase(dto.getId())
                    && current.getSemester().equalsIgnoreCase(dto.getSemester())
                    && current.getCourse() == dto.getCourse()) {
                return true;
            }
        }
        return false;
    }

    // ================= DELETE =================
    // xoa sinh vien khoi danh sach
    public boolean deleteStudent(String id) {

        Student student = findById(id);

        // neu khong tim thay thi khong xoa duoc
        if (student == null) {
            return false;
        }

        studentList.remove(student);
        return true;
    }

    // ================= FIND =================
    // tim sinh vien theo id
    public Student findById(String id) {
        if (id == null) {
            return null;
        }
        String normalizedId = id.trim();
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(normalizedId)) {
                return s;
            }
        }
        return null;
    }

    // lay toan bo danh sach sinh vien
    public List<Student> getAllStudents() {
        return studentList;
    }

    // kiem tra danh sach co rong hay khong
    public boolean isEmpty() {
        return studentList.isEmpty();
    }
}
