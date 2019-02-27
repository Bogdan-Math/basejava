package main.code.exception.file;

import main.code.exception.AbstractStorageException;

public class IOStorageException extends AbstractStorageException {

    public IOStorageException(String uuid) {
        super("IO storage exception: " + uuid);
    }
}
