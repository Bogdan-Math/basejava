package main.code.storage.disk.file;

import main.code.storage.AbstractStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.ObjectStreamSerializerStrategy;

import java.io.File;

public class StorageByObjectStreamSerializerStrategyTest extends AbstractStorageTest {

    public StorageByObjectStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File("D:\\Projects\\Java\\basejava\\storage"),
                new ObjectStreamSerializerStrategy()
        ));
    }

}