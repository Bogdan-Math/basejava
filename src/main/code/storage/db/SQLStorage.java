package main.code.storage.db;

import main.code.exception.ResumeNotExistInStorageException;
import main.code.model.ContactType;
import main.code.model.Resume;
import main.code.model.Section;
import main.code.model.SectionType;
import main.code.sql.SQLHelper;
import main.code.storage.Storage;
import main.code.util.json.JSONParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLStorage implements Storage {

    private SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
            insertSections(connection, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transqctionalExecute(connection -> {
            Resume resume;
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid WHERE r.uuid = ?")) {
                preparedStatement.setString(1, uuid);
                final ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    throw new ResumeNotExistInStorageException(uuid);
                }
                resume = new Resume(uuid, resultSet.getString("full_name"));
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE  resume_uuid=?")) {
                preparedStatement.setString(1, uuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addContact(resultSet, resume);
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM section WHERE  resume_uuid=?")) {
                preparedStatement.setString(1, uuid);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addSection(resultSet, resume);
                }
            }

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
            deleteSections(connection, resume);
            insertContacts(connection, resume);
            insertSections(connection, resume);
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
        return sqlHelper.transqctionalExecute(connection -> {
            Map<String, Resume> resumes = new HashMap<>();

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, resultSet.getString("full_name")));
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addContact(resultSet, resumes.get(resultSet.getString("resume_uuid")));
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM section")) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    addSection(resultSet, resumes.get(resultSet.getString("resume_uuid")));
                }
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

    private void deleteContacts(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.execute();
        }
    }

    private void deleteSections(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            preparedStatement.setString(1, resume.getUuid());
            preparedStatement.execute();
        }
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

    private void insertSections(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
                preparedStatement.setString(1, resume.getUuid());
                preparedStatement.setString(2, entry.getKey().name());
                preparedStatement.setString(3, new JSONParser().writeObject(entry.getValue(), Section.class));
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

    private void addSection(ResultSet resultSet, Resume resume) throws SQLException {
        final String content = resultSet.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(resultSet.getString("type"));
            resume.addSection(type, new JSONParser().readObject(content, Section.class));
        }
    }
}
