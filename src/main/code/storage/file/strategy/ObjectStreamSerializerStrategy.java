package main.code.storage.file.strategy;

import main.code.model.Resume;
import main.code.storage.file.strategy.StreamSerializerStrategy;

import java.io.*;

public class ObjectStreamSerializerStrategy implements StreamSerializerStrategy {

    @Override
    public Resume doRead(InputStream key) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(key)) {
            return (Resume) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doWrite(OutputStream key, Resume resume) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(key)) {
            objectOutputStream.writeObject(resume);
        }
    }

}