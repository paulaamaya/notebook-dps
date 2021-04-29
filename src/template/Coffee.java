package template;

import java.util.Locale;
import java.util.Scanner;

public class Coffee extends CaffeineBeverage{
    /**
     * {@inheritDoc}
     * This implementation brews coffee beans.
     */
    @Override
     void brew() {
        System.out.println("Brewing coffee beans...");
    }

    /**
     * {@inheritDoc}
     * This implementation adds milk and sugar.
     */
    @Override
    void addCondiments() {
        System.out.println("Adding milk and sugar");
    }

    /**
     * Asks a customer if they would like milk and sugar in their coffee.
     *
     * @return true iff customer says 'Yes' to the prompt for milk and sugar.
     */
    @Override
    boolean customerWantsCondiments(){
        System.out.println("Would you like milk and sugar with your coffee?");
        Scanner s = new Scanner(System.in);

        String answer = s.nextLine();
        if(answer.toLowerCase().equals("yes")){
            return true;
        }
        return false;
    }
}
