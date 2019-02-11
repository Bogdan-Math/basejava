package main.code.exception.array;

import main.code.exception.AbstractStorageException;

public class StorageIsFullException extends AbstractStorageException {

    public StorageIsFullException(String uuid) {
        super("Storage IS FULL. Resume UUID: " + uuid);
    }
}
