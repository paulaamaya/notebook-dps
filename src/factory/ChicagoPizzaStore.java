package factory;

public class ChicagoPizzaStore extends PizzaStore{

    @Override
    public Pizza makePizza(String type) {
        return switch (type) {
            case "pepperoni" -> new ChicagoPepperoniPizza();
            default -> new ChicagoCheesePizza();
        };
    }
}
