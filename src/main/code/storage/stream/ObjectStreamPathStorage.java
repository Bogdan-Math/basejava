package main.code.storage.stream;

import main.code.model.Resume;
import main.code.storage.file.AbstractPathStorage;

import java.io.*;
import java.nio.file.Path;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(Path directory) {
        super(directory);
    }

    @Override
    protected Resume doRead(InputStream key) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(key)) {
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doWrite(OutputStream key, Resume resume) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(key)) {
            objectOutputStream.writeObject(resume);
        }
    }

}