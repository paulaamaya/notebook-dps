# Object-Oriented Design Principles

> **Encapsulation** Identify the aspects of your application that vary and separate them from what stays the same.

By encapsulating the parts that vary, you can alter or extend these without affecting the parts that don't vary.

> Program to an interface not an implementation.

In this way client code will simply need to know the behaviour of the components rather than worry about the details of their implementation.

> Favor composition over inheritance.

> Strive for loosely coupled designs between objects that interact.

Loosely coupled designs allow you to build flexible programs that can easily handle change because the interdependency between objects is minimized.

> **Open-Closed Principle** Classes should be open for extension, but closed for modification.

The goal is to allow classes whose behaviour can easily be extended without having to modify existing code.

> **Dependency Inversion Principle** Depend upon abstractions.  Do not depend upon concrete classes.

High-level components should not depend on low-level components.  Both should depend on abstractions.

> **Least Knowledge Principle** Talk only to your immediate friends.

When you build dependencies between many classes, you are building a fragile system that will be hard to understand and costly to maintain.

As a general rule take a class, and for any method in that class, we should only invoke methods that belong to:

- The class itself
- Class attributes
- Objects passed as parameters to the method
- Objects instantiated by the method

This is a class that adheres to the principle of least knowledge:

```java
public class Car {
    Engine engine;
    
    public Car() { ... }
    
    public void start(Key key){
        // Object instantiated by method -> Door methods valid
        Doors doors = new Doors();
        
        // Object passed as parameter -> Key methods valid
        boolean authorized = key.turns();
        
        if (authorized){
            // Instance variable -> Engine methods valid
            this.engine.start();
            // Class methods valid
            this.updateDashboard();
            doors.lock();
        }
    }
    
    public void updateDashboard() { ... }
}
```

This is a class that does not adhere to the principle of least knowledge:

```java
public class WeatherReport {
    WeatherStation station;
    
    public float getTemp() {
        // We get the object from a foreign method
        Thermometer thermometer = this.station.getThermometer();
        // Then we call a method on the returned object
        thermometer.getTemp();
    }
}
```

> **The Hollywood Principle** Don't call us, we'll call you.

Allow low-level components to hook into a system, but let the high-level components determine when they are needed and how.

> **Single Responsibility Principle** A class should have only one reason to change.

Each responsibility of a class is an area of potential change; more than one responsibility means more than one area of change.

Be diligent in examining your designs and be on the alert for classes that are changing in more than one way as the system grows.


