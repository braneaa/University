package Model.Values;

import Model.Types.RefType;
import Model.Types.Type;

public class RefValue implements Value {

    private int address;
    private Type locationType;

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    public Type getLocationType() {
        return locationType;
    }

    public int getAddress() {
        return address;
    }

    public String toString(){
        return this.locationType.toString();
    }
}
