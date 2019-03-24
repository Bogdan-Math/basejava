package main.code.storage.disk.path;

import main.code.storage.AbstractStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.ObjectStreamSerializerStrategy;

import java.nio.file.Paths;

public class StorageByObjectStreamSerializerStrategyTest extends AbstractStorageTest {

    public StorageByObjectStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get("D:\\Projects\\Java\\basejava\\storage"),
                new ObjectStreamSerializerStrategy()
        ));
    }

}