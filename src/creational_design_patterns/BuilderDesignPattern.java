package creational_design_patterns;

// BurgerMeal:-
// BUN
// PATTY

// Bread
// Cheese
// Sauce
// Veggies
// Extra Toppings

class BurgerMeal{
    private String bun;
    private String patty;
    // If we need extra things on burger we have to pass those as a part of constructor.
    // This makes our constructor very horrible but what if we have 20 options, it will be worst and
    // hard to manage, like if user don't want an item we have to pass 'null' for that.
    public BurgerMeal(String bun, String patty, String Cheese, String Sauce, String Veggies){
        this.bun = bun;
        this.patty = patty;
    }
}

public class BuilderDesignPattern {
    static void main() {
        BurgerMeal burgerMeal = new BurgerMeal("Wheat", "Veg");
    }
}
