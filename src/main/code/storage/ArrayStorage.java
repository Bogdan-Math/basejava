package main.code.storage;

import main.code.model.Resume;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based main.code.storage for Resumes
 */
public class ArrayStorage {

    private static final String RESUME_NOT_EXIST = "Resume NOT EXIST in the storage.";
    private static final String RESUME_IS_EXIST = "Resume already EXIST in the storage.";
    private static final String STORAGE_IS_FULL = "Storage is FULL.";

    private static final int NOT_EXIST_INDEX = -1;

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        fill(storage, null);
        size = 0;
    }

    public void save(Resume resume) {

        if (storageIsFull()) {
            System.out.println(STORAGE_IS_FULL);
            return;
        }

        if (isExist(indexOf(resume.getUuid()))) {
            System.out.println(RESUME_IS_EXIST);
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (!isExist(index)) {
            System.out.println(RESUME_NOT_EXIST);
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (!isExist(index)) {
            System.out.println(RESUME_NOT_EXIST);
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (!isExist(index)) {
            System.out.println(RESUME_NOT_EXIST);
        } else {
            int lastElementIndex = size - 1;
            storage[index] = storage[lastElementIndex];
            storage[lastElementIndex] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in main.code.storage (without null)
     */
    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private boolean isExist(int index) {
        return index != NOT_EXIST_INDEX;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return NOT_EXIST_INDEX;
    }

    private boolean storageIsFull() {
        return size >= storage.length;
    }
}