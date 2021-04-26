package command;

/**
 * A class for a light in a house.  A light has a location where it is placed and can be turned on or off.
 */
public class Light {

    String location;

    public Light(String location){
        this.location = location;
    }

    public void on(){
        System.out.println("The light in the " + this.location + " is on.");
    }

    public void off(){
        System.out.println("The light in the " + this.location + " is on.");
    }
}
