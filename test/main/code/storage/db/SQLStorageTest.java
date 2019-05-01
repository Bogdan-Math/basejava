package main.code.storage.db;

import main.code.Config;
import main.code.storage.AbstractStorageTest;

public class SQLStorageTest extends AbstractStorageTest {

    public SQLStorageTest() {
        super(new SQLStorage(
                        Config.getInstance().getDbUrl(),
                        Config.getInstance().getDbUser(),
                        Config.getInstance().getDbPassword()
                )
        );
    }

}