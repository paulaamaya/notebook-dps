package factory;

import java.util.ArrayList;

public class ChicagoPepperoniPizza extends Pizza{

    public ChicagoPepperoniPizza(){
        super("thick", "pepperoni", new ArrayList<String>());
        this.ingredients.add("cheese");
        this.ingredients.add("pepperoni");
        this.ingredients.add("oil");
    }
}
