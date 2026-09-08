package repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import model.FeastMenu;

public class MenuRepository {
    private final Path menuFile;

    public MenuRepository(String fileName) {
        menuFile = Path.of(fileName);
    }

    public List<FeastMenu> load() throws IOException {
        List<FeastMenu> menus = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(menuFile, StandardCharsets.UTF_8)) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    line = line.replace("\uFEFF", "");
                    firstLine = false;
                    if (line.toLowerCase().startsWith("code,")) continue;
                }
                if (line.isBlank()) continue;
                List<String> fields = parseCsvLine(line);
                if (fields.size() < 4) continue;
                String priceText = fields.get(2).replaceAll("[^0-9.]", "");
                menus.add(new FeastMenu(fields.get(0).trim().toUpperCase(), fields.get(1).trim(),
                        new BigDecimal(priceText), fields.get(3).trim()));
            }
        }
        return menus;
    }

    private List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean quoted = false;
        for (int index = 0; index < line.length(); index++) {
            char character = line.charAt(index);
            if (character == '"') {
                if (quoted && index + 1 < line.length() && line.charAt(index + 1) == '"') {
                    field.append('"');
                    index++;
                } else {
                    quoted = !quoted;
                }
            } else if (character == ',' && !quoted) {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(character);
            }
        }
        fields.add(field.toString());
        return fields;
    }
}
