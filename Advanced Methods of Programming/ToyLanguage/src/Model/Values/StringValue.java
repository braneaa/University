package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value {

    private String string;

    public String getVal(){
        return string;
    }

    public StringValue(String str){
        this.string = str;
    }

    @Override
    public String toString(){
        return string;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
