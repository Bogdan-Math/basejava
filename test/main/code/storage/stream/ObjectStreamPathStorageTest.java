package main.code.storage.stream;

import main.code.storage.AbstractStorageTest;

import java.nio.file.Paths;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(Paths.get("D:\\Projects\\Java\\basejava\\storage")));
    }

}