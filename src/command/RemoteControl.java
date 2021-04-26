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
