package model;

/**
 * Experience candidate (type 0).
 */
public class ExperienceCandidate extends Candidate {

    private int expInYear;
    private String proSkill;

    public ExperienceCandidate() {
        this.candidateType = 0;
    }

    public ExperienceCandidate(String candidateId, String firstName, String lastName,
            int birthDate, String address, String phone, String email,
            int expInYear, String proSkill) {
        super(candidateId, firstName, lastName, birthDate, address, phone, email, 0);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Exp: %d year(s) | Skill: %s", expInYear, proSkill);
    }
}
