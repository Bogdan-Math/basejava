package main.code.storage.disk.file;

import main.code.storage.AbstractStorageTest;
import main.code.storage.disk.FileStorage;
import main.code.storage.disk.strategy.XMLStreamSerializerStrategy;

import java.io.File;

public class ByXMLStreamSerializerStrategyTest extends AbstractStorageTest {

    public ByXMLStreamSerializerStrategyTest() {
        super(new FileStorage(
                new File("D:\\Projects\\Java\\basejava\\storage"),
                new XMLStreamSerializerStrategy()
        ));
    }
}
