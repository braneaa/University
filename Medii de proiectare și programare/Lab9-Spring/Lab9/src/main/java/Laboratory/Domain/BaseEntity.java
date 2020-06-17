package Laboratory.Domain;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity <ID extends Serializable> implements Serializable {

    @Id
    private ID id;

    public ID getId(){return this.id;}
    public void setId(ID i){this.id=i;}

    @Override
    public String toString()
    {
        return "BaseEntity{ " + "id= " + id + " }";
    }
}
