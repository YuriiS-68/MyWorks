package lesson20.task2;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        TransactionDAO transactionDAO = new TransactionDAO();
        try {
            Transaction transaction = new Transaction(1111, "Odessa", 20, "good", TransactionType.INCOME, new Date());
            Transaction transaction1 = new Transaction(2222, "Kiev", 5, "good", TransactionType.OUTCOME, new Date());
            Transaction transaction2 = new Transaction(3333, "Odessa", 5, "good", TransactionType.INCOME, new Date());
            Transaction transaction3 = new Transaction(4444, "Odessa", 5, "good", TransactionType.OUTCOME, new Date());
            Transaction transaction4 = new Transaction(5555, "Odessa", 5, "good", TransactionType.INCOME, new Date());
            Transaction transaction5 = new Transaction(6666, "Kiev", 10, "good", TransactionType.OUTCOME, new Date());
            Transaction transaction6 = new Transaction(7777, "Odessa", 5, "good", TransactionType.INCOME, new Date());
            Transaction transaction7 = new Transaction(8888, "Odessa", 5, "good", TransactionType.OUTCOME, new Date());
            Transaction transaction8 = new Transaction(9999, "Odessa", 10, "good", TransactionType.INCOME, new Date());
            Transaction transaction9 = new Transaction(1010, "Odessa", 5, "good", TransactionType.OUTCOME, new Date());

            //System.out.println(transactionDAO.validate(transaction));
            //transactionDAO.save(transaction);
            //transactionDAO.save(transaction1);
            //transactionDAO.save(transaction2);
            //transactionDAO.save(transaction3);
            //transactionDAO.save(transaction4);
            System.out.println(transactionDAO.save(transaction));
            System.out.println(transactionDAO.save(transaction1));
            System.out.println(transactionDAO.save(transaction2));
            System.out.println(transactionDAO.save(transaction3));
            System.out.println(transactionDAO.save(transaction4));
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
