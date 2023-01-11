package pro.sky.recipeproject.service;

import org.springframework.stereotype.Service;
import pro.sky.recipeproject.model.FileServiceException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService {

    public void save(Path path, String json) {
        try {
            clear(path);
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new FileServiceException(e);
        }
    }

    public String read(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new FileServiceException(e);
        }
    }

    public void clear(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new FileServiceException(e);
        }
    }

    public File get(Path path) {
        return new File(path.toString());
    }
}
