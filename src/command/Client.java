package command;

public class Client {

    public static void main(String[] args) {

        // create the receivers of the remote
        GarageDoor garageDoor = new GarageDoor();
        Alarm alarm = new Alarm();
        Light porchLight = new Light("front porch");

        // create commands and hook them up to the receivers
        GarageOpenCommand garageDoorUp = new GarageOpenCommand(garageDoor);
        AlarmArmCommand alarmArm = new AlarmArmCommand(alarm);
        LightOnCommand porchLightOn = new LightOnCommand(porchLight);
        MacroCommand comeHomeCommand = new MacroCommand(new Command[]{garageDoorUp, porchLightOn});

        // load commands ino the remote
        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, garageDoorUp);
        remote.setCommand(1, alarmArm);
        remote.setCommand(2, porchLightOn);
        remote.setCommand(3, comeHomeCommand);

        // use remote!
        remote.buttonPushed(0);
        remote.buttonPushed(1);
        remote.buttonPushed(2);
        remote.buttonPushed(3);
    }
}
