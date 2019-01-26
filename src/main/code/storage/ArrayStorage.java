package main.code.storage;

import main.code.model.Resume;

import static java.lang.System.arraycopy;

/**
 * Array based main.code.storage for Resumes
 */
public class ArrayStorage {
    private static final int ELEMENT_NOT_EXIST_INDEX = -1;

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (!isExist(index)) {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        return null;
    }

    public void update(Resume resume) {

    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (isExist(index)) {
            int tail = size - index - 1;
            arraycopy(storage, index + 1, storage, index, tail);
            storage[size] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in main.code.storage (without null)
     */
    public Resume[] getAll() {
        Resume[] realResumes = new Resume[size];
        arraycopy(storage, 0, realResumes, 0, size);
        return realResumes;
    }

    public int size() {
        return size;
    }

    private boolean isExist(int currentIndex) {
        return currentIndex != ELEMENT_NOT_EXIST_INDEX;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return ELEMENT_NOT_EXIST_INDEX;
    }
}