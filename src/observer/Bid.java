package observer;

/**
 * A class for a Bid. A bid has a unique id and an amount.
 */
public class Bid {

    private static int currID = 1;
    public int amount;
    public int id;

    public Bid(int amount){
        this.amount = amount;
        this.id = Bid.currID;
        increaseCurrID();
    }

    private  static void increaseCurrID(){
        Bid.currID++;
    }
}
