package main.code.storage.array;

import main.code.exception.ResumeAlreadyExistInStorageException;
import main.code.exception.ResumeNotExistInStorageException;
import main.code.exception.StorageIsFullException;
import main.code.model.Resume;
import main.code.storage.Storage;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

abstract class AbstractArrayStorage implements Storage {

    static final int MAX_SIZE = 1000;
    static final int NOT_EXIST_INDEX = -1;

    Resume[] storage    = new Resume[MAX_SIZE];
    int size            = 0;

    abstract int indexOf(String uuid);

    abstract void insert(Resume resume, int index);

    abstract void fillEmptyCell(int index);

    @Override
    public void save(Resume resume) {

        if (storageIsFull()) {
            throw new StorageIsFullException(resume.getUuid());
        }

        int index = indexOf(resume.getUuid());
        if (isExist(index)) {
            throw new ResumeAlreadyExistInStorageException(resume.getUuid());
        } else {
            insert(resume, index);
            size++;
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (!isExist(index)) {
            throw new ResumeNotExistInStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (!isExist(index)) {
            throw new ResumeNotExistInStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (!isExist(index)) {
            throw new ResumeNotExistInStorageException(uuid);
        } else {
            fillEmptyCell(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    private boolean isExist(int index) {
        return index > NOT_EXIST_INDEX;
    }

    @Override
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean storageIsFull() {
        return size >= storage.length;
    }

}