package lesson20.task2;

public class Controller {
    public TransactionDAO transactionDAO = new TransactionDAO();

    public Transaction save(Transaction transaction)throws Exception{
        return transactionDAO.save(transaction);
    }

    public Transaction[] transactionList(){

        return null;
    }

    public Transaction[] transactionList(String city){

        return null;
    }

    public Transaction[] transactionList(int amount){

        return null;
    }
}
