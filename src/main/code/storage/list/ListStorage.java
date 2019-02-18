package main.code.storage.list;

import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    protected Integer getKeyOf(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected void doSave(Integer key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    protected void doUpdate(Integer key, Resume resume) {
        storage.set(key, resume);
    }

    @Override
    protected void doDelete(Integer key) {
        storage.remove((key).intValue());
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
