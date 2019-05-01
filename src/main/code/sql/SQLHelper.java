package main.code.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {

    private ConnectionFactory connectionFactory;

    public SQLHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, PreparedStatement::execute);
    }

    public <T> T execute(String sql, SQLExecutor<T> sqlExecutor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return sqlExecutor.execute(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
