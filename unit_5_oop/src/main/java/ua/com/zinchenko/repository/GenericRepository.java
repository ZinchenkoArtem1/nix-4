package ua.com.zinchenko.repository;

import ua.com.zinchenko.entity.Author;
import ua.com.zinchenko.entity.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface GenericRepository<TEntity extends BaseEntity<TKey>, TKey> {
    Optional<TEntity> read(TKey id);
    List<TEntity> readAll();
    void update(TEntity entity);
    void remove(TEntity entity);
    void create(TEntity entity);
    List<TEntity> findBy(Predicate<TEntity> predicate);

}
