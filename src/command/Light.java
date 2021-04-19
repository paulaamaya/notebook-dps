package command;

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
