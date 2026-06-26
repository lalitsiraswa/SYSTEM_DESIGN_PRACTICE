package behavioral_design_patterns;

class Tea {
    public void prepare() {
        boilWater();
        addTeaLeaves();
        pourIntoCup();
        addSugar();
    }
    private void boilWater() {
        System.out.println("Boiling Water");
    }
    private void addTeaLeaves() {
        System.out.println("Adding Tea Leaves");
    }
    private void pourIntoCup() {
        System.out.println("Pouring into Cup");
    }
    private void addSugar() {
        System.out.println("Adding Sugar");
    }
}

class Coffee {
    public void prepare() {
        boilWater();
        addCoffee();
        pourIntoCup();
        addSugar();
    }
    private void boilWater() {
        System.out.println("Boiling Water");
    }
    private void addCoffee() {
        System.out.println("Adding Coffee Powder");
    }
    private void pourIntoCup() {
        System.out.println("Pouring into Cup");
    }
    private void addSugar() {
        System.out.println("Adding Sugar");
    }
}
// Problem
// Duplicate code:
// boilWater()
// pourIntoCup()
// addSugar()
// appears in every class.

public class TemplateMethodDesignPatternCHATGPT {
    static void main() {

    }
}
