package main.code.storage.file;

import main.code.exception.file.IOStorageException;
import main.code.model.Resume;
import main.code.storage.AbstractStorage;
import main.code.storage.file.strategy.StreamSerializerStrategy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private StreamSerializerStrategy streamSerializerStrategy;

    public PathStorage(Path directory, StreamSerializerStrategy streamSerializerStrategy) {
        requireNonNull(directory);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is NOT directory.");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is NOT readable/writable.");
        }
        this.directory = directory;
        this.streamSerializerStrategy = streamSerializerStrategy;
    }

    @Override
    protected Path getKeyOf(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path key) {
        return Files.isRegularFile(key);
    }

    @Override
    protected void doSave(Path key, Resume resume) {
        try {
            Files.createFile(key);
        } catch (IOException e) {
            throw new IOStorageException(key + ": " + e.getMessage());
        }
        doUpdate(key, resume);
    }

    @Override
    protected Resume doGet(Path key) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(Files.newInputStream(key)));
        } catch (IOException e) {
            throw new IOStorageException("zxczczxc");
        }
    }

    @Override
    protected void doUpdate(Path key, Resume resume) {
        try {
            streamSerializerStrategy.doWrite(new BufferedOutputStream(Files.newOutputStream(key)), resume);
        } catch (IOException e) {
            throw new IOStorageException("ASDAS");
        }
    }

    @Override
    protected void doDelete(Path key) {
        try {
            Files.delete(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        try {
            return Files.list(directory).map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try {
            return Math.toIntExact(Files.list(directory).count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
