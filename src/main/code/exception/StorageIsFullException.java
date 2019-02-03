package main.code.exception;

public class StorageIsFullException extends AbstractStorageException {

    public StorageIsFullException(String uuid) {
        super("Storage IS FULL. Resume UUID: " + uuid);
    }
}
