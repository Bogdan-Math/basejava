package main.code.storage;

import main.code.storage.array.SimpleArrayStorageTest;
import main.code.storage.array.SortedArrayStorageTest;
import main.code.storage.list.ListStorageTest;
import main.code.storage.map.MapStorageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SimpleArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
})
public class AllStorageTest {
}
