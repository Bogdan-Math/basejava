package main.code.storage;

import main.code.model.Resume;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

public class ArrayStorage implements Storage {

    private static final String RESUME_NOT_EXIST = "Resume NOT EXIST in the storage.";
    private static final String RESUME_IS_EXIST = "Resume already EXIST in the storage.";
    private static final String STORAGE_IS_FULL = "Storage is FULL.";

    private static final int STORAGE_LIMIT = 10000;
    private static final int NOT_EXIST_INDEX = -1;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
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

    @Override
    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (!isExist(index)) {
            System.out.println(RESUME_NOT_EXIST);
            return null;
        } else {
            return storage[index];
        }
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (!isExist(index)) {
            System.out.println(RESUME_NOT_EXIST);
        } else {
            storage[index] = resume;
        }
    }

    @Override
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

    @Override
    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    @Override
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
        return size >= STORAGE_LIMIT;
    }
}