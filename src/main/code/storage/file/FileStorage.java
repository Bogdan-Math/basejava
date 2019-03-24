package main.code.storage.file;

import main.code.exception.file.IOStorageException;
import main.code.model.Resume;
import main.code.storage.AbstractStorage;
import main.code.storage.file.strategy.StreamSerializerStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class FileStorage extends AbstractStorage<File> {

    private File directory;
    private StreamSerializerStrategy streamSerializerStrategy;

    public FileStorage(File directory, StreamSerializerStrategy streamSerializerStrategy) {
        requireNonNull(directory);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is NOT directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is NOT readable/writable.");
        }
        this.directory = directory;
        this.streamSerializerStrategy = streamSerializerStrategy;
    }

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
        } catch (IOException e) {
            throw new IOStorageException(key.getName() + ": " + e.getMessage());
        }
        doUpdate(key, resume);
    }

    @Override
    protected Resume doGet(File key) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(new FileInputStream(key)));
        } catch (IOException e) {
            throw new IOStorageException("zxczczxc");
        }
    }

    @Override
    protected void doUpdate(File key, Resume resume) {
        try {
            streamSerializerStrategy.doWrite(new BufferedOutputStream(new FileOutputStream(key)), resume);
        } catch (IOException e) {
            throw new IOStorageException("ASDAS");
        }
    }

    @Override
    protected void doDelete(File key) {
        if (!key.delete()) {
            throw new IOStorageException(key.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new IOStorageException("bbbbbbb");
        }
        ArrayList<Resume> resumes = new ArrayList<>(files.length);
        for (File file : files) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] files = directory.list();
        if (files == null) {
            throw new IOStorageException("!!!!");
        }
        return files.length;
    }
}
