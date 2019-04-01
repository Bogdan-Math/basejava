package main.code.storage.disk.file;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.DataStreamSerializerStrategy;

import java.io.File;

public class ByDataStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByDataStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File(PATH_NAME),
                new DataStreamSerializerStrategy()
        ));
    }

}
