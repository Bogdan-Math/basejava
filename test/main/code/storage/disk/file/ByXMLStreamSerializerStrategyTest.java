package main.code.storage.disk.file;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.XMLStreamSerializerStrategy;

import java.io.File;

public class ByXMLStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByXMLStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File(PATH_NAME),
                new XMLStreamSerializerStrategy()
        ));
    }
}
