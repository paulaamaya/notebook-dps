package singleton;

/**
 * A class for a chocolate boiler in a factory with a single boiler.
 *
 * If more than one boiler is instantiated in a program, bad things can happen.  (e.g. the
 * program creates two boiler instances and fills up an full boiler -> overflow!)
 */
public class ChocolateBoiler {

    private boolean isEmpty;
    private boolean isBoiled;
    private static ChocolateBoiler uniqueInstance;

    /**
     * Private constructor that creates an empty, non-boiled chocolate boiler.
     */
    private ChocolateBoiler(){
        this.isEmpty = false;
        this.isBoiled = false;
    }

    /**
     * A method for accessing the chocolate boiler.  If no boiler has been instantiated, the
     * private constructor is called to create a new one first, then the boiler is returned.
     * Else it just returns the unique boiler.
     *
     * @return the unique instance of the class, i.e. ChocolateBoiler.uniqueInstance
     */
    public static ChocolateBoiler getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    // Other useful methods...
}
