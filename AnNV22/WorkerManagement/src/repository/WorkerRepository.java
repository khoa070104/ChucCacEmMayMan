package repository;

//import dto.SalaryHistoryResponseDTO;
import constants.SalaryStatus;
import dto.WorkerRequestDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.SalaryHistory;
import model.Worker;

public class WorkerRepository {

    // Khai bao bien luu tru 
    private Map<String, Worker> workerMap = new HashMap<>();

    //Constructor
    public WorkerRepository() {
    }

    // Luu doi tuong Worker vao bien luu tru workerMap
    public void save(WorkerRequestDTO dto) {
        Worker worker = new Worker(dto.getId(), dto.getName(), dto.getAge(), dto.getSalary(), dto.getLocation());
        
        //OPTIONAL : thay co the hoi ve viec them bang luong moi tao
        this.addInitialSalaryHistory(worker);

        this.workerMap.put(worker.getId(), worker);

    }

    private void addInitialSalaryHistory(Worker worker) {
        // Vì cậu muốn giữ nguyên Enum nên tớ dùng UP cho mức lương khởi điểm luôn nhé
        SalaryHistory initialHistory = new SalaryHistory(worker.getCurrentSalary(), SalaryStatus.UP, LocalDate.now());
        worker.addHistory(initialHistory);
    }

    // Tra ve id cua Worker khi tim duoc trong workerMap
    public Worker findById(String workerId) {
        return this.workerMap.get(workerId);
    }

    // Kiem tra id da co san trong workerMap
    public boolean exists(String workerId) {
        return this.workerMap.containsKey(workerId);
    }

    // Kiem tra map co dang trong workerMap khong
    public boolean isEmpty() {
        return this.workerMap.isEmpty();
    }

    // Tra ve tat ca doi tuong Worker
    public List<Worker> findAll() {
        return new ArrayList<>(this.workerMap.values());
    }

}
