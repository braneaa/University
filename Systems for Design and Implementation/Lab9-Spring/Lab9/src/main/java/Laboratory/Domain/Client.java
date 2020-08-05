package Laboratory.Domain;


import javax.persistence.Entity;

@Entity
public class Client extends BaseEntity<Integer>{
    private int id2;
    private String name;
    private long phoneNumber;

    public Client(){}
    public Client(int i, String n, long pn){
        this.id2=i;
        this.name=n;
        this.phoneNumber=pn;
    }

    public int getID(){return id2;}
    public String getName(){return name;}
    public long getPhoneNumber(){return phoneNumber;}

    public void setID(int i){this.id2=i;}
    public void setName(String n){this.name=n;}
    public void setPhoneNumber(long pn){this.phoneNumber=pn;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if( o == null || o.getClass()!=this.getClass()) return false;
        Client client=(Client) o;

        if(id2 != client.id2)return false;
        if(phoneNumber != client.phoneNumber) return false;
        return name.equals(client.name);

    }

    @Override
    public String toString()
    {
        return "Client{" + "id: " + id2 + "\n" +
                " name: " + name + "\n" +
                " phone number: " + phoneNumber + "}" + super.toString();

    }

    @Override
    public int hashCode()
    {
        int result= id2;
        result = 31* result + name.hashCode();
        return result;
    }


}
