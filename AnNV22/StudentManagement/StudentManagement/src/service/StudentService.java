package service;

import constants.Message;
import dto.ReportResponseDTO;
import dto.StudentRequestDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import model.Student;
import repository.StudentRepository;

public class StudentService {

    // Goi Repository de thao tac du lieu
    private StudentRepository repository;

    // Constructor: khoi tao Repository
    public StudentService() {
        repository = new StudentRepository();
    }

    // tao sinh vien moi
    public boolean createStudent(StudentRequestDTO dto) throws Exception {

        validateRequest(dto, true);

        Student student = repository.findById(dto.getId());

        if (student != null) {
            repository.checkDuplicate(student, dto);
        }

        // Moi lan create them mot ban ghi Student day du vao ArrayList.
        repository.addStudent(new Student(
                dto.getId(), dto.getName(), dto.getSemester(), dto.getCourse()));
        return true;
    }
    //Cap nhat sinh vien
    public boolean updateStudent(String id, StudentRequestDTO dto) throws Exception {
        validateId(id);
        validateRequest(dto, false);
        dto.setId(id.trim());
        return repository.updateStudent(id.trim(), dto);
    }

    //Xoa sinh vien
    public boolean deleteStudent(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return repository.deleteStudent(id.trim());
    }

    //Tim kiem va sap xep sinh vien
    public List<ReportResponseDTO> findAndSort(String keyword) throws Exception {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new Exception(Message.EMPTY_INPUT);
        }
        String normalizedKeyword = keyword.trim().toLowerCase();
        // Danh sach tam de luu cac ban ghi tim duoc
        List<Student> filtered = new ArrayList<>();
        // Duyet toan bo danh sach sinh vien
        for (Student s : repository.getAllStudents()) {
            // Kiem tra ten co chua tu khoa hay khong
            if (s.getName().toLowerCase().contains(normalizedKeyword)) {
                filtered.add(s);
            }
        }
        // Neu khong tim thay sinh vien nao thi nem loi
        if (filtered.isEmpty()) {
            throw new Exception(Message.STUDENT_NOT_EXIST);
        }
        // Sap xep danh sach theo ten tang dan
        Collections.sort(filtered, new Comparator<Student>() {
            @Override
            public int compare(Student first, Student second) {
                return first.getName().compareToIgnoreCase(second.getName());
            }
        });
        // Danh sach ket qua tra ve cho Controller
        List<ReportResponseDTO> result = new ArrayList<>();
        // Chuyen doi du lieu tu Model sang DTO
        for (Student s : filtered) {
            result.add(new ReportResponseDTO(
                    s.getName(),
                    s.getSemester(),
                    s.getCourse().getDisplayName()));
        }
        return result;
    }

    //Bao cao sinh vien
    public List<ReportResponseDTO> report() {
        // Danh sach ket qua tra ve
        List<ReportResponseDTO> result = new ArrayList<>();
        // Duyet tung sinh vien
        Map<String, ReportResponseDTO> reportRows = new java.util.LinkedHashMap<>();
        for (Student student : repository.getAllStudents()) {
            String key = student.getId().toLowerCase() + "|" + student.getCourse().name();
            ReportResponseDTO current = reportRows.get(key);
            int total = current == null ? 1 : current.getTotal() + 1;
            reportRows.put(key, new ReportResponseDTO(
                    student.getName(), student.getCourse().getDisplayName(), total));
        }
        result.addAll(reportRows.values());
        return result;
    }

    // Kiem tra database co rong hay khong
    public boolean isEmpty() {
        return repository.isEmpty();
    }

    public boolean studentExists(String id) {
        return id != null && repository.findById(id) != null;
    }

    //Cho ra tong so hoc sinh da them vao
    public int getTotalStudents() {
        return repository.getAllStudents().size();
    }

    private void validateId(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception(Message.EMPTY_INPUT);
        }
    }

    private void validateRequest(StudentRequestDTO dto, boolean requireId) throws Exception {
        if (dto == null) {
            throw new Exception(Message.EMPTY_INPUT);
        }
        if (requireId) {
            validateId(dto.getId());
        }
        if (dto.getName() == null || dto.getName().trim().isEmpty()
                || dto.getSemester() == null || dto.getSemester().trim().isEmpty()) {
            throw new Exception(Message.EMPTY_INPUT);
        }
        if (dto.getCourse() == null) {
            throw new Exception(Message.INVALID_COURSE);
        }

        if (dto.getId() != null) {
            dto.setId(dto.getId().trim());
        }
        dto.setName(dto.getName().trim());
        dto.setSemester(dto.getSemester().trim());
    }
}
