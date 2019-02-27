package main.code.storage.file;

import main.code.exception.file.IOStorageException;
import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        requireNonNull(directory);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is NOT directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is NOT readable/writable.");
        }
        this.directory = directory;
    }

    abstract void doWrite(File key, Resume resume) throws IOException;

    @Override
    protected File getKeyOf(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File key) {
        return key.exists();
    }

    @Override
    protected void doSave(File key, Resume resume) {
        try {
            key.createNewFile();
            doWrite(key, resume);
        } catch (IOException e) {
            throw new IOStorageException(key.getName() + ": " + e.getMessage());
        }
    }

    @Override
    protected Resume doGet(File key) {
        return null;
    }

    @Override
    protected void doUpdate(File key, Resume resume) {

    }

    @Override
    protected void doDelete(File key) {

    }

    @Override
    protected List<Resume> doCopyAll() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
