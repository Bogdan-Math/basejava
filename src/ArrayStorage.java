import static java.lang.System.arraycopy;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int ELEMENT_NOT_EXIST_INDEX = -1;

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        int index = indexOf(resume.uuid);
        if (!isExist(index)) {
            storage[size] = resume;
            size++;
        }
    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        if (isExist(index)) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = indexOf(uuid);
        if (isExist(index)) {
            int tail = size - index - 1;
            arraycopy(storage, index + 1, storage, index, tail);
            storage[size] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] realResumes = new Resume[size];
        arraycopy(storage, 0, realResumes, 0, size);
        return realResumes;
    }

    int size() {
        return size;
    }

    private boolean isExist(int currentIndex) {
        return currentIndex != ELEMENT_NOT_EXIST_INDEX;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return ELEMENT_NOT_EXIST_INDEX;
    }
}