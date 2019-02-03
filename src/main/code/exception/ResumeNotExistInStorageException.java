package main.code.exception;

public class ResumeNotExistInStorageException extends AbstractStorageException {

    public ResumeNotExistInStorageException(String uuid) {
        super("Resume NOT EXIST in the storage. Resume UUID: " + uuid);
    }
}
