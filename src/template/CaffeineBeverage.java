package template;

/**
 * An abstract class for a caffeinated beverage.  This class controls the recipe algorithm and call on subclasses to
 * define the brewing and condiment-adding process (depending on an optional hook).
 */
public abstract class CaffeineBeverage {

    /**
     * The template method for preparing a recipe - boiling, brewing, pouring, and condiments.
     */
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()) {
            addCondiments();
        }
        System.out.println("Your beverage is ready!\n");
    }

    /**
     * Brews the contents of the beverage.
     */
    abstract void brew();

    /**
     * Adds condiments to the beverage.
     */
    abstract void addCondiments();

    /**
     * Boils the water for the beverage.
     */
    void boilWater() {
        System.out.println("Boiling water...");
    }

    /**
     * Pours beverage in a cup.
     */
    void pourInCup() {
        System.out.println("Pouring in cup");
    }

    /**
     * Hook method that subclasses can override to control if the beverage should have condiments.  If subclasses do
     * not override this hook, the default behaviour is to add condiments.
     *
     * @return true
     */
    boolean customerWantsCondiments(){
        return true;
    }
}
