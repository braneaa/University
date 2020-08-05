package client.Provider;

public interface Provider<T> {
    void save(T DTO);
    void update(T DTO);
    T getByID(Long ID);
    void delete(Long ID);
}
