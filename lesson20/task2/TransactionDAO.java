package lesson20.task2;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Transaction[] transactions = new Transaction[10];

    public Transaction[] getTransactions() {
        return transactions;
    }

    private Utils utils = new Utils();

    public Transaction save(Transaction transaction) throws Exception{
        //если транзакция проходит валидацию, то сохранять
        //проверить есть ли уже такая транзакция в массиве и если нет, то сохранить
        if (transaction == null)
            throw new BadRequestException("NullPointer");

        validate(transaction);

        for(int i = 0; i < transactions.length; i++){
            if (transactions[i] == null){
                transactions[i] = transaction;
                return transactions[i];
            }
        }
        throw new InternalServerException("Can not save this " + transaction.getId() + " transaction");
    }

    private void validate(Transaction transaction) throws Exception{
        //сумма транзакции больше указанного лимита
        //сумма транзакций за день больше дневного лимита
        //количество транзакций за день больше указанного лимита
        //если город оплаты (совершения транзакции) не разрешен
        //не хватило места
        for (Transaction tr : transactions) {
            if (tr != null && tr.equals(transaction)){
                throw new BadRequestException("Such transaction " + transaction.getId() + " already exists");
            }
        }

        if (transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Transaction limit exceeded " + transaction.getId() + ". Can`t be saved");

        int sum = 0;
        int count = 0;
        for (Transaction tr : getTransactionsPerDay(transaction.getDateCreated())) {
            sum += tr.getAmount();
            count++;
        }

        if (sum + transaction.getAmount() > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Transaction limit per day amount exceeded " + transaction.getId() + ". Can`t be saved");

        if ((count + 1) > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Transaction limit per day count exceeded " + transaction.getId() + ". Can`t be saved");

        if (!checkCity(transaction))
            throw new BadRequestException("A transaction " + transaction.getId() + " from city: " + transaction.getCity() + " is not possible");

        if (checkIsFull() == 0)
            throw new InternalServerException("Can not save transaction " + transaction.getId() + " storage is full");
    }

    public Transaction[] transactionList(){
        if (transactions == null){
            return null;
        }

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null){
                count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction tr : transactions) {
            if (tr != null){
                result[index] = tr;
            }
            index++;
        }
        return result;
    }

    public Transaction[] transactionList(String city){
        if (transactions == null || city == null){
            return null;
        }

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && city.equals(transaction.getCity())){
                count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction tr : transactions) {
            if (tr != null && tr.getCity().equals(city)){
                result[index] = tr;
                index++;
            }
        }
        return result;
    }

    public Transaction[] transactionList(int amount){
        if (transactions == null || amount == 0){
            return null;
        }

        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction != null && transaction.getAmount() == amount){
                count++;
            }
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for (Transaction tr : transactions) {
            if (tr != null && amount == tr.getAmount()){
                result[index] = tr;
                index++;
            }
        }
        return result;
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

    private int checkIsFull(){
        int index = 0;
        int countNull = 0;
        for (Transaction transaction : transactions) {
            if (transaction == null){
                countNull++;
            }
            index++;
        }
        return countNull;
    }

    private boolean checkCity(Transaction transaction){
        if (transaction == null){
            return false;
        }

        for (String city : utils.getCities()) {
            if (city != null && city.equals(transaction.getCity())) {
                return true;
            }
        }
        return false;
    }
}
