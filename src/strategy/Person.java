package strategy;

public class Person {

    private String name;
    private String location;

    public Person(String name, String location){
        this.name = name;
        this.location = location;
    }

    public String getName(){ return this.name; }

    public String getLocation(){ return this.location; }

    public void setLocation(String location){ this.location = location; }
}
