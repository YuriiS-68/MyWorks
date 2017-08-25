package lesson20.task2;

import java.util.Date;

public class Transaction {

    private long id;
    private String city;
    private int amount;
    private String description;
    private TransactionType type;
    private Date dateCreated;

    public Transaction(long id, String city, int amount, String description, TransactionType type, Date dateCreated)throws InternalServerException {
        this.id = id;
        this.city = city;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (!city.equals(that.city)) return false;
        if (!description.equals(that.description)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + city.hashCode();
        result = 31 * result + amount;
        result = 31 * result + description.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id - " + id +
                ", city - '" + city + '\'' +
                ", amount - " + amount +
                ", description - '" + description + '\'' +
                ", type - " + type +
                ", dateCreated - " + dateCreated +
                '}';
    }
}
