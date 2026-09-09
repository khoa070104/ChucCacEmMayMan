package DataObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final Path path;

    public FileManager(String fileName) {
        path = Paths.get(fileName);
    }

    public List<String> readDataFromFile() throws IOException {
        if (!Files.exists(path)) return new ArrayList<String>();
        return Files.readAllLines(path, StandardCharsets.UTF_8);
    }

    public void saveDataToFile(List<String> lines) throws IOException {
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
