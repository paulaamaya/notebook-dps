package adapter;

/**
 * A class for an adapter from the Turkey interface to the Duck interface.
 */
public class TurkeyAdapter implements Duck{

    private Turkey turkey;

    /**
     * Creates a new TurkeyAdapter that takes in a Turkey and adapts it to have Duck behaviour.
     * @param turkey Turkey object to be wrapped in the adapter.
     */
    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    // The quack translation is to call the turkey's gobble method.
    @Override
    public void quack() {
        this.turkey.gobble();
    }

    // Turkeys fly in short spurts.  To map between a Duck's fly() and a Turkey's we must call the
    // the turkey's method fly() five times to make it fly an equivalent distance.
    @Override
    public void fly() {
        for(int i = 0; i < 5; i++ ){
            this.turkey.fly();
        }
    }
}
