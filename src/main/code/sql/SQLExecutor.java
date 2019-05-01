package main.code.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLExecutor<T> {
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
