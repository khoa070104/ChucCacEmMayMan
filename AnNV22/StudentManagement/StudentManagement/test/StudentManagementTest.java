import constants.CourseType;
import constants.Message;
import dto.ReportResponseDTO;
import dto.StudentRequestDTO;
import java.util.List;
import service.StudentService;
import utils.Validation;

/**
 * Bo test hoi quy cho cac chuc nang cua Student Management.
 */
public class StudentManagementTest {

    public static void main(String[] args) throws Exception {
        testValidation();
        testCreateAndDuplicateRules();
        testUpdateReplacesInsteadOfAdding();
        testUpdateDuplicateAndNoChange();
        testFindSortAndReport();
        testDeleteAndInvalidRequests();
        System.out.println("StudentManagementTest: PASSED");
    }

    private static void testValidation() throws Exception {
        assertEquals("trimmed string", "An Nguyen", Validation.getString("  An Nguyen  "));
        assertEquals("trimmed choice", 3, Validation.getChoice(" 3 ", 1, 5));
        assertEquals("java course", CourseType.JAVA, Validation.validateCourse(" java "));
        assertEquals("dot net course", CourseType.DOT_NET, Validation.validateCourse(" .net "));
        assertEquals("c/c++ course", CourseType.CPP, Validation.validateCourse(" C/C++ "));
        assertEquals("trimmed yes/no", "Y", Validation.validateYesNo(" y "));
        assertEquals("trimmed update/delete", "D", Validation.validateUpdateDelete(" d "));

        assertException(Message.EMPTY_INPUT, () -> Validation.getString(null));
        assertException(Message.EMPTY_INPUT, () -> Validation.validateCourse("   "));
        assertException(Message.INVALID_COURSE, () -> Validation.validateCourse("Python"));
        assertException(Message.INVALID_NUMBER, () -> Validation.getChoice(null, 1, 5));
        assertException(Message.INVALID_RANGE, () -> Validation.getChoice("6", 1, 5));
        assertException(Message.INVALID_YN, () -> Validation.validateYesNo("maybe"));
        assertException(Message.INVALID_UD, () -> Validation.validateUpdateDelete("X"));
    }

    private static void testCreateAndDuplicateRules() throws Exception {
        StudentService service = new StudentService();
        service.createStudent(dto("S01", "An Nguyen", "1", CourseType.JAVA));
        service.createStudent(dto("s01", "an nguyen", "1", CourseType.DOT_NET));

        List<ReportResponseDTO> courses = service.findAndSort("AN");
        assertEquals("new course is added to existing student", 2, courses.size());

        assertException(
                Message.DUPLICATE_COURSE,
                () -> service.createStudent(dto("S01", "An Nguyen", "1", CourseType.JAVA)));
        assertException(
                Message.DUPLICATE_ID,
                () -> service.createStudent(dto("S01", "Another Name", "2", CourseType.CPP)));
    }

    private static void testUpdateReplacesInsteadOfAdding() throws Exception {
        StudentService service = new StudentService();
        service.createStudent(dto("S01", "Old Name", "1", CourseType.JAVA));
        service.createStudent(dto("S01", "Old Name", "2", CourseType.DOT_NET));

        StudentRequestDTO update = dto(null, "New Name", "3", CourseType.CPP);
        service.updateStudent("  S01  ", update);

        List<ReportResponseDTO> courses = service.findAndSort("new name");
        assertEquals("update must not append a third course", 2, courses.size());
        assertEquals("updated semester", "3", courses.get(0).getSemester());
        assertEquals("updated course", "C/C++", courses.get(0).getCourse());
        assertEquals("second enrollment is preserved", ".Net", courses.get(1).getCourse());
    }

    private static void testUpdateDuplicateAndNoChange() throws Exception {
        StudentService service = new StudentService();
        service.createStudent(dto("S01", "An Nguyen", "1", CourseType.JAVA));
        service.createStudent(dto("S01", "An Nguyen", "2", CourseType.DOT_NET));

        assertException(
                Message.NOTHING_CHANGE,
                () -> service.updateStudent(
                        "S01", dto("S01", "An Nguyen", "1", CourseType.JAVA)));
        assertException(
                Message.DUPLICATE_COURSE,
                () -> service.updateStudent(
                        "S01", dto("S01", "An Nguyen", "2", CourseType.DOT_NET)));

        List<ReportResponseDTO> courses = service.findAndSort("An");
        assertEquals("failed update must keep course count", 2, courses.size());
        assertEquals("failed update must keep old semester", "1", courses.get(0).getSemester());
        assertEquals("failed update must keep old course", "Java", courses.get(0).getCourse());
    }

    private static void testFindSortAndReport() throws Exception {
        StudentService service = new StudentService();
        service.createStudent(dto("S02", "zach", "1", CourseType.JAVA));
        service.createStudent(dto("S01", "An", "1", CourseType.JAVA));
        service.createStudent(dto("S01", "An", "2", CourseType.JAVA));

        List<ReportResponseDTO> found = service.findAndSort("A");
        assertEquals("case-insensitive sort", "An", found.get(0).getStudentName());
        assertEquals("find result includes semester", "1", found.get(0).getSemester());

        List<ReportResponseDTO> report = service.report();
        ReportResponseDTO javaReport = findReport(report, "An", "Java");
        assertEquals("report aggregates same course", 2, javaReport.getTotal());

        assertException(Message.EMPTY_INPUT, () -> service.findAndSort("  "));
        assertException(Message.STUDENT_NOT_EXIST, () -> service.findAndSort("missing"));
    }

    private static void testDeleteAndInvalidRequests() throws Exception {
        StudentService service = new StudentService();
        service.createStudent(dto("S01", "An", "1", CourseType.JAVA));

        if (!service.deleteStudent("S01")) {
            throw new AssertionError("Existing student must be deleted.");
        }
        if (service.deleteStudent("S01") || service.deleteStudent(null)) {
            throw new AssertionError("Deleting a missing/invalid student must return false.");
        }
        if (!service.isEmpty()) {
            throw new AssertionError("Database must be empty after delete.");
        }

        assertException(
                Message.STUDENT_NOT_EXIST,
                () -> service.updateStudent("missing", dto("missing", "An", "1", CourseType.JAVA)));
        assertException(Message.EMPTY_INPUT, () -> service.createStudent(null));
        assertException(
                Message.EMPTY_INPUT,
                () -> service.createStudent(dto(" ", "An", "1", CourseType.JAVA)));
        assertException(
                Message.INVALID_COURSE,
                () -> service.createStudent(dto("S02", "An", "1", null)));

        StudentService multipleRecords = new StudentService();
        multipleRecords.createStudent(dto("S03", "Binh", "1", CourseType.JAVA));
        multipleRecords.createStudent(dto("S03", "Binh", "2", CourseType.DOT_NET));
        multipleRecords.deleteStudent("S03");
        assertEquals(
                "delete removes the record found by ID",
                1,
                multipleRecords.findAndSort("Binh").size());
    }

    private static StudentRequestDTO dto(
            String id, String name, String semester, CourseType course) {
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setSemester(semester);
        dto.setCourse(course);
        return dto;
    }

    private static ReportResponseDTO findReport(
            List<ReportResponseDTO> report, String studentName, String course) {
        for (ReportResponseDTO row : report) {
            if (row.getStudentName().equals(studentName) && row.getCourse().equals(course)) {
                return row;
            }
        }
        throw new AssertionError("Report row was not found.");
    }

    private static void assertEquals(String caseName, Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                    caseName + ": expected <" + expected + "> but was <" + actual + ">");
        }
    }

    private static void assertException(String expectedMessage, CheckedAction action) {
        try {
            action.run();
            throw new AssertionError("Expected exception: " + expectedMessage);
        } catch (Exception exception) {
            if (!expectedMessage.equals(exception.getMessage())) {
                throw new AssertionError(
                        "Expected exception <" + expectedMessage + "> but was <"
                                + exception.getMessage() + ">");
            }
        }
    }

    private interface CheckedAction {
        void run() throws Exception;
    }
}
