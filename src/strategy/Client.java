package strategy;

public class Client {

    public static void main(String[] args) {
         // People who will travel
        Person bob = new Person("Bob", "120 Street 1");
        Person rob = new Person("Rob", "130 Street 2");
        TravelContext context = new TravelContext();

        // The Client creates a specific strategy object and passes it to the context
        CarStrategy useCar = new CarStrategy();
        // The context exposes a setter which lets clients replace the strategy associated with the context at runtime
        context.setStrategy(useCar);

        context.takeTrip(bob, "terminal 1");
        context.takeTrip(rob, "terminal 2");

        // The client can change strategy at any time, through the context, without having to worry about implementation
        BusStrategy useBus = new BusStrategy();

        context.takeTrip(bob, "hilton hotel");
        context.takeTrip(rob, "fairmont hotel");
    }
}
