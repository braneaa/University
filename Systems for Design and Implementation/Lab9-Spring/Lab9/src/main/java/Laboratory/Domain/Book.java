package Laboratory.Domain;


import javax.persistence.Entity;

@Entity
public class Book extends BaseEntity<Integer>{
    private String serialNumber;
    private String name;
    private String genre;
    private String author;

    public Book(){}
    public Book(String sn, String n, String g, String a){
        this.serialNumber=sn;
        this.name=n;
        this.genre=a;
        this.author=g;
    }

    public String getSerialNumber(){return serialNumber;}
    public String getName(){return name;}
    public String getGenre(){return genre;}
    public String getAuthor(){return author;}

    public void setSerialNumber(String sn){this.serialNumber=sn;}
    public void setName(String n){this.name=n;}
    public void setGenre(String g){this.genre=g;}
    public void setAuthor(String a){this.author=a;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if( o == null || o.getClass()!=this.getClass()) return false;

        Book book=(Book) o;
        if(!serialNumber.equals(book.serialNumber)) return false;
        if(!genre.equals(book.genre))return false;
        if(!author.equals(book.author))return false;
        return name.equals(book.name);

    }

    @Override
    public String toString()
    {
        return "Book{ " + "serialNumber: " + serialNumber + '\n'+
                " name: " + name + '\n' +
                " author " + author + '\n' +
                " genre: " + genre + "}" + super.toString();
    }

    @Override
    public int hashCode()
    {
        int result= serialNumber.hashCode();
        result = 31* result + name.hashCode();
        result= 31 * result + genre.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

}
