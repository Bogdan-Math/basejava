package main.code.storage;

import main.code.model.Resume;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    int indexOf(String uuid) {
        Resume resume = new Resume(uuid);
        return binarySearch(storage, 0, size, resume);
    }

    @Override
    void insert(Resume resume, int index) {
        int headLastIndex = - index - 1;
        arraycopy(storage, headLastIndex, storage, headLastIndex + 1, size - headLastIndex);
        storage[headLastIndex] = resume;
    }

    @Override
    void fillEmptyCell(int index) {
        int emptyCellIndex = index + 1;
        arraycopy(storage, emptyCellIndex, storage, index, size - emptyCellIndex);
    }
}
