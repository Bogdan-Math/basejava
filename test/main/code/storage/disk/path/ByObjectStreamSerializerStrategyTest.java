package main.code.storage.disk.path;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.ObjectStreamSerializerStrategy;

import java.nio.file.Paths;

public class ByObjectStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByObjectStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get(PATH_NAME),
                new ObjectStreamSerializerStrategy()
        ));
    }

}