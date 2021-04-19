package decorator;

public abstract class Decorator implements ChristmasTree{

    public ChristmasTree wrappee;
    
    public Decorator(ChristmasTree wrappee){
        this.wrappee = wrappee;
    }
}
