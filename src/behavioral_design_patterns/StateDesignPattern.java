package behavioral_design_patterns;

// Food Delivery Application
class Order{
    private String state;
    public Order(){
        this.state = "ORDER_PLACED";
    }
    public void cancelOrder(){
        if(state.equals("ORDER_PLACED") || state.equals("PREPARING")){
            state = "CANCELLED";
            System.out.println("Order has been cancelled!");
        }
        else{
            System.out.println("Cannot cancel the order now.");
        }
    }
    // PROBLEM - It is not scalable
    // We have all the business ligic under same class like: ORDER_PLACE, PREPARING, OUT_FOR_DELIVERY.
    // It is breaking single responsibility principle.
    // Let's say tomorrow if new state introduces we have to change out existing class that will break Open-Close Principle
    public void nextState(){
        switch (state){
            case "ORDER_PLACED":
                state = "PREPARING";
                break;
            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;
            default:
                System.out.println("No next state from: " + state);
        }
    }
    public String getState(){
        return state;
    }
}

public class StateDesignPattern {
    static void main() {

    }
}
