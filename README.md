
- [Strategy Pattern](#strategy-pattern)
  - [Example: Travelling](#example-travelling)
- [Observer Pattern](#observer-pattern)
  - [Example: Auctioneer and Bidders](#example-auction)
- [Decorator Pattern](#decorator-pattern)
  - [Example: Christmas Tree](#example-christmas-tree)
- [Factory Pattern](#factory-pattern)
  - [Example: Pizza Stores](#example-pizza-stores)
- [Singleton Pattern](#singleton-pattern)
  - [Example: Chocolate Boiler](#example-chocolate-boiler)
- [Command Pattern](#command-pattern)
  - [Example: Global Remote Control](#example-global-remote-control)
- [Adapter Pattern](#adapter-pattern)
- [Facade Pattern](#facade-pattern)
- [Template Method](#template-method)
- [Iterator Pattern](#iterator-pattern)
  - [Example: Song Iterator](#example-song-iterator)

# Strategy Pattern

Defines a family of algorithms, encapsulates each one, and makes them interchangeable.  Strategy enables the client to select the algorithm at runtime; the algorithm varies independently of the clients that use it.

![Strategy UML](docs/strategy-uml.png)

## Example: Travelling

Here we will implement a simple program that takes a person to a given location through different strategies - the person can travel by car or bus.  The Client only interacts with the context as follows:

```java
public class Client {

    public static void main(String[] args) {
         // People who will travel
        Person bob = new Person("Bob", "120 Street 1");
        Person rob = new Person("Rob", "130 Street 2");
        TravelContext context = new TravelContext();

        // The Client creates a specific strategy object and passes it to the context
        CarStrategy useCar = new CarStrategy();
        // The context exposes a setter which lets clients replace the strategy associated with the context at runtime
        context.setStrategy(useCar);

        context.takeTrip(bob, "terminal 1");
        context.takeTrip(rob, "terminal 2");

        // The client can change strategy at any time, through the context, without having to worry about implementation
        BusStrategy useBus = new BusStrategy();

        context.takeTrip(bob, "hilton hotel");
        context.takeTrip(rob, "fairmont hotel");
    }
}
```

```
Bob has travelled by car to terminal 1
Rob has travelled by car to terminal 2

Bob has travelled by car to hilton hotel
Rob has travelled by car to fairmont hotel
```

The context delegates the algorithm to a linked strategy object instead of doing the work itself. The context does not know what type of strategy it uses or how the algorithm is executed, it simply works with an interface.  In this way, the context is decoupled from the specific strategies and work with all strategies that implement a generic interface.

```java
/**
 * A class for a travel context. It exposes a setter method by mean of which the client can pass a chosen strategy and
 * a call to an algorithm to be implemented by all the valid strategies that can be passed.
 */
public class TravelContext {

    private TravelStrategy strategy;

    /**
     * Sets this context's strategy.
     *
     * @param strategy Given strategy to be used (passed by client).
     */
    public void setStrategy(TravelStrategy strategy){
        this.strategy = strategy;
    }


    /**
     * The algorithm to be called on all the interchangeable strategies.
     *
     * @param person Person that will travel.
     * @param location Location to take the Person object to.
     */
    public void takeTrip(Person person, String location){
        this.strategy.travel(person, location);
    }

}
```

This is the generic interface that declares the functionality to be implemented by the corresponding family of algorithms.

```java
public interface TravelStrategy {

    public void travel(Person person, String location);

}
```

These are the concrete implementations of the Strategy interface.  Notice that the details of how each strategy achieves the algorithm are completely hidden from the context or client.

```java
public class CarStrategy implements TravelStrategy{

    @Override
    public void travel(Person person, String location) {
        person.setLocation(location);
        System.out.println(person.getName() + " has travelled by car to " + person.getLocation() + "\n");
    }
}

public class BusStrategy implements TravelStrategy{

  @Override
  public void travel(Person person, String location) {
    person.setLocation(location);
    System.out.println(person.getName() + " has travelled by bus to " + person.getLocation() + "\n");
  }
}
```

# Observer Pattern
Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

![Observer UML](docs/observer-uml.png)

**Pull Communication Method**: The Observable just sends a short message to observers to notify them that something has changed, the observers are responsible for pulling the data they need from the Observer.

**Push Communication Method**: The Observable just sends all information to the observers; the observers decide what to use.

## Example: Auction

This design pattern can be modelled as an auction (Client) with an auctioneer (Observable) and bidders (Observers) getting information about the status of the transaction from the auctioneer.

```java
/**
 * A class for a Bid. A bid has a unique id and an amount.
 */
public class Bid {

    private static int currID = 1;
    public int amount;
    public int id;

    public Bid(int amount){
        this.amount = amount;
        this.id = Bid.currID;
        increaseCurrID();
    }

    private  static void increaseCurrID(){
        Bid.currID++;
    }
}
```

```java
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class for an auctioneer at an auction.  An auctioneer receives bids,
 * keeps track of the highest bid and notifies all bidders a new higher bid
 * has been placed.  The auctioneer is observed by the bidders.
 */
public class Auctioneer extends Observable {
    ArrayList<Bid> bids; //list of bids received
    int highestBid;

    public Auctioneer(){
        this.bids = new ArrayList<Bid>();
        this.highestBid = 0;
    }


    /**
     * Receives a passed bid.  If the bid is higher than the current highest bid,
     * the highest bid is updated and auctioneers are notified.
     */
    public void receiveBid(Bid bid){
        this.bids.add(bid);
        if (bid.amount > this.highestBid){
            this.highestBid = bid.amount;
            this.setChanged();
            this.notifyObservers("The highest bid is now " + bid.amount + " with ID: " + bid.id);
        }
    }
}

/**
 * A class for a bidder in an auction. A bidder makes bids and receives updates from an
 * Observable that it has been observing.
 */
public class Bidder implements Observer{

  /**
   * Returns a new Bid object with a unique ID for the given amount.
   *
   * @param amount  Amount the bidder is offering.
   * @return A Bid object for the amount offered.
   */
  public Bid makeBid(int amount){
    Bid bid = new Bid(amount);
    return bid;}

  @Override
  public void update(Observable o, Object arg) {
    System.out.println(arg);
  }
}
```

Now notice how the observable and observers are loosely coupled.  They interact as needed but have very little knowledge about each other.
- The `Observable` only knows that its observers implement the `Observer` interface.  It knows nothing about their class or what they do.
- We can add all sorts of `Observer`s without the need to modify the `Observable`.
- As long as both parties meet their design pattern expectations, changes to either the `Observable` or the `Observer`s will not affect each other.
  
```java
import java.util.ArrayList;
/**
 * A class for an auction.  An auction has an auctioneer and a list of bidders that are observers
 * of the auctioneer.  An auction creates its own auctioneer but takes in bidders, if given any.
 */
public class Auction {

    public Auctioneer auctioneer;
    private ArrayList<Bidder> bidders;

    public Auction(){
        this.auctioneer = new Auctioneer();
        this.bidders = new ArrayList<>();
    }

    public void addBidder(Bidder bidder){
        this.bidders.add(bidder);
        // new bidders in the auction are observers of the auctioneer
        this.auctioneer.addObserver(bidder);
    }

    public static void main(String[] args){

        // Create an auction with 3 bidders
        Auction auction = new Auction();
        Bidder bidder1 = new Bidder();
        Bidder bidder2 = new Bidder();


        // Add the bidders to the auction
        auction.addBidder(bidder1);
        auction.addBidder(bidder2);

        // Have the auctioneer receive bids from the bidders
        // If a higher bid is made, all the bidders are automatically notified of the update
        auction.auctioneer.receiveBid(bidder1.makeBid(50));
        auction.auctioneer.receiveBid(bidder2.makeBid(70));
        auction.auctioneer.receiveBid(bidder1.makeBid(100));

    }
}
```

```
The highest bid is now 50 with ID: 1
The highest bid is now 50 with ID: 1
The highest bid is now 70 with ID: 2
The highest bid is now 70 with ID: 2
The highest bid is now 100 with ID: 3
The highest bid is now 100 with ID: 3
```
  
# Decorator Pattern

Attaches additional responsibilities to an object dynamically.  The decorator pattern involves a set of decorating classes that are used to wrap concrete components.  Decorators provide a flexible alternative to subclassing for extending functionality.

By dynamically composing objects, you can add new functionality by writing new "decorating" code rather than changing existing code.

![Decorator UML](docs/decorator-uml.png)

## Example: Christmas Tree

First we define a common interface for both the concrete components and decorators to implement:

```java
public interface ChristmasTree {

    public String decorate();
}
```

Now we define the base classes, i.e. the **basic component class** and the **abstract decorator class** for all the decorators that we will implement.

```java
/**
 * A class for a basic pine christmas tree.
 */
public class PineChristmasTree implements ChristmasTree{

  @Override
  public String decorate(){
    return "Pine christmas tree";
  }
}

/**
 * An abstract class for a tree Decorator requiring that all subclasses implement the 
 * ChristmasTree interface.  A Decorator has a reference to a ChristmasTree object, 
 * which it wraps.
 */
public abstract class Decorator implements ChristmasTree{

  public ChristmasTree wrappee;

  /**
   * Creates a Decorator object that wraps a given ChristmasTree object.
   *
   * @param wrappee The ChristmasTree to be decorated.
   */
  public Decorator(ChristmasTree wrappee){
    this.wrappee = wrappee;
  }
}
```

Now we implement the **concrete decorator classes** which inherit from the abstract `Decorator` class.  They keep a reference to the object they are wrapping so that they can chain method calls down to the basic component class, this component is passed to their constructor.

```java
/**
 * A class for a christmas tree decorator that adds lights to the tree it wraps.
 */
public class TreeLights extends Decorator{

  public TreeLights(ChristmasTree wrappee) {
    super(wrappee);
  }

  @Override
  public String decorate() {
    return this.wrappee.decorate() + " with lights";
  }
}

/**
 * A class for a christmas tree decorator that adds a topper to the tree it wraps.
 */
public class TreeTopper extends Decorator{

    public TreeTopper(ChristmasTree wrappee){
        super(wrappee);
    }

    @Override
    public String decorate(){
        return this.wrappee.decorate() + " with tree topper";
    }
}
```

Decorators are meant to add behaviour to the object they wrap.  So the basic component class gets additional behaviour added to it, not by adding more code into it, but by having the client wrap it in decorators expanding its behaviour.

```java
// Client wraps concrete components with decorator classes
public class TreeClient {

  public static void main(String[] args) {
    ChristmasTree myTree = new TreeTopper(new TreeLights(new PineChristmasTree()));
    ChristmasTree yourTree = new TreeTopper(new PineChristmasTree());

    System.out.println("Let's decorate my tree: " + myTree.decorate());
    System.out.println("Let's decorate your tree: " + yourTree.decorate());
  }
}
```

```
Let's decorate my tree: Pine christmas tree with lights with tree topper
Let's decorate your tree: Pine christmas tree with tree topper
```

# Factory Pattern

Defines an abstract class for creating an object, but allows subclasses to decide what objects to instantiate.  It lets a class defer instantiation to subclasses.

![Factory UML](docs/factory-uml.png)

The Creator class gives you an interface with a method for creating objects, also known as the factory method.

Any other methods implemented in the abstract Creator class are written to operate on products produced, except the factory method – the creator class is written without knowledge of the actual products that will be created.

## Example: Pizza Stores

We begin with the abstract `Product` class that all the concrete products will inherit from.  
This abstract product class will provide some defaults while the concrete products will define the details  - this part is nothing fancy, just inheritance in its purest form.

We can think of these `Product` classes as the tiny factories that the store call on to make their pizzas.

```java
// Abstract 'Product' class
public class Pizza {

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

// Concrete product classes
public class NYPepperoniPizza extends Pizza {

  public NYPepperoniPizza(){
    super("thin", "pepperoni", new ArrayList<String>());
    this.ingredients.add("cheese");
    this.ingredients.add("pepperoni");
    this.ingredients.add("oregano");
  }
}

public class ChicagoPepperoniPizza extends Pizza{

  public ChicagoPepperoniPizza(){
    super("thick", "pepperoni", new ArrayList<String>());
    this.ingredients.add("cheese");
    this.ingredients.add("pepperoni");
    this.ingredients.add("oil");
  }
}
```

Now we are going to define the `PizzaStore` classes which will encapsulate the creation of the pizzas through the factories we defined above.  We start off with a basic `PizzaStore` abstract class that defines the common methods amongst store franchises - such as the flow of preparing an order - and an abstract factory method.

```java
public abstract class PizzaStore {

  public Pizza orderPizza(String type){
    Pizza pizza = this.makePizza(type);

    pizza.prepare();
    pizza.bake();
    pizza.box();

    return pizza;
  }

  public abstract Pizza makePizza(String type);
}
```

A cheese pizza in Chicago is different from a cheese Pizza in NYC.  However, it is the store in each city that worries about making the right one, not the client ordering the pizza!

The concrete creators decide which concrete product class to instantiate.

```java
public class NYPizzaStore extends PizzaStore{

    @Override
    public Pizza factoryMethod(String type) {
        return switch (type) {
            case "pepperoni" -> new NYPepperoniPizza();
            default -> new NYCheesePizza();
        };
    }
}

public class ChicagoPizzaStore extends PizzaStore {

  @Override
  public Pizza factoryMethod(String type) {
    return switch (type) {
      case "pepperoni" -> new ChicagoPepperoniPizza();
      default -> new ChicagoCheesePizza();
    };
  }
}
```

As it should, the client only deals with the stores and has no knowledge of the intricacies behind creating the right pizza.  The creation of the `Product` is completely encapsulated by the `Creator`.

```java
public class Client {

    public static void main(String[] args) {
        PizzaStore nyc = new NYPizzaStore();
        PizzaStore chi = new ChicagoPizzaStore();

        // Harvey's order
        nyc.orderPizza("pepperoni");

        //Jessica's order
        chi.orderPizza("pepperoni");
    }
}
```

```
Preparing a pepperoni pizza with thin crust and cheese and pepperoni and oregano
Baking pizza...
Boxing pizza...

Preparing a pepperoni pizza with thick crust and cheese and pepperoni and oil
Baking pizza...
Boxing pizza...
```

# Singleton Pattern

Ensures a class has only one instance of an object and provides a global point of access. We are letting the class manage a single instance of itself.

![Singleton UML](docs/singleton-uml.png)

## Example: Chocolate Boiler

The trick with Singleton is to block external creation of the class:
- Declare a private constructor so that only the class can create an instance of itself.
- Declare a `getInstance()` method as the only point of access.  Whether the unique instance
  exists or not, this is the only way another class can access it.
- Keep a reference to the unique instance in a private class variable.

```java
/**
 * A class for a chocolate boiler in a factory with a single boiler.
 *
 * If more than one boiler is instantiated in a program, bad things can happen.  (e.g. the
 * program creates two boiler instances and fills up an full boiler -> overflow!)
 */
public class ChocolateBoiler {

  private boolean isEmpty;
  private boolean isBoiled;
  private static ChocolateBoiler uniqueInstance;

  /**
   * Private constructor that creates an empty, non-boiled chocolate boiler.
   */
  private ChocolateBoiler(){
    this.isEmpty = false;
    this.isBoiled = false;
  }

  /**
   * A method for accessing the chocolate boiler.  If no boiler has been instantiated, the
   * private constructor is called to create a new one first, then the boiler is returned.
   * Else it just returns the unique boiler.
   *
   * @return the unique instance of the class, i.e. ChocolateBoiler.uniqueInstance
   */
  public static ChocolateBoiler getInstance(){
    if(uniqueInstance == null){
      uniqueInstance = new ChocolateBoiler();
    }
    return uniqueInstance;
  }

  // Other useful methods...
}
```

# Command Pattern

This pattern encapsulates requests as objects, thereby letting you parametrize other objects with different requests,  queue or log requests and support undoable operations.  It decouples the requester of an action from the object that actually performs the action.

In this way the same object can be parametrized in multiple ways with all sort of commands as long as they implement the `Command` interface!

![Command UML](docs/command-uml.png)

A command object encapsulates a request by binding together a set of actions on a specific receiver.  To achieve this it packages the actions and the receiver into an object that only exposes the method `execute()`.

> The Null Object
> 
>A **null object** is useful when you don’t have a meaningful object to return, and yet you want to remove the responsibility for handling a `null` from the client.  It is common to use a `NoCommand` null object for unassigned invokers. (See the Remote Control constructor below).

In general, we strive for “dumb” command objects that just invoke an action on the receiver, so we can maintain the same level of decoupling between the invoker and the receiver.

## Example: Global Remote Control

We begin with the **`Invoker`** class which will be completely decoupled from the actual receiver classes.  It will simply interact with the messenger `Command` interface.

```java
package command;

/**
 * A class for a home remote control that stores four commands.
 */
public class RemoteControl {

    public Command[] onCommands;

    /**
     * Creates a new remote control with noCommand objects in each button. Thus, the execute() method 
     * can be called on all buttons, even if they haven't been assigned a command that does something useful
     */
    public RemoteControl(){
        this.onCommands = new Command[4];

        Command noCommand = new NoCommand();
        for(int i = 0; i < 4; i++){
            onCommands[i] = noCommand;
        }
    }

    /**
     * Sets a given Command object into a given slot number.
     *
     * @param slot Button number to be programmed.
     * @param onCommand Command to be carried out by the given button.
     */
    public void setCommand(int slot, Command onCommand){
        if (slot < 4 && slot >= 0){
            this.onCommands[slot] = onCommand;
        }
    }

    /**
     * Executes the Command that has been set for the given button number by calling the Command's execute() method.
     * If the button has not been assigned a significant command, the method is called on a NoCommand object which does
     * nothing meaningful, but application doesn't break.
     *
     * @param slot Button whose assigned command is to be carried out.
     */
    public void buttonPushed(int slot){
        if (slot < 4 && slot >= 0){
            this.onCommands[slot].execute();
        }
    }
}
```

Here is where the beauty of command is really highlighted.  Below we have some classes for receivers that we would like to program into our global remote control.  Each class has very different implementations, functionalities, and method names.

Notice that if we wanted to do this the naive way, we would have to alter the `RemoteControl` code eveytime we wanted to add a new receivers so as to accomodate to its APIs.

```java
/**
 * A class for a house alarm.  A house alarm can be armed or disarmed.
 */
public class Alarm {

    public void arm(){
        System.out.println("Alarm is armed. Please exit the building in the next minute.");
    }

    public void disarm(){
        System.out.println("Alarm is disarmed.");
    }
}
```

```java
/**
 * A class for a garage door.  The garage door can go up or down.
 */
public class GarageDoor {

    public void up(){
        System.out.println("Garage door is up.");
    }

    public void down(){
        System.out.println("Garage door is down.");
    }
}
```

```java
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
```

Here is where this design pattern steps in.  Instead of all that hard coding into to remote control, we define a `Command` interface that simply imposes an `execute()` method.  **Each concrete implementation of `Command` objects will worry about the messy details of what "executing" means**.

```java
public interface Command { 
  public void execute();
  }
```

```java
/**
 * A class for a Command object that arms an alarm.  An AlarmArmCommand has an Alarm object that it arms.
 */
public class AlarmArmCommand implements Command{

    private Alarm alarm;

    /**
     * Creates a Command that can arm the given Alarm object if called upon to do so.
     *
     * @param alarm Alarm to arm if called to do so.
     */
    public AlarmArmCommand(Alarm alarm){
        this.alarm = alarm;
    }

    /**
     * Calls the Alarm's arm() method.
     */
    @Override
    public void execute() {
        this.alarm.arm();
    }

}
```

```java
/**
 * A class for a Command object that opens a GarageDoor.  A GarageOpenCommand has a GarageDoor object that it opens.
 */
public class GarageOpenCommand implements Command {

    private GarageDoor door;

    public GarageOpenCommand(GarageDoor door){
        this.door = door;
    }

    @Override
    public void execute() {
        this.door.up();
    }

}
```

```java
/**
 * A class for a Command object that turns a light on.  A LightOnCommand has a Light object that it turns on.
 */
public class LightOnCommand implements Command{

    private Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.on();
    }
}
```

Finally, we can define a `MacroCommand` object that is basically a compound of `Command`s and bundles a set of actions into a single `execute()` method.  This gives us the opportunity of having a button that performs numerous actions at once in our remote control!

```java
/**
 * A class for a Command that performs a series of given Commands when called upon to do so.
 */
public class MacroCommand implements Command{

    Command[] commands;

    /**
     * Creates a new command object that will carry out all the given Commands.
     *
     * @param commands An array of Command objects that make up the macro command.
     */
    public MacroCommand(Command[] commands){
        this.commands = commands;
    }

    /**
     * Executes all the Commands that were given to this MacroCommand when instantiated.
     */
    @Override
    public void execute() {
        for(int i = 0; i < this.commands.length; i++){
            this.commands[i].execute();
        }
    }
}
```

Now notice how the client sets the entire mechanism in motion by passing command objects to the invoker.  These commands incapsulate the actions from the user.  Also, **the invoker is completely unaware of the receivers that have been programmed into the commands and how they work**.

```java
public class Client {

    public static void main(String[] args) {

        // create the receivers of the remote
        GarageDoor garageDoor = new GarageDoor();
        Alarm alarm = new Alarm();
        Light porchLight = new Light("front porch");

        // create corresponding commands and bind them up to the receivers
        GarageOpenCommand garageDoorUp = new GarageOpenCommand(garageDoor);
        AlarmArmCommand alarmArm = new AlarmArmCommand(alarm);
        LightOnCommand porchLightOn = new LightOnCommand(porchLight);
        MacroCommand comeHomeCommand = new MacroCommand(new Command[]{garageDoorUp, porchLightOn});

        
        // load commands into the invoker
        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, garageDoorUp);
        remote.setCommand(1, alarmArm);
        remote.setCommand(2, porchLightOn);
        remote.setCommand(3, comeHomeCommand);

        // use remote!
        remote.buttonPushed(0);
        remote.buttonPushed(1);
        remote.buttonPushed(2);

        remote.buttonPushed(3);
    }
}
```

```
Garage door is up.
Alarm is armed. Please exit the building in the next minute.
The light in the front porch is on.

Garage door is up.
The light in the front porch is on.
```

# Adapter Pattern

# Facade Pattern

# Template Pattern

# Iterator Pattern

Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

- `Iterable` objects give us a "dummy" object called an `Iterator` that gives all a standard method of traversing .
- The `Iterator` deals with all the messy details of tranversing the aggregate depending on its implementation.

![Iterator UML](docs/iterator-uml.png)

## Example: Song Iterator

Suppose are trying to work with two lists of songs, one is implemented in an `Array` while the other in a `HashMap`.  However, we would like to write one single program that can tranverse both lists seamlessly, after all they are both the same principle: a numbered list of Song objects.

We begin by making a simple expansion to the existing classes and binding them to the `Iterable` interface.  This involves a minimal change to the exsiting classes - defining a getter method that returns an `Iterator`.

```java
/**
 * A class for a song list implemented using a HashMap.
 */
public class MySongs implements Iterable<Song> {

    HashMap<Integer, Song> mySongs;

    public MySongs() {
        mySongs = new HashMap<Integer, Song>();

        mySongs.put(0, new Song("Green Day", "American Idiot"));
        mySongs.put(1, new Song("AC/DC", "Highway to Hell"));
        mySongs.put(2, new Song("Bon Jovi", "Livin' On a Prayer"));
    }

    @Override
    public Iterator<Song> iterator() {
        return new MySongsIterator(mySongs);
    }
}
```

```java
/**
 * A class for a list of songs implemented using an Array.
 */
public class YourSongs {

    Song[] songs;

    public YourSongs() {
        songs = new Song[3];

        songs[0] = new Song("Britney Spears", "Hit Me Baby One More Time");
        songs[1] = new Song("Aqua", "Barbie Girl");
        songs[2] = new Song("Spice Girls", "Wannabe");

    }

    public Iterator<Song> iterator(){
        return new SongsIterator(this.songs);
    }
}
```

Now the we define the concrete `Iterator` objects that whose job will be to faciliate the necessary functionality while encapsulating the iteration of their corresponding `Iterable` objects.

```java
/**
 * A class for an Iterator for a list of songs implemented using an array.
 */
public class YourSongsIterator implements Iterator<Song> {

    private Song[] songs;
    private int indexKey;

    /**
     * Takes in an array of songs and creates an iterator object for it.
     *
     * @param s Array of songs to iterate upon.
     */
    public YourSongsIterator(Song[] s) {
        this.songs = s;
        this.indexKey = 0;
    }

    /**
     * Informs the user if the end of the songs list has been reached.
     *
     * @return True iff the iterator has not reached the end of the list.
     */
    @Override
    public boolean hasNext(){
        return this.indexKey < this.songs.length;
    }

    /**
     * Returns the next song in the list, if there is one.  If the end of the list has been reached, returns null.
     *
     * @return Next song on the list if not at the end.  Else returns null.
     */
    @Override
    public Song next(){
        if (this.hasNext()){
            return this.songs[this.indexKey++];
        }
        return null;
    }
}
```

```java
/**
 * A class for an Iterator for a list of songs implemented using a hashmap.
 */
public class MySongsIterator implements Iterator<Song> {

    private HashMap<Integer, Song> songs;
    private int indexKey;

    public MySongsIterator(HashMap<Integer, Song> s) {
        this.songs = s;
        indexKey = 0;
    }

    @Override
    public boolean hasNext() {
        return this.indexKey < this.songs.size();
    }

    @Override
    public Song next() {
        return this.songs.get(indexKey++);
    }

}
```

Now notice that even though the lists have different implementation, we can work with their iterators in the exact same way:

```java
public class Client {

    public static void main(String[] args) {

        // The two playlists with different implementations
        MySongs myList =  new MySongs();
        YourSongs yourList = new YourSongs();

        // Get the two iterators for the lists
        Iterator<Song> i1 = myList.iterator();
        Iterator<Song> i2 = yourList.iterator();

        // Traverse the two lists at the same time using the same interface
        while(i1.hasNext() && i2.hasNext()){
            Song song1 = i1.next();
            Song song2 = i2.next();
            System.out.println("My next song is: " + song1.name + " by " + song1.artist);
            System.out.println("Your next song is: " + song2.name + " by " + song2.artist + "\n");
        }
    }
}
```

```
My next song is: American Idiot by Green Day
Your next song is: Hit Me Baby One More Time by Britney Spears

My next song is: Highway to Hell by AC/DC
Your next song is: Barbie Girl by Aqua

My next song is: Livin' On a Prayer by Bon Jovi
Your next song is: Wannabe by Spice Girls
```