package main.code.storage.array;

import main.code.exception.array.StorageIsFullException;
import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

abstract class AbstractArrayStorage extends AbstractStorage {

    static final int MAX_SIZE = 1000;
    static final int NOT_EXIST_INDEX = -1;

    Resume[] storage = new Resume[MAX_SIZE];

    protected abstract void insert(Resume resume, Object key);

    protected abstract void fillEmptyCell(Object key);

    @Override
    public void doSave(Object key, Resume resume) {
        if (arrayIsFull()) throw new StorageIsFullException(resume.getUuid());
        insert(resume, key);
    }

    @Override
    public Resume doGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    public void doUpdate(Object key, Resume resume) {
        storage[(Integer) key] = resume;
    }

    @Override
    public void doDelete(Object key) {
        fillEmptyCell(key);
        storage[size - 1] = null;
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key > NOT_EXIST_INDEX;
    }

    @Override
    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    private boolean arrayIsFull() {
        return size >= storage.length;
    }
}