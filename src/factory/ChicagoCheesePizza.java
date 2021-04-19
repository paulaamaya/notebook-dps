package factory;

import java.util.ArrayList;

public class ChicagoCheesePizza extends Pizza{

    public ChicagoCheesePizza(){
        super("thick", "cheese", new ArrayList<String>());
        this.ingredients.add("cheese");
        this.ingredients.add("oil");
    }
}
