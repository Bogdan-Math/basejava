package main.code.exception;

abstract class AbstractStorageException extends RuntimeException {

    AbstractStorageException(String description) {
        super(description);
    }
}
