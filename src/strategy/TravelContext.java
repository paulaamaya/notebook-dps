package strategy;

/**
 * A class for a travel context. It exposes a setter method by mean of which the client can pass a chosen strategy and
 * a call to an algorithm to be implemented by all the valid strategies that can be passed.
 */
public class TravelContext {

    private TravelStrategy strategy;

    /**
     * Sets this context's strategy.
     *
     * @param strategy Given strategy to be used (passed by client).
     */
    public void setStrategy(TravelStrategy strategy){
        this.strategy = strategy;
    }


    /**
     * The algorithm to be called on all the interchangeable strategies.
     *
     * @param person Person that will travel.
     * @param location Location to take the Person object to.
     */
    public void takeTrip(Person person, String location){
        this.strategy.travel(person, location);
    }

}
