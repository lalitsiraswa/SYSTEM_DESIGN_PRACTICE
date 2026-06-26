package behavioral_design_patterns;

// PROBLEM
// It is not scalable, It is violating a lot of SOLID principles

import java.util.Stack;

// RECEIVER
class Light{
    public void on(){
        System.out.println("Light turned ON");
    }
    public void off(){
        System.out.println("Light turned OFF");
    }
}
// RECEIVER
class AC{
    public void on(){
        System.out.println("AC turned ON");
    }
    public void off(){
        System.out.println("AC turned OFF");
    }
}
// Now we can easily add a new device like Fan
class Fan{
    public void on(){
        System.out.println("Fan turned ON");
    }
    public void off(){
        System.out.println("Fan turned OFF");
    }
}

// Currently, this "NaiveRemoteControl" is tightly coupled with devices i.e (Light and AC).
// And we have individual method for each device(AC, LIGHT), if tomorrow a new device have to add, we have to make changes to this class which is
// violating the Single Responsibility Principle. And it is not scalable also as we have to write the method for that device too.
class NaiveRemoteControl{
    private Light light;
    private AC ac;
    private String lastAction = "";

    public NaiveRemoteControl(Light light, AC ac){
        this.light = light;
        this.ac = ac;
    }
    public void pressLightOn(){
        light.on();
        lastAction = "LIGHT_ON";
    }
    public void pressLightOff(){
        light.off();
        lastAction = "LIGHT_OFF";
    }
    public void pressACOn(){
        ac.on();
        lastAction = "AC_ON";
    }
    public void pressACOff(){
        ac.off();
        lastAction = "AC_OFF";
    }
    // Undo is Not Scalable
    public void pressUndo(){
        switch (lastAction){
            case "LIGHT_ON": light.off(); lastAction = "LIGHT_OFF"; break;
            case "LIGHT_OFF": light.on(); lastAction = "LIGHT_ON"; break;
            case "AC_ON": ac.off(); lastAction = "AC_OFF"; break;
            case "AC_OFF": ac.on(); lastAction = "AC_ON"; break;
            default: System.out.println("No action to undo");
        }
    }
}

// SOLUTION
// Commands
interface Command{
    void execute();
    void undo();
}
// Concrete Commands
class LightOnCommand implements Command{
    private Light light;
    public LightOnCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.on();
    }
    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command{
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }
    @Override
    public void undo() {
        light.on();
    }
}

class ACOnCommand implements Command{
    private AC ac;
    public ACOnCommand(AC ac){
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.on();
    }
    @Override
    public void undo() {
        ac.off();
    }
}

class ACOffCommand implements Command{
    private AC ac;
    public ACOffCommand(AC ac){
        this.ac = ac;
    }
    @Override
    public void execute() {
        ac.off();
    }
    @Override
    public void undo() {
        ac.on();
    }
}
class FanOnCommand implements Command{
    private Fan fan;
    public FanOnCommand(Fan fan){
        this.fan = fan;
    }
    @Override
    public void execute() {
        fan.on();
    }
    @Override
    public void undo() {
        fan.off();
    }
}

class FanOffCommand implements Command{
    private Fan fan;
    public FanOffCommand(Fan fan){
        this.fan = fan;
    }
    @Override
    public void execute() {
        fan.off();
    }
    @Override
    public void undo() {
        fan.on();
    }
}
// Invoker
// RemoteControl doesn't know anything about the device(LIGHT, AC) or Function(ON, OFF).
class RemoteControl{
    private Command[] buttons = new Command[4];
    private Stack<Command> commandHistory = new Stack<>();

    public void setCommand(int slot, Command command){
        buttons[slot] = command;
    }
    public void pressButton(int slot){
        if(buttons[slot] != null){
            buttons[slot].execute();
            commandHistory.push(buttons[slot]);
        }
        else{
            System.out.println("No command assigned to slot " + slot);
        }
    }
    public void pressUndo(){
        if(!commandHistory.isEmpty()){
            commandHistory.pop().undo();
        }
        else{
            System.out.println("No commands to undo.");
        }
    }
}

// Four Key Components
// - Client
// - Invoker
// - Command
// - Receiver
// We introduced the Command Design Pattern to make sure that they are not tightly coupled.
public class CommandDesignPattern {
    static void main() {
        Light light = new Light();
        AC ac = new AC();
        NaiveRemoteControl remoteControl = new NaiveRemoteControl(light, ac);
        remoteControl.pressLightOn();
        remoteControl.pressACOn();
        remoteControl.pressLightOff();
        remoteControl.pressUndo(); // Should undo LIGHT_OFF => LIGHT_ON
        remoteControl.pressUndo(); // Should undo AC_ON => AC_OFF

        System.out.println();
        // SOLUTION
        Light light1 = new Light();
        AC ac1 = new AC();

        Command lightOn = new LightOnCommand(light1);
        Command lightOff = new LightOffCommand(light1);
        Command acOn = new ACOnCommand(ac1);
        Command acOff = new ACOffCommand(ac1);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, lightOn);
        remote.setCommand(1, lightOff);
        remote.setCommand(2, acOn);
        remote.setCommand(3, acOff);

        remote.pressButton(0); // LIGHT ON
        remote.pressButton(2); // AC ON
        remote.pressButton(1); // LIGHT OFF
        remote.pressUndo(); // UNDO LIGHT OFF => LIGHT ON
        remote.pressUndo(); // UNDO AC ON =? AC OFF
    }
}
// What happens without the Command Design Pattern?
// - Tight coupling between invoker(Remote) and receiver(Fan, AC, Light)
// - No reusability or abstraction for actions.
// - Undo/Redo or any other operation is poorly supported.
// - Hard to implement BATCH(Night Mode) actions.(Hard to implement multiple actions at once like(Night Mode - LIGHT OFF, AC ON, FAN OFF)).
// - Scalability breaks down.
