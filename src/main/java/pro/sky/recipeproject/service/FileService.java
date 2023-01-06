package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    public boolean save(Path path, String json) {
        try {
            clear(path);
            Files.writeString(path, json);
            return true;
        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }

    public String read(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            e.getStackTrace();
            return null;
        }
    }

    public boolean clear(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
    }

    public File get(Path path) {
        return new File(path.toString());
    }
}
