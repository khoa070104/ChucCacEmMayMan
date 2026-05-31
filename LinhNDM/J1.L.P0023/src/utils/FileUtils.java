package utils;

import common.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Fruit;

/**
 * Tiện ích đọc/ghi file dữ liệu trái cây.
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
     * @throws IOException khi không tạo được file
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
     * Ghi thêm một trái cây vào cuối file.
     *
     * @param filePath đường dẫn file
     * @param fruit trái cây cần lưu
     * @throws IOException khi ghi file thất bại
     */
    public static void saveFruit(String filePath, Fruit fruit) throws IOException {
        ensureParentDir(filePath);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {
            writer.write(serialize(fruit));
            writer.newLine();
        }
    }

    /**
     * Chuyển đối tượng trái cây thành chuỗi lưu file.
     *
     * @param fruit trái cây cần serialize
     * @return chuỗi dữ liệu
     */
    public static String serialize(Fruit fruit) {
        StringBuilder lineBuilder;
        lineBuilder = new StringBuilder();
        lineBuilder.append(fruit.getFruitId());
        lineBuilder.append(Constants.FILE_FIELD_SEPARATOR);
        lineBuilder.append(fruit.getFruitName());
        lineBuilder.append(Constants.FILE_FIELD_SEPARATOR);
        lineBuilder.append(fruit.getPrice());
        lineBuilder.append(Constants.FILE_FIELD_SEPARATOR);
        lineBuilder.append(fruit.getQuantity());
        lineBuilder.append(Constants.FILE_FIELD_SEPARATOR);
        lineBuilder.append(fruit.getOrigin());
        return lineBuilder.toString();
    }

    /**
     * Chuyển một dòng file thành đối tượng trái cây.
     *
     * @param line dòng dữ liệu
     * @return trái cây hoặc null nếu dòng không hợp lệ
     */
    public static Fruit deserialize(String line) {
        String[] parts;
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        parts = line.split("\\|", -1);
        if (parts.length < 5) {
            return null;
        }
        return new Fruit(parts[0], parts[1],
                Double.parseDouble(parts[2]),
                Integer.parseInt(parts[3]),
                parts[4]);
    }

    /**
     * Đọc danh sách trái cây từ file vào bộ nhớ.
     *
     * @param filePath đường dẫn file
     * @param fruitList danh sách đích
     * @throws IOException khi đọc file thất bại
     */
    public static void loadFruits(String filePath,
            ArrayList<Fruit> fruitList) throws IOException {
        File file;
        String line;
        Fruit fruit;
        file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                fruit = deserialize(line);
                if (fruit != null) {
                    fruitList.add(fruit);
                }
            }
        }
    }
}
