package utils;

import common.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Candidate;
import model.ExperienceCandidate;
import model.FresherCandidate;
import model.InternCandidate;

/**
 * Tiện ích đọc và ghi file ứng viên.
 *
 * @author LinhNDM
 */
public final class FileUtils {

    private FileUtils() {
    }

    /**
     * Tạo thư mục cha và file nếu chưa tồn tại.
     *
     * @param filePath đường dẫn file
     * @throws IOException khi tạo file thất bại
     */
    public static void ensureParentDir(String filePath) throws IOException {
        File file;
        File parent;
        file = new File(filePath);
        parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Ghi thêm một ứng viên vào cuối file.
     *
     * @param filePath đường dẫn file
     * @param candidate ứng viên cần ghi
     * @throws IOException khi ghi file thất bại
     */
    public static void saveCandidate(String filePath, Candidate candidate)
            throws IOException {
        ensureParentDir(filePath);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {
            writer.write(serialize(candidate));
            writer.newLine();
        }
    }

    /**
     * Chuyển ứng viên thành chuỗi lưu file.
     *
     * @param candidate ứng viên cần serialize
     * @return chuỗi dữ liệu
     */
    public static String serialize(Candidate candidate) {
        StringBuilder builder;
        builder = new StringBuilder();
        builder.append(candidate.getCandidateType());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getCandidateId());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getFirstName());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getLastName());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getBirthDate());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getAddress());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getPhone());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getEmail());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        if (candidate instanceof ExperienceCandidate) {
            appendExperienceFields(builder, (ExperienceCandidate) candidate);
        } else if (candidate instanceof FresherCandidate) {
            appendFresherFields(builder, (FresherCandidate) candidate);
        } else if (candidate instanceof InternCandidate) {
            appendInternFields(builder, (InternCandidate) candidate);
        }
        return builder.toString();
    }

    /**
     * Đọc ứng viên từ một dòng file.
     *
     * @param line dòng dữ liệu
     * @return ứng viên hoặc null nếu dòng không hợp lệ
     */
    public static Candidate deserialize(String line) {
        String[] partArray;
        int type;
        String candidateId;
        String firstName;
        String lastName;
        int birthDate;
        String address;
        String phone;
        String email;
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        partArray = line.split("\\" + Constants.FILE_FIELD_SEPARATOR, -1);
        type = Integer.parseInt(partArray[0]);
        candidateId = partArray[1];
        firstName = partArray[2];
        lastName = partArray[3];
        birthDate = Integer.parseInt(partArray[4]);
        address = partArray[5];
        phone = partArray[6];
        email = partArray[7];
        switch (type) {
            case Constants.TYPE_EXPERIENCE:
                return new ExperienceCandidate(candidateId, firstName, lastName,
                        birthDate, address, phone, email,
                        Integer.parseInt(partArray[8]), partArray[9]);
            case Constants.TYPE_FRESHER:
                return new FresherCandidate(candidateId, firstName, lastName,
                        birthDate, address, phone, email,
                        Integer.parseInt(partArray[8]), partArray[9], partArray[10]);
            case Constants.TYPE_INTERN:
                return new InternCandidate(candidateId, firstName, lastName,
                        birthDate, address, phone, email,
                        partArray[8], partArray[9], partArray[10]);
            default:
                return null;
        }
    }

    /**
     * Nạp danh sách ứng viên từ file.
     *
     * @param filePath đường dẫn file
     * @param target danh sách đích
     * @throws IOException khi đọc file thất bại
     */
    public static void loadCandidates(String filePath, List<Candidate> target)
            throws IOException {
        File file;
        String line;
        Candidate candidate;
        file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                candidate = deserialize(line);
                if (candidate != null) {
                    target.add(candidate);
                }
            }
        }
    }

    /**
     * Nối thêm trường dữ liệu của ứng viên kinh nghiệm.
     *
     * @param builder StringBuilder đích
     * @param candidate ứng viên kinh nghiệm
     */
    private static void appendExperienceFields(StringBuilder builder,
            ExperienceCandidate candidate) {
        builder.append(candidate.getExpInYear());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getProSkill());
    }

    /**
     * Nối thêm trường dữ liệu của ứng viên fresher.
     *
     * @param builder StringBuilder đích
     * @param candidate ứng viên fresher
     */
    private static void appendFresherFields(StringBuilder builder,
            FresherCandidate candidate) {
        builder.append(candidate.getGraduationDate());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getGraduationRank());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getEducation());
    }

    /**
     * Nối thêm trường dữ liệu của ứng viên intern.
     *
     * @param builder StringBuilder đích
     * @param candidate ứng viên intern
     */
    private static void appendInternFields(StringBuilder builder,
            InternCandidate candidate) {
        builder.append(candidate.getMajors());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getSemester());
        builder.append(Constants.FILE_FIELD_SEPARATOR);
        builder.append(candidate.getUniversityName());
    }
}
