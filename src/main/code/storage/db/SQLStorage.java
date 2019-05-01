package main.code.storage.db;

import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.Resume;
import main.code.sql.SQLHelper;
import main.code.storage.Storage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {

    private SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", preparedStatement -> {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.setString(2, resume.getFullName());
            preparedStatement.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new ResumeNotExistInStorageException(uuid);
            }
            return new Resume(uuid, resultSet.getString("full_name"));
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.execute("UPDATE resume SET full_name = ? where uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, resume.getFullName());
            preparedStatement.setString(2, resume.getUuid());
            final int count = preparedStatement.executeUpdate();
            if (count == 0) {
                throw new ResumeNotExistInStorageException(resume.getUuid());
            }
            return count;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            final int count = preparedStatement.executeUpdate();
            if (count == 0) {
                throw new ResumeNotExistInStorageException(uuid);
            }
            return count;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name, uuid", preparedStatement -> {
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (resultSet.next()) {
                resumes.add(new Resume(resultSet.getString("uuid"), resultSet.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", preparedStatement -> {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }
}
