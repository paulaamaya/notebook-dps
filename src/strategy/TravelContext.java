package strategy;

public class TravelContext {

    private TravelStrategy strategy;

    public void setStrategy(TravelStrategy strategy){
        this.strategy = strategy;
    }

    //The context calls the execution method on the linked strategy object each time it needs to run the algorithm.
    // The context doesn’t know what type of strategy it uses or how the algorithm is executed.
    public void takeTrip(Person person, String location){
        this.strategy.travel(person, location);
    }

}
