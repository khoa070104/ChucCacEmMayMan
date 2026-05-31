package model;

import java.util.ArrayList;

/**
 * Quản lý danh sách ứng viên và xử lý nghiệp vụ thêm, tìm kiếm.
 *
 * @author LinhNDM
 */
public class Candidates extends ArrayList<Candidate> {

    /**
     * Tìm ứng viên theo mã định danh.
     *
     * @param candidateId mã ứng viên cần tìm
     * @return ứng viên tìm thấy hoặc null nếu không tồn tại
     */
    public Candidate getCandidateById(String candidateId) {
        Candidate candidate;
        for (Candidate item : this) {
            if (item.getCandidateId().equalsIgnoreCase(candidateId)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Kiểm tra mã ứng viên đã tồn tại trong hệ thống hay chưa.
     *
     * @param candidateId mã ứng viên cần kiểm tra
     * @return true nếu trùng ID
     */
    public boolean isDuplicateId(String candidateId) {
        return getCandidateById(candidateId) != null;
    }

    /**
     * Thêm ứng viên mới sau khi kiểm tra trùng ID.
     *
     * @param candidate ứng viên cần thêm
     * @return true nếu thêm thành công
     */
    public boolean addCandidate(Candidate candidate) {
        if (isDuplicateId(candidate.getCandidateId())) {
            return false;
        }
        return add(candidate);
    }

    /**
     * Tìm kiếm ứng viên theo tên và loại.
     *
     * @param name từ khóa tên cần tìm
     * @param type loại ứng viên
     * @return danh sách ứng viên khớp điều kiện
     */
    public ArrayList<Candidate> searchByNameAndType(String name, int type) {
        ArrayList<Candidate> resultList;
        String keyword;
        Candidate candidate;
        resultList = new ArrayList<>();
        keyword = name.trim().toLowerCase();
        for (Candidate item : this) {
            candidate = item;
            if (candidate.getCandidateType() != type) {
                continue;
            }
            if (candidate.getFirstName().toLowerCase().contains(keyword)
                    || candidate.getLastName().toLowerCase().contains(keyword)) {
                resultList.add(candidate);
            }
        }
        return resultList;
    }

    /**
     * Lấy danh sách ứng viên theo loại.
     *
     * @param type loại ứng viên
     * @return danh sách ứng viên cùng loại
     */
    public ArrayList<Candidate> findByType(int type) {
        ArrayList<Candidate> resultList;
        Candidate candidate;
        resultList = new ArrayList<>();
        for (Candidate item : this) {
            candidate = item;
            if (candidate.getCandidateType() == type) {
                resultList.add(candidate);
            }
        }
        return resultList;
    }
}
