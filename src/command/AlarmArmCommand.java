package command;

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
