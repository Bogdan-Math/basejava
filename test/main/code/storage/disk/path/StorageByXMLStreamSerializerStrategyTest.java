package main.code.storage.disk.path;

import main.code.storage.AbstractStorageTest;
import main.code.storage.disk.PathStorage;
import main.code.storage.disk.strategy.XMLStreamSerializerStrategy;

import java.nio.file.Paths;

public class StorageByXMLStreamSerializerStrategyTest extends AbstractStorageTest {

    public StorageByXMLStreamSerializerStrategyTest() {
        super(new PathStorage(
                Paths.get("D:\\Projects\\Java\\basejava\\storage"),
                new XMLStreamSerializerStrategy()
        ));
    }

}