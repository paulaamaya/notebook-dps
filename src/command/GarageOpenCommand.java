package command;

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
