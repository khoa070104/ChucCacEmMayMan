package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import model.Mountain;

public class Mountains extends java.util.ArrayList<Mountain> {

    private final String pathFile;

    public Mountains(String pathFile) {
        this.pathFile = pathFile;
    }

    public Mountain get(String mountainCode) {
        for (Mountain m : this) {
            if (m.getMountainCode().equalsIgnoreCase(mountainCode)) {
                return m;
            }
        }
        return null;
    }

    public boolean isValidMountainCode(String mountainCode) {
        return get(mountainCode) != null;
    }

    public Mountain dataToObject(String text) {
        String[] parts = text.split(",", 4);
        if (parts.length < 3) {
            return null;
        }
        String rawCode = parts[0].trim();
        String code = formatMountainCode(rawCode);
        String name = parts[1].trim();
        String province = parts[2].trim();
        String description = parts.length > 3 ? parts[3].trim() : "";
        return new Mountain(code, name, province, description);
    }

    public static String formatMountainCode(String rawCode) {
        int num = Integer.parseInt(rawCode.trim());
        return String.format("MT%02d", num);
    }

    public void readFromFile() {
        this.clear();
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("Mountain list file not found: " + pathFile);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Mountain mountain = dataToObject(line);
                if (mountain != null) {
                    add(mountain);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading mountain file: " + e.getMessage());
        }
    }
}
