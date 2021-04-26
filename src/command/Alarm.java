package command;

/**
 * A class for a house alarm.  A house alarm can be armed or disarmed.
 */
public class Alarm {

    public void arm(){
        System.out.println("Alarm is armed. Please exit the building in the next minute.");
    }

    public void disarm(){
        System.out.println("Alarm is disarmed.");
    }
}
