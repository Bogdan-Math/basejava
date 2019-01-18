import static java.lang.String.format;
import static java.lang.System.arraycopy;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (isExists(resume.uuid)) {
            storage[indexOf(resume.uuid)] = resume;
        } else {
            storage[size] = resume;
            size++;
        }
    }

    Resume get(String uuid) {
        if (isExists(uuid)) {
            return storage[indexOf(uuid)];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        if (isExists(uuid)) {
            int index = indexOf(uuid);
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

    private boolean isExists(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return true;
            }
        }
        return false;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        throw new RuntimeException(format("Can't find index of required element. Resume with uuid=%s NOT exists in storage.", uuid));
    }
}