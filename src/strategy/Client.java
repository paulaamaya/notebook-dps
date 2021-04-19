package strategy;

public class Client {

    public static void main(String[] args) {
         // People who will travel
        Person bob = new Person("Bob", "120 Street 1");
        Person rob = new Person("Rob", "130 Street 2");
        TravelContext context = new TravelContext();

        // The Client creates a specific strategy object and passes it to the context.
        // The context exposes a setter which lets clients replace the strategy associated with the context at runtime.
        CarStrategy car = new CarStrategy();
        context.setStrategy(car);

        context.takeTrip(bob, "airport");
        context.takeTrip(rob, "fairmont hotel");
    }
}
