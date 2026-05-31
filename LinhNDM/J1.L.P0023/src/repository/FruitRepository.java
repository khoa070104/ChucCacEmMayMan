package repository;

import common.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Fruit;
import utils.FileUtils;

/**
 * Lớp truy cập dữ liệu trái cây trong bộ nhớ và file.
 *
 * @author LinhNDM
 */
public class FruitRepository {

    private final ArrayList<Fruit> fruitList = new ArrayList<>();
    private String loadErrorMessage;

    /**
     * Khởi tạo repository và nạp dữ liệu từ file.
     */
    public FruitRepository() {
        try {
            FileUtils.loadFruits(Constants.DATA_FILE, fruitList);
        } catch (IOException exception) {
            loadErrorMessage = exception.getMessage();
        }
    }

    /**
     * Lấy thông báo lỗi khi nạp file (nếu có).
     *
     * @return thông báo lỗi hoặc null
     */
    public String getLoadErrorMessage() {
        return loadErrorMessage;
    }

    /**
     * Thêm trái cây mới và ghi xuống file.
     *
     * @param fruit trái cây cần thêm
     * @throws IOException khi ghi file thất bại
     */
    public void add(Fruit fruit) throws IOException {
        fruitList.add(fruit);
        FileUtils.saveFruit(Constants.DATA_FILE, fruit);
    }

    /**
     * Lấy toàn bộ trái cây.
     *
     * @return bản sao danh sách trái cây
     */
    public List<Fruit> findAll() {
        return new ArrayList<>(fruitList);
    }

    /**
     * Tìm trái cây theo chỉ số trong danh sách.
     *
     * @param index vị trí cần tìm
     * @return trái cây hoặc null nếu không tồn tại
     */
    public Fruit findByIndex(int index) {
        if (index < 0 || index >= fruitList.size()) {
            return null;
        }
        return fruitList.get(index);
    }

    /**
     * Kiểm tra danh sách trái cây có rỗng hay không.
     *
     * @return true nếu rỗng
     */
    public boolean isEmpty() {
        return fruitList.isEmpty();
    }

    /**
     * Lấy số lượng trái cây hiện có.
     *
     * @return kích thước danh sách
     */
    public int size() {
        return fruitList.size();
    }

    /**
     * Kiểm tra mã trái cây đã tồn tại hay chưa.
     *
     * @param fruitId mã cần kiểm tra
     * @return true nếu đã tồn tại
     */
    public boolean existsById(String fruitId) {
        for (Fruit fruit : fruitList) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                return true;
            }
        }
        return false;
    }
}
