package Laboratory.Domain;


import javax.persistence.Entity;

@Entity
public class SaleEntity extends BaseEntity<Integer> {
    private int buyid;
    private int bookid;
    private int clientid;

    public SaleEntity(){}

    public SaleEntity(int i, int b, int c) {
        this.buyid = i;
        this.bookid = b;
        this.clientid = c;
    }

    public int getBuyID() {
        return buyid;
    }

    public void setBuyID(int i) {
        this.buyid = i;
    }

    public int getBookID() {
        return bookid;
    }

    public void setBook(int b) {
        this.bookid = b;
    }

    public int getClientID() {
        return clientid;
    }

    public void setClient(int c) {
        this.clientid = c;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        SaleEntity by = (SaleEntity) o;

        if (buyid != by.buyid) return false;
        if (bookid != by.bookid) return false;
        return clientid==by.clientid;

    }

    @Override
    public String toString() {
        return "BookPurchase{" + "id " + this.getId() + "\n" + "Client ID: " + this.getClientID() + "\nBook ID: " + this.bookid + "}";
    }

    @Override
    public int hashCode() {
        int result = this.getId().hashCode();
        result = 31 * result + bookid;
        result = 31 * result + clientid;
        return result;
    }
}