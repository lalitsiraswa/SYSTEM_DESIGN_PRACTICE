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

// SOLUTION
abstract class Beverage {
    // Template Method
    public final void prepare() {
        boilWater();
        addMainIngredient();
        pourIntoCup();
        addSugar();
    }
    private void boilWater() {
        System.out.println("Boiling Water");
    }
    private void pourIntoCup() {
        System.out.println("Pouring into Cup");
    }
    private void addSugar() {
        System.out.println("Adding Sugar");
    }
    protected abstract void addMainIngredient();
}

class TeaClone extends Beverage {
    @Override
    protected void addMainIngredient() {
        System.out.println("Adding Tea Leaves");
    }
}
class CoffeeClone extends Beverage {
    @Override
    protected void addMainIngredient() {
        System.out.println("Adding Coffee Powder");
    }
}
public class TemplateMethodDesignPatternCHATGPT {
    static void main() {
        Beverage tea = new TeaClone();
        tea.prepare();
        System.out.println();
        Beverage coffee = new CoffeeClone();
        coffee.prepare();
    }
}
