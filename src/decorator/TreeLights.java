package decorator;

/**
 * A class for a christmas tree decorator that adds lights to the tree it wraps.
 */
public class TreeLights extends Decorator{

    public TreeLights(ChristmasTree wrappee) {
        super(wrappee);
    }

    @Override
    public String decorate() {
        return this.wrappee.decorate() + " with lights";
    }
}
