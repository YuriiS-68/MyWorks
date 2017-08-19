package lesson20.task2;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save(Transaction transaction)throws Exception {
        //сумма транзакции больше указанного лимита
        //сумма транзакций за день больше дневного лимита
        //количество транзакций за день больше указанного лимита
        //если город оплаты (совершения транзакции) не разрешен
        //не хватило места
        int index = 0;
        if (validate(transaction)) {
            for(Transaction tr : transactions){
                if (tr == null){
                    transactions[index] = transaction;
                    return transactions[index];
                }
                index++;
            }
        }
        throw new InternalServerException("Unexpected error");
    }

    private boolean validate(Transaction transaction) throws Exception{
        if (transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Transaction limit exceeded " + transaction.getId() + ". Can`t be saved");

        int sum = 0;
        int count = 0;
        for(Transaction tr : getTransactionsPerDay(transaction.getDateCreated())){
            sum += tr.getAmount();
            count++;
        }

        if (sum > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Transaction limit per day amount exceeded " + transaction.getId() + ". Can`t be saved");

        if (count > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Transaction limit per day count exceeded " + transaction.getId() + ". Can`t be saved");

        if (!checkCity(transaction))
            throw new BadRequestException("A transaction from city: " + transaction.getCity() + " is not possible");

        if (!checkIsFull(transactions))
            throw new InternalServerException("unexpected error");

        return true;
    }

    private boolean checkIsFull(Transaction[] transactions){
        int index = 0;
        for(Transaction tr : transactions){
            if (tr != null){
                return false;
            }
            index++;
        }
        return true;
    }

    private boolean checkCity(Transaction transaction)throws InternalServerException{
        for(String city : utils.getCities()){
            if (city != null && city.equals(transaction.getCity())){
                return true;
            }
        }
        return false;
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


    public Transaction[] getTransactionsPerDay(Date dateOfCurTransaction){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfCurTransaction);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int count = 0;
        for(Transaction transaction : transactions){
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (trMonth == month && trDay == day)
                    count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for(Transaction transaction : transactions){
            if (transaction != null) {
                calendar.setTime(transaction.getDateCreated());
                int trMonth = calendar.get(Calendar.MONTH);
                int trDay = calendar.get(Calendar.DAY_OF_MONTH);

                if (trMonth == month && trDay == day) {
                    result[index] = transaction;
                    index++;
                }
            }
        }
        return result;
    }
}
