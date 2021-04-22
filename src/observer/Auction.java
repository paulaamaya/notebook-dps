package observer;
import java.util.ArrayList;
/**
 * A class for an auction.  An auction has an auctioneer and a list of bidders that are observers
 * of the auctioneer.  An auction creates its own auctioneer but takes in bidders, if given any.
 */
public class Auction {

    public Auctioneer auctioneer;
    private ArrayList<Bidder> bidders;

    public Auction(){
        this.auctioneer = new Auctioneer();
        this.bidders = new ArrayList<>();
    }

    public void addBidder(Bidder bidder){
        this.bidders.add(bidder);
        // new bidders in the auction are observers of the auctioneer
        this.auctioneer.addObserver(bidder);
    }

    public static void main(String[] args){

        // Create an auction with 3 bidders
        Auction auction = new Auction();
        Bidder bidder1 = new Bidder();
        Bidder bidder2 = new Bidder();


        // Add the bidders to the auction
        auction.addBidder(bidder1);
        auction.addBidder(bidder2);

        // Have the auctioneer receive bids from the bidders
        // If a higher bid is made, all the bidders are automatically notified of the update
        auction.auctioneer.receiveBid(bidder1.makeBid(50));
        auction.auctioneer.receiveBid(bidder2.makeBid(70));
        auction.auctioneer.receiveBid(bidder1.makeBid(100));

    }
}