package template;

public class Tea extends CaffeineBeverage{

    /**
     * {@inheritDoc}
     * This implementation steeps tea bags.
     */
    @Override
    void brew() {
        System.out.println("Steeping tea bags...");
    }

    /**
     * {@inheritDoc}
     * This implementation adds lemon to the tea.
     */
    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
}
