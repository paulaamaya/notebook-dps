package command;

public class AlarmArmCommand implements Command{

    private Alarm alarm;

    public AlarmArmCommand(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        this.alarm.arm();
    }

}
