package lesson20.task2;

import java.util.Calendar;
import java.util.Date;

public class TransactionDAO {

    private Controller controller = new Controller();
    private Utils utils = new Utils();

    public TransactionDAO() {
        utils.setLimitTransactionsPerDayCount(5);
        utils.setLimitTransactionsPerDayAmount(100);
        utils.setLimitSimpleTransactionAmount(40);
        utils.setCities(new String[]{"Kiev, Odesa, Mykolayiv"});
    }
    private Transaction[] transactions = new Transaction[10];

    public Transaction save(Transaction transaction)throws Exception{
        //TODO check and save if ok
        if (transaction.getAmount() > utils.getLimitTransactionsPerDayCount())
            throw new LimitExceeded("Amount of this transaction exceeded");

        if (transactions.length + 1 > utils.getLimitTransactionsPerDayAmount())
            throw new LimitExceeded("Count of transactions per day exceeded");

        if (controller.transactionsPerDayAmount(transactions) + transaction.getAmount() > utils.getLimitSimpleTransactionAmount())
            throw new LimitExceeded("Amount of transactions per day exceeded");

        //- если город оплаты(совершения транзакции) не разрешен
        if (!controller.checkFromCityTransaction(utils.getCities(), transaction))
            throw new BadRequestException("From this city: " + transaction.getCity() + " payment is not possible");

        return transaction;
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
            calendar.setTime(transaction.getDateCreated());
            int trMonth = calendar.get(Calendar.MONTH);
            int trDay = calendar.get(Calendar.DAY_OF_MONTH);

            if (trMonth == month && trDay == day)
                count++;
        }

        Transaction[] result = new Transaction[count];
        int index = 0;
        for(Transaction transaction : transactions){
            calendar.setTime(transaction.getDateCreated());
            int trMonth = calendar.get(Calendar.MONTH);
            int trDay = calendar.get(Calendar.DAY_OF_MONTH);

            if (trMonth == month && trDay == day)
                result[index] = transaction;
        }
        return result;
    }
}
