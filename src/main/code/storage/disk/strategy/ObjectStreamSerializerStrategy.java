package main.code.storage.disk.strategy;

import main.code.model.Resume;

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