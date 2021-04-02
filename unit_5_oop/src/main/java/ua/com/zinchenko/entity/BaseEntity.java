package ua.com.zinchenko.entity;

public abstract class BaseEntity<TKey> {

    private TKey id;

    public BaseEntity(TKey id) {
        this.id = id;
    }

    public TKey getId() {
        return id;
    }

    public void setId(TKey id) {
        this.id = id;
    }
}
