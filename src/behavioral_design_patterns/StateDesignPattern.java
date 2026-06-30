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

// SOLUTION

class OrderContext{
    private OrderState currentState;
    public OrderContext(){
        this.currentState = new OrderPlacedState(); // Default state
    }
    public void setState(OrderState state){
        this.currentState = state;
    }
    public void next(){
        this.currentState.next(this);
    }
    public void cancel(){
        this.currentState.cancel(this);
    }
    public String getCurrentState(){
        return this.currentState.getStateName();
    }
}

interface OrderState{
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getStateName();
}

class OrderPlacedState implements OrderState{
    @Override
    public void next(OrderContext context) {
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared.");
    }
    @Override
    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }
    @Override
    public String getStateName() {
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState{
    @Override
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery.");
    }
    @Override
    public void cancel(OrderContext context) {
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }
    @Override
    public String getStateName() {
        return "PREPARING";
    }
}

class OutForDeliveryState implements OrderState{
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered.");
    }
    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel. Order is out for delivery.");
    }
    @Override
    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState{
    @Override
    public void next(OrderContext context) {
        System.out.println("Order is already delivered");
    }
    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel a delivered order.");
    }
    @Override
    public String getStateName() {
        return "DELIVERED";
    }
}

class CancelledState implements OrderState{
    @Override
    public void next(OrderContext context) {
        System.out.println("Cancelled order cannot move to next state.");
    }
    @Override
    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }
    @Override
    public String getStateName() {
        return "CANCELLED";
    }
}

public class StateDesignPattern {
    static void main() {
        OrderContext order = new OrderContext();
        System.out.println("Current state: " + order.getCurrentState());
        order.next(); // Preparing
        order.next(); // Out for delivery
        order.cancel(); // Should fail
        order.next(); // Delivered
        order.next(); // Should fail
    }
}
