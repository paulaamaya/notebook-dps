package command;

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
