package main.code.storage;

import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractArrayStorageTest {

    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";

    private Storage storage;

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @After
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void save() {
        storage.save(new Resume("NEW_UUID"));
        assertEquals(4, storage.size());
    }

    @Test
    public void get() {
        assertEquals(UUID_1, storage.get(UUID_1).getUuid());
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1));
        assertEquals(3, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        assertNotNull(resumes[0]);
        assertNotNull(resumes[1]);
        assertNotNull(resumes[2]);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test(expected = ResumeNotExistInStorageException.class)
    public void resumeNotExist() {
        storage.get("resume_not_exist_uuid");
    }
}