package main.code.storage.disk.path;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.DataStreamSerializerStrategy;

import java.nio.file.Paths;

public class ByDataStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByDataStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get(PATH_NAME),
                new DataStreamSerializerStrategy()
        ));
    }

}
