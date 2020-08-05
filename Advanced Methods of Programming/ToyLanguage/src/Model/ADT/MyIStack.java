package Model.ADT;

import Model.Exceptions.MyException;

public interface MyIStack<T> {
    T pop() throws MyException;
    void push(T v);
    boolean isEmpty();

    @Override
    String toString();
}


