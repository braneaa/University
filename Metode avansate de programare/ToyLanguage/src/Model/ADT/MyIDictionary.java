package Model.ADT;


import Model.Exceptions.MyException;

import java.util.Map;

public interface MyIDictionary<K, V> {
    public V search(K key) throws MyException;
    boolean isDefined(K key);
    void update(K key, V value);
    void delete(K key);
    public Map getContent();
    public MyIDictionary<K,V> clone();

    @Override
    String toString();
}
