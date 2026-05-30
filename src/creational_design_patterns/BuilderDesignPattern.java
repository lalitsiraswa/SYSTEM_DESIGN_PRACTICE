package creational_design_patterns;

import java.util.List;

// BurgerMeal:-
// BUN
// PATTY

// Bread
// Cheese
// Sauce
// Veggies
// Extra Toppings

//class BurgerMeal{
//    private String bun;
//    private String patty;
//    // If we need extra things on burger we have to pass those as a part of constructor.
//    // This makes our constructor very horrible but what if we have 20 options, it will be worst and
//    // hard to manage, like if user don't want an item we have to pass 'null' for that.
//    public BurgerMeal(String bun, String patty, String Cheese, String Sauce, String Veggies){
//        this.bun = bun;
//        this.patty = patty;
//    }
//}


class BurgerMeal{
    // Required
    private final String bunType;
    private final String patty;

    // Optional
    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;
    private final String drink;

    // Private Constructor
    private BurgerMeal(BurgerBuilder builder){
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
        this.drink = builder.drink;
    }

    public static class BurgerBuilder{
        // Required
        private final String bunType;
        private final String patty;

        // Optional
        private boolean hasCheese;
        private List<String> toppings;
        private String side;
        private String drink;

        public BurgerBuilder(String bunType, String patty){
            this.bunType = bunType;
            this.patty = patty;
        }

        public BurgerBuilder withCheese(boolean hasCheese){
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withToppings(List<String> toppings){
            this.toppings = toppings;
            return this;
        }

        public BurgerBuilder withSide(String side){
            this.side = side;
            return this;
        }

        public BurgerBuilder withDrink(String drink){
            this.drink = drink;
            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }

    @Override
    public String toString() {
        return "BurgerMeal{" +
                "bunType='" + bunType + '\'' +
                ", patty='" + patty + '\'' +
                ", hasCheese=" + hasCheese +
                ", toppings=" + toppings +
                ", side='" + side + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}

public class BuilderDesignPattern {
    static void main() {
        BurgerMeal burgerMeal = new BurgerMeal.BurgerBuilder("Wheat", "Veg").build();
        System.out.println(burgerMeal.toString());
        BurgerMeal burgerMeal1WithCheese = new BurgerMeal.BurgerBuilder("Wheat", "Veg").withCheese(true).build();
        System.out.println(burgerMeal1WithCheese.toString());
        BurgerMeal burgerMeal1WithCheeseAndDrink = new BurgerMeal.BurgerBuilder("Wheat", "Non-Veg").withCheese(true).withDrink("Sprite").build();
        System.out.println(burgerMeal1WithCheeseAndDrink);
    }
}
