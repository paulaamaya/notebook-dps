package factory;

import java.util.ArrayList;

public class NYCheesePizza extends Pizza{

    public NYCheesePizza(){
        super("thin", "cheese", new ArrayList<String>());
        this.ingredients.add("cheese");
        this.ingredients.add("oregano");
    }
}
