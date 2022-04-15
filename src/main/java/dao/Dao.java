package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Kulagin
 */
public interface Dao<T, ID> {
    T findById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    void save(T o) throws SQLException;

    void update(T o) throws SQLException;

    void delete(ID id) throws SQLException;
}
