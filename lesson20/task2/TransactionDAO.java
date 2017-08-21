package lesson20.task2;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];
    private Utils utils = new Utils();

    public Transaction save(Transaction transaction) throws Exception{
        //если транзакция проходит валидацию, то сохранять
        //проверить есть ли уже такая транзакция в массиве и если нет, то сохранить

        if (checkTransaction(transactions, transaction))
            throw new InternalServerException("Such transaction " + transaction.getId() + " already exists");
        //System.out.println(checkTransaction(transactions, transaction));

        if (!validate(transaction))
        throw new InternalServerException("unexpected error");

        int index = 0;
        for(Transaction tr : transactions){
            if (tr == null){
                transactions[index] = transaction;
                break;
            }
            index++;
        }
        //System.out.println(Arrays.toString(transactions));
        return transactions[index];
    }

    public boolean validate(Transaction transaction) throws Exception{
        //сумма транзакции больше указанного лимита
        //сумма транзакций за день больше дневного лимита
        //количество транзакций за день больше указанного лимита
        //если город оплаты (совершения транзакции) не разрешен
        //не хватило места

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
            throw new InternalServerException("A transaction from city: " + transaction.getCity() + " is not possible");

        if (!checkIsFull(transactions))
            throw new BadRequestException("Storage " + Arrays.toString(transactions) + " is full");

        return true;
    }

    public Transaction[] transactionList(){
        if (transactions == null){
            return null;
        }

        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null){
                transactions[index] = transaction;
            }
            index++;
        }
        return transactions;
    }

    public Transaction[] transactionList(String city){
        if (transactions == null || city == null){
            return null;
        }

        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && city.equals(transaction.getCity())){
                transactions[index] = transaction;
            }
            index++;
        }
        return transactions;
    }

    public Transaction[] transactionList(int amount){
        if (transactions == null){
            return null;
        }

        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && amount == transaction.getAmount()){
                transactions[index] = transaction;
            }
            index++;
        }
        return transactions;
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

    private boolean checkTransaction(Transaction[] transactions, Transaction transaction){
        if (transactions == null || transaction == null){
            return false;
        }

        int index = 0;
        for(Transaction tr : transactions){
            if (tr != null && transaction.equals(tr)){
                return true;
            }
            index++;
        }
        return false;
    }

    private boolean checkIsFull(Transaction[] transactions){
        if (transactions == null){
            return false;
        }

        int index = 0;
        for (Transaction transaction : transactions) {
            if (transaction == null){
                return true;
            }
            index++;
        }
        return false;
    }

    private boolean checkCity(Transaction transaction)throws InternalServerException{
        if (transaction == null){
            return false;
        }

        for(String city : utils.getCities()){
            if (city != null && city.equals(transaction.getCity())){
                return true;
            }
        }
        return false;
    }
}
