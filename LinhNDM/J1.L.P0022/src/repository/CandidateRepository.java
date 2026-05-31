package repository;

import common.Constants;
import common.Messages;
import java.io.IOException;
import model.Candidate;
import model.Candidates;
import utils.FileUtils;

/**
 * Truy cập dữ liệu ứng viên từ file.
 *
 * @author LinhNDM
 */
public class CandidateRepository {

    /**
     * Nạp danh sách ứng viên từ file vào model.
     *
     * @param candidateList danh sách ứng viên cần nạp dữ liệu
     */
    public void loadInto(Candidates candidateList) {
        try {
            FileUtils.loadCandidates(Constants.DATA_FILE, candidateList);
        } catch (IOException exception) {
            System.out.println(Messages.ERR_LOAD_CANDIDATE_FILE
                    + exception.getMessage());
        }
    }

    /**
     * Lưu một ứng viên mới vào file.
     *
     * @param candidate ứng viên cần lưu
     * @throws IOException khi ghi file thất bại
     */
    public void saveCandidate(Candidate candidate) throws IOException {
        FileUtils.saveCandidate(Constants.DATA_FILE, candidate);
    }
}
