package main.code.storage.disk.path;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.JSONStreamSerializerStrategy;

import java.nio.file.Paths;

public class ByJSONStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByJSONStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get(PATH_NAME),
                new JSONStreamSerializerStrategy()
        ));
    }

}