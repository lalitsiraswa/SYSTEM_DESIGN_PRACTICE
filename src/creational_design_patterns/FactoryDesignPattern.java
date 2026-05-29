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

class Train implements Logistics{
    @Override
    public void transport() {
        System.out.println("Transporting by train logic!");
    }
}

// Currently the LogisticsService class is violating the Single Responsibility Principle.
// It should be only responsible for transporting the Item, but currently it is also dealing with object creation.
// This causes:
// > tight coupling
// > repeated code
// >difficult maintenance
//class LogisticsService{
//    public void transport(String mode){
//        if(mode.equals("Air")){
//            Logistics logistics = new Air();
//            logistics.transport();
//        }
//        else if(mode.equals("Road")){
//            Logistics logistics = new Road();
//            logistics.transport();
//        }
//    }
//}

// Following SOLID Principles
// Open Close Principle
class LogisticsFactory{
    public static Logistics getLogistics(String mode){
        if(mode.equals("Road")){
            return new Road();
        }
        else if(mode.equals("Air")){
            return new Air();
        }
        else if(mode.equals("Train")){
            return new Train();
        }
        return null;
    }
}

// Following SOLID Principles
// Single Responsibility Principle
class LogisticsService{
    public void transport(String mode){
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.transport();
    }
}

public class FactoryDesignPattern {
    static void main() {
        LogisticsService logisticsService = new LogisticsService();
        logisticsService.transport("Air");
        logisticsService.transport("Road");
        logisticsService.transport("Train");
    }
}
