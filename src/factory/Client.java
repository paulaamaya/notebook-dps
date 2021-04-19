package factory;

public class Client {

    public static void main(String[] args) {
        PizzaStore nyc = new NYPizzaStore();
        PizzaStore chi = new ChicagoPizzaStore();

        // Harvey's order
        nyc.orderPizza("pepperoni");

        //Jessica's order
        chi.orderPizza("pepperoni");
    }
}
