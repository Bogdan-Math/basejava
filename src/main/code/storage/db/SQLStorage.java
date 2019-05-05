package main.code.storage.db;

import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.ContactType;
import main.code.model.Resume;
import main.code.sql.SQLHelper;
import main.code.storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        sqlHelper.<Void>transqctionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, resume.getFullName());
                preparedStatement.execute();
            }
            insertContacts(connection, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) {
                        throw new ResumeNotExistInStorageException(uuid);
                    }
                    final Resume resume = new Resume(uuid, resultSet.getString("full_name"));
                    do {
                        addContact(resultSet, resume);
                    }
                    while (resultSet.next());
                    return resume;
                });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.<Void>transqctionalExecute(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE resume SET full_name = ? where uuid = ?")) {
                preparedStatement.setString(1, resume.getFullName());
                preparedStatement.setString(2, resume.getUuid());
                final int count = preparedStatement.executeUpdate();
                if (count == 0) {
                    throw new ResumeNotExistInStorageException(resume.getUuid());
                }
            }
            deleteContacts(connection, resume);
            insertContacts(connection, resume);
            return null;
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
        return sqlHelper.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid ORDER BY full_name, uuid",
                preparedStatement -> {
                    final ResultSet resultSet = preparedStatement.executeQuery();
                    Map<String, Resume> resumes = new HashMap<>();
                    while (resultSet.next()) {
                        final String uuid = resultSet.getString("uuid");
                        Resume resume = resumes.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, resultSet.getString("full_name"));
                            resumes.put(uuid, resume);
                        }
                        addContact(resultSet, resume);
                    }
                    return new ArrayList<>(resumes.values());
                });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", preparedStatement -> {
            final ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        });
    }

    private void deleteContacts(Connection connection, Resume resume) {
        sqlHelper.<Void>execute("DELETE FROM contact WHERE resume_uuid = ?", preparedStatement -> {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.execute();
            return null;
        });
    }

    private void insertContacts(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, entry.getKey().name());
                preparedStatement.setString(3, entry.getValue());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void addContact(ResultSet resultSet, Resume resume) throws SQLException {
        final String type = resultSet.getString("type");
        final String value = resultSet.getString("value");
        if (type != null && value != null) {
            resume.addContact(ContactType.valueOf(type), value);
        }
    }

}
