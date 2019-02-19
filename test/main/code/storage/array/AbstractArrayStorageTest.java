package main.code.storage.array;

import main.code.exception.array.StorageIsFullException;
import main.code.model.Resume;
import main.code.storage.AbstractStorageTest;
import main.code.storage.Storage;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveStorageIsFullException() {
        try {
            for (int i = storage.size(); i <= AbstractArrayStorage.MAX_SIZE; i++) {
                storage.save(new Resume("FULL_NAME_" + i));
            }
            fail();
        } catch (StorageIsFullException e) {
            assertStorageSize(AbstractArrayStorage.MAX_SIZE);
        }
    }

}