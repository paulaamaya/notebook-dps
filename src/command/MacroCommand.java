package command;

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
