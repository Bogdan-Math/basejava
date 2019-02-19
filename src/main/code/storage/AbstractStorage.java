package main.code.storage;

import main.code.exception.ResumeAlreadyExistInStorageException;
import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.Resume;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<K> implements Storage {

    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract K getKeyOf(String uuid);

    protected abstract boolean isExist(K key);

    protected abstract void doSave(K key, Resume resume);

    protected abstract Resume doGet(K key);

    protected abstract void doUpdate(K key, Resume resume);

    protected abstract void doDelete(K key);

    protected abstract List<Resume> doCopyAll();

    @Override
    public void save(Resume resume) {
        LOGGER.info("save: " + resume);
        K key = getNotExistedKeyOf(resume.getUuid());
        doSave(key, resume);
    }

    @Override
    public Resume get(String uuid) {
        LOGGER.info("get: " + uuid);
        K key = getExistedKeyOf(uuid);
        return doGet(key);
    }

    @Override
    public void update(Resume resume) {
        LOGGER.info("update: " + resume);
        K key = getExistedKeyOf(resume.getUuid());
        doUpdate(key, resume);
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("delete: " + uuid);
        K key = getExistedKeyOf(uuid);
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> copyAll = doCopyAll();
        copyAll.sort(RESUME_COMPARATOR);
        return copyAll;
    }

    private K getExistedKeyOf(String key) {
        K oKey = getKeyOf(key);
        if (!isExist(oKey)) throw new ResumeNotExistInStorageException(key);
        return oKey;
    }

    private K getNotExistedKeyOf(String key) {
        K oKey = getKeyOf(key);
        if (isExist(oKey)) throw new ResumeAlreadyExistInStorageException(key);
        return oKey;
    }

}
