package main.code.storage.file;

import main.code.storage.AbstractStorageTest;
import main.code.storage.file.strategy.ObjectStreamSerializerStrategy;

import java.nio.file.Paths;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(
                Paths.get("D:\\Projects\\Java\\basejava\\storage"),
                new ObjectStreamSerializerStrategy()
        ));
    }

}