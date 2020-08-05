package Model.ADT;


import Model.Exceptions.MyException;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {

    private ArrayList<T> list;

    public MyList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean add(T v) {
        return list.add(v);
    }

    @Override
    public void remove(T v) throws MyException{
        if(!this.list.contains(v)){
            throw new MyException("Element does not exist!");
        }
        this.list.remove(v);
    }

    @Override
    public T get(int i) throws MyException{
        if(i < 0 || i > list.size()){
            throw new MyException("Index out of range");
        }
        return list.get(i);
    }

    @Override
    public String toString() {
        String result = "{";

        for (T el:this.list) {
            result += el.toString()+" ";
        }
        result+="}\n";
        return result.toString();    }
}
