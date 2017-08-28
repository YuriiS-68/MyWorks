package dz_to_lesson25;

public class Order {
    private String name;

    public Order(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                '}';
    }
}
