package app.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(ID id);
}
