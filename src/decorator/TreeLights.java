package decorator;

public class TreeLights extends Decorator{

    public TreeLights(ChristmasTree wrappee) {
        super(wrappee);
    }

    @Override
    public String decorate() {
        return this.wrappee.decorate() + " with lights";
    }
}
