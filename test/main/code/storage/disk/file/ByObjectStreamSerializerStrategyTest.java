package main.code.storage.disk.file;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.ObjectStreamSerializerStrategy;

import java.io.File;

public class ByObjectStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByObjectStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File(PATH_NAME),
                new ObjectStreamSerializerStrategy()
        ));
    }

}