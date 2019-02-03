package main.code.storage.array;

import main.code.model.Resume;

public class SimpleArrayStorage extends AbstractArrayStorage {

    @Override
    int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return NOT_EXIST_INDEX;
    }

    @Override
    void insert(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    void fillEmptyCell(int index) {
        storage[index] = storage[size - 1];
    }

}