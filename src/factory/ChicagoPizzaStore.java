package factory;

public class ChicagoPizzaStore extends PizzaStore{

    @Override
    public Pizza factoryMethod(String type) {
        return switch (type) {
            case "pepperoni" -> new ChicagoPepperoniPizza();
            default -> new ChicagoCheesePizza();
        };
    }
}
