package strategy;

public class BusStrategy implements TravelStrategy{

    @Override
    public void travel(Person person, String location) {
        person.setLocation(location);
        System.out.println(person.getName() + " has travelled by bus to " + person.getLocation() + "\n");
    }
}