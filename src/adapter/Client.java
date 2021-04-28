package adapter;

public class Client {

    public static void main(String[] args) {

        // Create a Duck object
        Duck realDuck = new MallardDuck();

        // Create a Turkey object wrapped in an adapter
        Duck fakeDuck = new TurkeyAdapter(new WildTurkey());

        // Notice how the client can freely work with the Duck interface it expects
        realDuck.quack();
        fakeDuck.quack();

        realDuck.fly();
        fakeDuck.fly();
    }
}
