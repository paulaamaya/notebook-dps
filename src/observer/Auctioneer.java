package observer;
import java.util.ArrayList;
import java.util.Observable;

/**
 * A class for an auctioneer at an auction.  An auctioneer receives bids,
 * keeps track of the highest bid and notifies all bidders a new higher bid
 * has been placed.  The auctioneer is observed by the bidders.
 */
public class Auctioneer extends Observable {
    ArrayList<Bid> bids; //list of bids received
    int highestBid;

    public Auctioneer(){
        this.bids = new ArrayList<Bid>();
        this.highestBid = 0;
    }


    /**
     * Receives a passed bid.  If the bid is higher than the current highest bid,
     * the highest bid is updated and auctioneers are notified.
     */
    public void receiveBid(Bid bid){
        this.bids.add(bid);
        if (bid.amount > this.highestBid){
            this.highestBid = bid.amount;
            this.setChanged();
            this.notifyObservers("The highest bid is now " + bid.amount + " with ID: " + bid.id);
        }
    }
}
