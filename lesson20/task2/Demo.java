package lesson20.task2;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        Transaction[] transactions = new Transaction[2];
        TransactionDAO transactionDAO = new TransactionDAO();
        try {
            Transaction transaction = new Transaction(4444, "Odessa", 20, "good", TransactionType.INCOME, new Date());
            Transaction transaction1 = new Transaction(3333, "Odessa", 10, "good", TransactionType.OUTCOME, new Date());

            System.out.println(transactionDAO.validate(transaction));
            transactionDAO.save(transaction);
            System.out.println(Arrays.toString(transactions));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(transactions.length);
        System.out.println(Arrays.toString(transactions));


    }
}
