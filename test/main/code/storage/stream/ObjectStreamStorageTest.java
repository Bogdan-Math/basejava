package main.code.storage.stream;

import main.code.storage.AbstractStorageTest;

import java.io.File;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(new File("D:\\Projects\\Java\\basejava\\storage")));
    }
}