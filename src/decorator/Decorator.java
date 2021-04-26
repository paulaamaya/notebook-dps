package decorator;

/**
 * An abstract class for a tree Decorator requiring that all subclasses implement the ChristmasTree interface.  A Decorator has a reference to a ChristmasTree object,
 * which it wraps.
 */
public abstract class Decorator implements ChristmasTree{

    public ChristmasTree wrappee;

    /**
     * Creates a Decorator object that wraps a given ChristmasTree object.
     *
     * @param wrappee The ChristmasTree to be decorated.
     */
    public Decorator(ChristmasTree wrappee){
        this.wrappee = wrappee;
    }
}
