package model;

/**
 * Intern candidate (type 2).
 */
public class InternCandidate extends Candidate {

    private String majors;
    private String semester;
    private String universityName;

    public InternCandidate() {
        this.candidateType = 2;
    }

    public InternCandidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            String majors, String semester, String universityName) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 2);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Majors: %s | Semester: %s | University: %s",
                majors, semester, universityName);
    }
}
