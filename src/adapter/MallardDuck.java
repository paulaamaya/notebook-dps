package adapter;

/**
 * A class for a mallard duck that implements the Duck interface.  A duck quacks and flies.
 */
public class MallardDuck implements Duck{

    @Override
    public void quack() {
        System.out.println("Quack!");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying for 500 metres...");
    }
}
