package decorator;

/**
 * A class for a christmas tree decorator that adds a topper to the tree it wraps.
 */
public class TreeTopper extends Decorator{

    public TreeTopper(ChristmasTree wrappee){
        super(wrappee);
    }

    @Override
    public String decorate(){
        return this.wrappee.decorate() + " with tree topper";
    }
}
