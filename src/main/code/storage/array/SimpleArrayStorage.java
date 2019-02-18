package main.code.storage.array;

import main.code.model.Resume;

public class SimpleArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKeyOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return NOT_EXIST_INDEX;
    }

    @Override
    protected void insert(Integer key, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void fillEmptyCell(Integer key) {
        storage[key] = storage[size - 1];
    }
}