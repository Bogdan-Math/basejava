package main.code.storage.disk.path;

import main.code.storage.disk.AbstractDiskStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.XMLStreamSerializerStrategy;

import java.nio.file.Paths;

public class ByXMLStreamSerializerStrategyTest extends AbstractDiskStorageTest {

    public ByXMLStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get(PATH_NAME),
                new XMLStreamSerializerStrategy()
        ));
    }

}