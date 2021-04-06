package ua.com.zinchenko.repository.impl;

import ua.com.zinchenko.db.DBInMemory;
import ua.com.zinchenko.entity.BaseEntity;
import ua.com.zinchenko.repository.GenericRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericRepositoryImpl<TEntity extends BaseEntity<TKey>, TKey>
        implements GenericRepository<TEntity, TKey> {

    protected final List<TEntity> entities;
    private final Class<TEntity> clazz;

    public GenericRepositoryImpl(Class<TEntity> clazz) {
        this.clazz = clazz;
        DBInMemory dbInMemory = DBInMemory.getInstance();
        entities = dbInMemory.getEntities(clazz);
    }

    @Override
    public Optional<TEntity> read(TKey id) {
        return entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
    }

    @Override
    public List<TEntity> readAll() {
        return entities;
    }

    @Override
    public void update(TEntity entity) {
        TEntity currentEntity = read(entity.getId()).orElse(null);
        if(currentEntity == null) {
            throw new RuntimeException("Dont have " + clazz.getName() +
                    " with id: " + entity.getId());
        } else {
            entities.add(entities.indexOf(currentEntity), entity);
        }
    }

    @Override
    public void remove(TEntity entity) {
        TEntity currentEntity = read(entity.getId()).orElse(null);
        if(currentEntity == null) {
            throw new RuntimeException("Dont have " + clazz.getName() +
                    " with id: " + entity.getId());
        } else {
            entities.remove(currentEntity);
        }
    }

    @Override
    public void create(TEntity entity) {
        entities.add(entity);
    }

    @Override
    public List<TEntity> findBy(Predicate<TEntity> predicate) {
        return entities.stream().filter(predicate).collect(Collectors.toList());
    }
}
