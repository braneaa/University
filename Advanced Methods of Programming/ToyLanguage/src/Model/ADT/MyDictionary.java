package Model.ADT;

import Model.Exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {

    private HashMap<K,V> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<K,V>();
    }

    @Override
    public V search(K key) throws MyException {
        if(!dictionary.containsKey(key)){
            throw new MyException("Key does not exist!");
        }
        return dictionary.get(key);
    }

    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        dictionary.put(key,value);
    }

    @Override
    public void delete(K key) {
        dictionary.remove(key);
    }

    @Override
    public Map getContent() {
        return dictionary;
    }

    @Override
    public MyIDictionary<K,V> clone() {
        MyIDictionary<K,V> copy = new MyDictionary<>();
        for (K k : dictionary.keySet()) {
            copy.update(k,dictionary.get(k));
        }
        return copy;
    }

    @Override
    public String toString() {
        String result ="{";
        for (K key : dictionary.keySet())
            result+= key.toString() + " -> " + dictionary.get(key).toString() + ";";
        result+="}";
        return result;
    }
}
