package factory;

public class NYPizzaStore extends PizzaStore{

    @Override
    public Pizza makePizza(String type) {
        return switch (type) {
            case "pepperoni" -> new NYPepperoniPizza();
            default -> new NYCheesePizza();
        };
    }
}
