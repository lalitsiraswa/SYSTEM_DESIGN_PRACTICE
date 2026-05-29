package creational_design_patterns;

interface Logistics{
    void transport();
}

class Road implements Logistics{
    @Override
    public void transport() {
        System.out.println("Transporting by road logic!");
    }
}

class Air implements Logistics{
    @Override
    public void transport() {
        System.out.println("Transporting by air logic!");
    }
}

// Currently the LogisticsService class is violating the Single Responsibility Principle.
// It should be only responsible for transporting the Item, but currently it is also dealing with object creation.
// This causes:
// > tight coupling
// > repeated code
// >difficult maintenance
class LogisticsService{
    public void transport(String mode){
        if(mode.equals("Air")){
            Logistics logistics = new Air();
            logistics.transport();
        }
        else if(mode.equals("Road")){
            Logistics logistics = new Road();
            logistics.transport();
        }
    }
}

public class FactoryDesignPattern {
    static void main() {
        LogisticsService logisticsService = new LogisticsService();
        logisticsService.transport("Air");
        logisticsService.transport("Road");
    }
}
