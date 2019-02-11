package main.code.exception;

public abstract class AbstractStorageException extends RuntimeException {

    protected AbstractStorageException(String description) {
        super(description);
    }
}
