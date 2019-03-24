package main.code.storage.file;

import main.code.storage.AbstractStorageTest;
import main.code.storage.file.strategy.ObjectStreamSerializerStrategy;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(
                new File("D:\\Projects\\Java\\basejava\\storage"),
                new ObjectStreamSerializerStrategy()
        ));
    }

}