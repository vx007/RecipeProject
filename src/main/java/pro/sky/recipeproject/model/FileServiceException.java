package pro.sky.recipeproject.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileServiceException extends RuntimeException {
    public FileServiceException() {
        super();
    }

    public FileServiceException(String msg) {
        super(msg);
    }

    public FileServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public FileServiceException(Throwable t) {
        super(t);
    }
}
