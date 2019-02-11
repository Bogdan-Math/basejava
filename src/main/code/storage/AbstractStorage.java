package main.code.storage;

import main.code.exception.ResumeAlreadyExistInStorageException;
import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKeyOf(String uuid);

    protected abstract boolean isExist(Object key);

    protected abstract void doSave(Object key, Resume resume);

    protected abstract Resume doGet(Object key);

    protected abstract void doUpdate(Object key, Resume resume);

    protected abstract void doDelete(Object key);

    @Override
    public void save(Resume resume) {
        Object key = getNotExistedKeyOf(resume.getUuid());
        doSave(key, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getExistedKeyOf(uuid);
        return doGet(key);
    }

    @Override
    public void update(Resume resume) {
        Object key = getExistedKeyOf(resume.getUuid());
        doUpdate(key, resume);
    }

    @Override
    public void delete(String uuid) {
        Object key = getExistedKeyOf(uuid);
        doDelete(key);
    }

    private Object getExistedKeyOf(String key) {
        Object oKey = getKeyOf(key);
        if (!isExist(oKey)) throw new ResumeNotExistInStorageException(key);
        return oKey;
    }

    private Object getNotExistedKeyOf(String key) {
        Object oKey = getKeyOf(key);
        if (isExist(oKey)) throw new ResumeAlreadyExistInStorageException(key);
        return oKey;
    }

}
