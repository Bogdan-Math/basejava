package main.code.storage;

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
}