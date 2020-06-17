package Model.ADT;

import Model.Exceptions.MyException;

public interface MyIList<T> {
    int size();
    boolean isEmpty();
    boolean add(T v);
    void remove(T v) throws MyException;
    T get(int i) throws MyException;

    @Override
    String toString();
}
