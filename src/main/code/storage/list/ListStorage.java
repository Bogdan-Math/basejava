package main.code.storage.list;

import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getKeyOf(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove(((Integer) key).intValue());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
