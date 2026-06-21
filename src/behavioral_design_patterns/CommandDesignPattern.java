package behavioral_design_patterns;

// PROBLEM
// It is not scalable, It is violating a lot of SOLID principles

class Light{
    public void on(){
        System.out.println("Light turned ON");
    }
    public void off(){
        System.out.println("Light turned OFF");
    }
}
class AC{
    public void on(){
        System.out.println("AC turned ON");
    }
    public void off(){
        System.out.println("AC turned OFF");
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
    }
}
