package main.code.storage;

import main.code.model.Resume;

/**
 * Array based main.code.storage for Resumes
 */
public interface Storage {

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    Resume[] getAll();

    int size();
}
