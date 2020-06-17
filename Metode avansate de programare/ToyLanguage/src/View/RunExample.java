package View;

import Controller.Controller;
import Model.Exceptions.MyException;

import java.io.IOException;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr = ctr;
    }
    @Override
    public void execute() {
        try{
            this.ctr.allStep();
        }
        catch (MyException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
