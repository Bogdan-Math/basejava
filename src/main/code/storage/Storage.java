package main.code.storage;

import main.code.model.Resume;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/**
 * Array based main.code.storage for Resumes
 */
public interface Storage {

    Comparator<Resume> RESUME_COMPARATOR = comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    void clear();

    void save(Resume resume);

    Resume get(String uuid);

    void update(Resume resume);

    void delete(String uuid);

    List<Resume> getAllSorted();

    int size();
}
