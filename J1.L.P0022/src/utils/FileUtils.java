package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Candidate;
import model.ExperienceCandidate;
import model.FresherCandidate;
import model.InternCandidate;

/**
 * File read/write helpers for candidate persistence.
 */
public class FileUtils {

    private FileUtils() {
    }

    public static void ensureParentDir(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void saveCandidate(String filePath, Candidate candidate) throws IOException {
        ensureParentDir(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(serialize(candidate));
            writer.newLine();
        }
    }

    public static String serialize(Candidate c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c.getCandidateType()).append("|");
        sb.append(c.getCandidateId()).append("|");
        sb.append(c.getFirstName()).append("|");
        sb.append(c.getLastName()).append("|");
        sb.append(c.getBirthDate()).append("|");
        sb.append(c.getAddress()).append("|");
        sb.append(c.getPhone()).append("|");
        sb.append(c.getEmail()).append("|");

        if (c instanceof ExperienceCandidate) {
            ExperienceCandidate e = (ExperienceCandidate) c;
            sb.append(e.getExpInYear()).append("|").append(e.getProSkill());
        } else if (c instanceof FresherCandidate) {
            FresherCandidate f = (FresherCandidate) c;
            sb.append(f.getGraduationDate()).append("|")
                    .append(f.getGraduationRank()).append("|")
                    .append(f.getEducation());
        } else if (c instanceof InternCandidate) {
            InternCandidate i = (InternCandidate) c;
            sb.append(i.getMajors()).append("|")
                    .append(i.getSemester()).append("|")
                    .append(i.getUniversityName());
        }
        return sb.toString();
    }

    public static Candidate deserialize(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        String[] p = line.split("\\|", -1);
        int type = Integer.parseInt(p[0]);
        String id = p[1];
        String firstName = p[2];
        String lastName = p[3];
        int birthDate = Integer.parseInt(p[4]);
        String address = p[5];
        String phone = p[6];
        String email = p[7];

        switch (type) {
            case 0:
                return new ExperienceCandidate(id, firstName, lastName, birthDate,
                        address, phone, email, Integer.parseInt(p[8]), p[9]);
            case 1:
                return new FresherCandidate(id, firstName, lastName, birthDate,
                        address, phone, email, Integer.parseInt(p[8]), p[9], p[10]);
            case 2:
                return new InternCandidate(id, firstName, lastName, birthDate,
                        address, phone, email, p[8], p[9], p[10]);
            default:
                return null;
        }
    }

    public static void loadCandidates(String filePath, java.util.List<Candidate> target) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Candidate c = deserialize(line);
                if (c != null) {
                    target.add(c);
                }
            }
        }
    }
}
