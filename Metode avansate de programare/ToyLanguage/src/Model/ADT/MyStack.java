package Model.ADT;
import Model.Exceptions.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    @Override
    public T pop() throws MyException {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public  String toString(){
        String result = "{";
        for (T el:this.stack) {
            result += el.toString()+"|";
        }
        result+="}";
        return result.toString();
    }
}
