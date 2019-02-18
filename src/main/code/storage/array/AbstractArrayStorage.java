package main.code.storage.array;

import main.code.exception.array.StorageIsFullException;
import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.*;

abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    static final int MAX_SIZE = 1000;
    static final int NOT_EXIST_INDEX = -1;

    Resume[] storage = new Resume[MAX_SIZE];
    int size = 0;

    abstract void insert(Integer key, Resume resume);

    abstract void fillEmptyCell(Integer key);

    @Override
    public void doSave(Integer key, Resume resume) {
        if (arrayIsFull()) throw new StorageIsFullException(resume.getUuid());
        insert(key, resume);
        size++;
    }

    @Override
    public Resume doGet(Integer key) {
        return storage[key];
    }

    @Override
    public void doUpdate(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    public void doDelete(Integer key) {
        fillEmptyCell(key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected List<Resume> doCopyAll() {
        return asList(stream(storage)
                .filter(Objects::nonNull)
                .toArray(Resume[]::new));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key > NOT_EXIST_INDEX;
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