package main.code.storage.map;

import main.code.model.Resume;
import main.code.storage.AbstractStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getKeyOf(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    protected void doSave(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.getOrDefault((String) key, null);
    }

    @Override
    protected void doUpdate(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((String) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.values().stream().sorted(RESUME_COMPARATOR).collect(toList());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
