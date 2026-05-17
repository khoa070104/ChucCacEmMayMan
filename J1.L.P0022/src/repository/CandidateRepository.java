package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Candidate;
import utils.FileUtils;

/**
 * Data access layer – stores candidates in memory and file.
 */
public class CandidateRepository {

    private static final String DATA_FILE = "data/candidates.txt";
    private final ArrayList<Candidate> candidates = new ArrayList<>();

    public CandidateRepository() {
        try {
            FileUtils.loadCandidates(DATA_FILE, candidates);
        } catch (IOException e) {
            System.out.println("Could not load candidates file: " + e.getMessage());
        }
    }

    public void add(Candidate candidate) throws IOException {
        candidates.add(candidate);
        FileUtils.saveCandidate(DATA_FILE, candidate);
    }

    public List<Candidate> findAll() {
        return new ArrayList<>(candidates);
    }

    public List<Candidate> findByNameAndType(String name, int type) {
        List<Candidate> result = new ArrayList<>();
        String keyword = name.trim().toLowerCase();
        for (Candidate c : candidates) {
            if (c.getCandidateType() != type) {
                continue;
            }
            if (c.getFirstName().toLowerCase().contains(keyword)
                    || c.getLastName().toLowerCase().contains(keyword)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Candidate> findByType(int type) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate c : candidates) {
            if (c.getCandidateType() == type) {
                result.add(c);
            }
        }
        return result;
    }

    public boolean isEmpty() {
        return candidates.isEmpty();
    }
}
