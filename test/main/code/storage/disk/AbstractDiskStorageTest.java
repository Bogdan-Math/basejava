package main.code.storage.disk;

import main.code.storage.AbstractStorageTest;
import main.code.storage.Storage;

public abstract class AbstractDiskStorageTest extends AbstractStorageTest {

    protected static final String PATH_NAME = "storage";

    protected AbstractDiskStorageTest(Storage storage) {
        super(storage);
    }
}
