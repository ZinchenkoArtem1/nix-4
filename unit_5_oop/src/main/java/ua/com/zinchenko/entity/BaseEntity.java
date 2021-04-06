package ua.com.zinchenko.entity;

public abstract class BaseEntity<TKey> {

    private final TKey id;

    public BaseEntity(TKey id) {
        this.id = id;
    }

    public TKey getId() {
        return id;
    }

}
