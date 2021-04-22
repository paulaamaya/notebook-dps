package observer;
import java.util.Observable;
import java.util.Observer;

/**
 * A class for a bidder in an auction. A bidder makes bids and receives updates from an
 * Observable that it has been observing.
 */
public class Bidder implements Observer{

    /**
     * Returns a new Bid object with a unique ID for the given amount.
     *
     * @param amount  Amount the bidder is offering.
     * @return A Bid object for the amount offered.
     */
    public Bid makeBid(int amount){
        Bid bid = new Bid(amount);
        return bid;}

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
