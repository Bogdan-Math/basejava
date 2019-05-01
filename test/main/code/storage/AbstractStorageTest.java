package main.code.storage;

import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractStorageTest {

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String FULL_NAME_1 = "FULL_NAME_1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_1);

    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String FULL_NAME_2 = "FULL_NAME_2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String FULL_NAME_3 = "FULL_NAME_3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    private static final String NEW_UUID = UUID.randomUUID().toString();
    private static final String NEW_FULL_NAME = "NEW_FULL_NAME";
    private static final Resume NEW_RESUME = new Resume(NEW_UUID, NEW_FULL_NAME);


    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {

/*
        RESUME_1.addContact(ContactType.MAIL, "mail1@ya.ru");
        RESUME_1.addContact(ContactType.PHONE, "11111");
        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        RESUME_1.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "JavaScript"));
        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        RESUME_2.addContact(ContactType.SKYPE, "skype2");
        RESUME_2.addContact(ContactType.PHONE, "22222");
        RESUME_2.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
*/

        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @After
    public void tearDown() {
        storage.clear();
    }

    @Test(expected = RuntimeException.class)
    public void saveResumeAlreadyExistInStorageException() {
        storage.save(RESUME_1);
    }

    @Test
    public void save() {
        storage.save(NEW_RESUME);
        assertStorageSize(4);
        assertEquals(NEW_RESUME, storage.get(NEW_UUID));
    }

    @Test(expected = ResumeNotExistInStorageException.class)
    public void resumeNotExistInStorageException() {
        storage.get(NEW_UUID);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = ResumeNotExistInStorageException.class)
    public void updateResumeNotExistInStorageException() {
        storage.update(NEW_RESUME);
    }

    @Test
    public void update() {
        storage.update(new Resume(UUID_1, NEW_FULL_NAME));
        assertStorageSize(3);
        assertEquals(RESUME_1.getUuid(), storage.get(UUID_1).getUuid());
    }

    @Test(expected = ResumeNotExistInStorageException.class)
    public void deleteResumeNotExistInStorageException() {
        storage.delete(NEW_UUID);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertStorageSize(2);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(3, resumes.size());
        assertNotNull(resumes.get(0));
        assertNotNull(resumes.get(1));
        assertNotNull(resumes.get(2));
    }

    @Test
    public void clear() {
        storage.clear();
        assertStorageSize(0);
    }

    @Test
    public void size() {
        assertStorageSize(3);
    }

    protected void assertStorageSize(int expected) {
        assertEquals(expected, storage.size());
    }

}
