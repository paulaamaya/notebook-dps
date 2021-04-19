package factory;
import java.util.ArrayList;

public abstract class Pizza {

    String crust;
    String type;
    ArrayList<String> ingredients;

    public Pizza(String crust, String type, ArrayList<String> ingredients){
        this.crust = crust;
        this.type = type;
        this.ingredients = ingredients;
    }

    public void prepare(){
        String info = "Preparing a " + this.type + " pizza with " +
                this.crust + " crust";

        for(String ingredient : this.ingredients){
            info += " and " + ingredient;
        }

        System.out.println(info);
    }

    public void bake(){
        System.out.println("Baking pizza...");
    }

    public void box(){ System.out.println("Boxing pizza..."); }

}
