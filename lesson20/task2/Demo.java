package lesson20.task2;

import java.util.Arrays;
import java.util.Date;

public class Demo {
    public static void main(String[] args)throws Exception {

        Controller controller = new Controller();

        Transaction transaction = new Transaction(1111, "Odessa", 15, "good", TransactionType.INCOME, new Date());
        Transaction transaction1 = new Transaction(2222, "Kiev", 30, "good", TransactionType.OUTCOME, new Date());
        Transaction transaction2 = new Transaction(3333, "Odessa", 5, "good", TransactionType.INCOME, new Date());
        Transaction transaction3 = new Transaction(4444, "Odessa", 25, "good", TransactionType.OUTCOME, new Date());
        Transaction transaction4 = new Transaction(5555, "Odessa", 5, "good", TransactionType.INCOME, new Date());
        Transaction transaction5 = new Transaction(6666, "Kiev", 10, "good", TransactionType.OUTCOME, new Date());
        Transaction transaction6 = new Transaction(7777, "Odessa", 5, "good", TransactionType.INCOME, new Date());
        Transaction transaction7 = new Transaction(8888, "Odessa", 5, "good", TransactionType.OUTCOME, new Date());
        Transaction transaction8 = new Transaction(9999, "Odessa", 10, "good", TransactionType.INCOME, new Date());
        Transaction transaction9 = new Transaction(1010, "Odessa", 5, "good", TransactionType.OUTCOME, new Date());
        Transaction transaction10 = new Transaction(1212, "Kiev", 5, "good", TransactionType.OUTCOME, new Date());

        System.out.println(controller.save(transaction));
        System.out.println(controller.save(transaction1));
        System.out.println(controller.save(transaction2));
        System.out.println(controller.save(transaction3));
        System.out.println(controller.save(transaction4));
        System.out.println(controller.save(transaction5));
        //System.out.println(controller.save(transaction6));
        //System.out.println(controller.save(transaction7));
        //System.out.println(controller.save(transaction8));
        //System.out.println(controller.save(transaction9));
        //System.out.println(controller.save(transaction10));

        System.out.println();
        controller.transactionList();
        //System.out.println(Arrays.toString(controller.transactionDAO.transactionList()));
        System.out.println();
        //controller.transactionList("Kiev");
        //System.out.println(Arrays.toString(controller.transactionDAO.transactionList("Odessa")));

    }
}
