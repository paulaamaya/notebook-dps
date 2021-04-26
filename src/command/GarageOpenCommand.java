package command;

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
