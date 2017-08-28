package dz_to_lesson25;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args)throws Exception {

        GeneralDAO<Order> orderDAO = new GeneralDAO<>();
        Order order1 = new Order("first");
        Order order2 = new Order("second");
        Order order3 = new Order("three");
        Order order4 = new Order("four");
        Order order5 = new Order("five");
        Order order6 = new Order("six");
        Order order7 = new Order("seven");
        Order order8 = new Order("eight");
        Order order9 = new Order("nine");
        Order order10 = new Order("ten");
        Order order = new Order("zero");

        orderDAO.save(order1);
        orderDAO.save(order2);
        orderDAO.save(order3);
        orderDAO.save(order4);
        orderDAO.save(order5);
        orderDAO.save(order6);
        orderDAO.save(order7);
        orderDAO.save(order8);
        orderDAO.save(order9);
        orderDAO.save(order10);

        System.out.println(Arrays.deepToString(orderDAO.getAll()));
    }
}
