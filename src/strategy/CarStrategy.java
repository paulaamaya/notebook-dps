package strategy;

public class CarStrategy implements TravelStrategy{

    @Override
    public void travel(Person person, String location) {
        person.setLocation(location);
        System.out.printf(person.getName() + " has travelled by car to " + person.getLocation() + "\n");
    }
}
