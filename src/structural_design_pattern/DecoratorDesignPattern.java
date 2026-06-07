package structural_design_pattern;

// Problem
//class PlainPizza{}
//class CheesePizza extends PlainPizza{}
//class OlivePizza extends PlainPizza{}
//class StuffedPizza extends PlainPizza{}
//class CheeseStuffedPizza extends PlainPizza{}
//class CheeseOlivePizza extends PlainPizza{}
//class CheeseOliveStuffedPizza{}

// Solution
interface Pizza{
    String getDescription();
    double getCost();
}

// Concrete Component / Base Pizza
class PlainPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 100.0;
    }
}

// Concrete Component / Base Pizza
class MargheritaPizza implements Pizza{
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.0;
    }
}

// Decorator Abstract Class
abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;
    public PizzaDecorator(Pizza pizza){
        this.pizza = pizza; // HAS-A Relationship
    }
}

// Concrete Decorator
class ExtraChees extends PizzaDecorator{
    public ExtraChees(Pizza pizza){
        super(pizza);
    }
    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 40.0;
    }
}

// Concrete Decorator
class Olives extends PizzaDecorator{
    public Olives(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 30.0;
    }
}

class StuffedCrust extends PizzaDecorator{
    public StuffedCrust(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Stuffed Crust";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 50.0;
    }
}

public class DecoratorDesignPattern {
    static void main() {
        Pizza margheritaPizzaWithExtraCheese = new ExtraChees(new MargheritaPizza());
        System.out.println(margheritaPizzaWithExtraCheese.getDescription() + " : " + margheritaPizzaWithExtraCheese.getCost());
        Pizza plainPizzaWithExtraCheese = new ExtraChees(new PlainPizza());
        System.out.println(plainPizzaWithExtraCheese.getDescription() + " : " + plainPizzaWithExtraCheese.getCost());
        Pizza margheritaPizzaWithExtraCheeseAndOlives = new Olives(margheritaPizzaWithExtraCheese);
        System.out.println(margheritaPizzaWithExtraCheeseAndOlives.getDescription() + " : " + margheritaPizzaWithExtraCheeseAndOlives.getCost());
        Pizza margheritaPizzaWithOlivesAndStuffedCrust = new StuffedCrust(new Olives(new MargheritaPizza()));
        System.out.println(margheritaPizzaWithOlivesAndStuffedCrust.getDescription() + " : " + margheritaPizzaWithOlivesAndStuffedCrust.getCost());
        Pizza margheritaPizzaWithStuffedCrustAndExtraCheese = new ExtraChees(new StuffedCrust(new MargheritaPizza()));
        System.out.println(margheritaPizzaWithStuffedCrustAndExtraCheese.getDescription() + " : " + margheritaPizzaWithStuffedCrustAndExtraCheese.getCost());
    }
}
