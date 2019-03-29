package main.code.storage.disk.file;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.JSONStreamSerializerStrategy;

import java.io.File;

public class ByJSONStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByJSONStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File(PATH_NAME),
                new JSONStreamSerializerStrategy()
        ));
    }

}