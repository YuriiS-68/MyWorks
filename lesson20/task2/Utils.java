package lesson20.task2;

public class Utils {
    private static int limitTransactionsPerDayCount = 10;
    private static int limitTransactionsPerDayAmount = 100;
    private static int limitSimpleTransactionAmount = 40;
    private String[] cities = {"Kiev", "Odessa"};

    public int getLimitTransactionsPerDayCount() {
        return limitTransactionsPerDayCount;
    }

    public int getLimitTransactionsPerDayAmount() {
        return limitTransactionsPerDayAmount;
    }

    public int getLimitSimpleTransactionAmount() {
        return limitSimpleTransactionAmount;
    }

    public String[] getCities() {
        return cities;
    }
}
