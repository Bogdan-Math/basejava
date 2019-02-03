package main.code.exception;

public class ResumeAlreadyExistInStorageException extends AbstractStorageException {

    public ResumeAlreadyExistInStorageException(String uuid) {
        super("Resume ALREADY EXIST in the storage. Resume UUID: " + uuid);
    }
}
