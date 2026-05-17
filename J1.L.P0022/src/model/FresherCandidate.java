package model;

/**
 * Fresher candidate (type 1).
 */
public class FresherCandidate extends Candidate {

    private int graduationDate;
    private String graduationRank;
    private String education;

    public FresherCandidate() {
        this.candidateType = 1;
    }

    public FresherCandidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int graduationDate, String graduationRank, String education) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 1);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public int getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(int graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Grad: %d | Rank: %s | Education: %s",
                graduationDate, graduationRank, education);
    }
}
