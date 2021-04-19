package factory;

import java.util.ArrayList;

public class NYPepperoniPizza extends Pizza {

    public NYPepperoniPizza(){
        super("thin", "pepperoni", new ArrayList<String>());
        this.ingredients.add("cheese");
        this.ingredients.add("pepperoni");
        this.ingredients.add("oregano");
    }
}
