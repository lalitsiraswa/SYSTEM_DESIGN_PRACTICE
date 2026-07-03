package behavioral_design_patterns;
// Think of it like this:
// Request
//   |
//   v
// Handler 1
//   |
//   v
// Handler 2
//   |
//   v
// Handler 3
//   |
//   v
// Handled

// In a customer support system for an e-commerce platform like Amazon, Users raise tickets. These tickets could be:
// General inquiries
// Refund request
// Technical issues
// Complaints about delivery

class SupportService{
    public void handleRequest(String type){
        if(type.equals("GENERAL")){
            System.out.println("Handled by General Support.");
        }
        else if(type.equals("REFUND")){
            System.out.println("Handled by Billing Team.");
        }
        else if(type.equals("TECHNICAL")){
            System.out.println("Handled by Technical Support.");
        }
        else if(type.equals("DELIVERY")){
            System.out.println("Handled by Delivery Team.");
        }
        else{
            System.out.println("No handler available.");
        }
    }
}

// PROBLEM
// Violates Open-Closed Principle: Every time a new type is added, the method must be edited/modify.
// Violates Single Responsibility Principle: Single class containing all the handling logic (GENERAL, REFUND, TECHNICAL, DELIVERY).
// Monolithic code: All logic in a single class.
// Not flexible or scalable: Cannot change the order of processing without modifying core logic.

// SOLUTION
abstract class SupportHandler{
    protected SupportHandler nextHandler;
    public void setNextHandler(SupportHandler nextHandler){
        this.nextHandler = nextHandler;
    }
    public abstract void handleRequest(String requestType);
}

class GeneralSupport extends SupportHandler{
    @Override
    public void handleRequest(String requestType) {
        if(requestType.equals("GENERAL")){
            System.out.println("GeneralSupport: Handling general query");
        }
        else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}

class BillingSupport extends SupportHandler{
    @Override
    public void handleRequest(String requestType) {
        if(requestType.equals("REFUND")){
            System.out.println("BillingSupport: Handling refund request");
        }
        else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}

class TechnicalSupport extends SupportHandler{
    @Override
    public void handleRequest(String requestType) {
        if(requestType.equals("TECHNICAL")){
            System.out.println("TechnicalSupport: Handling technical issue");
        }
        else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
    }
}

class DeliverySupport extends SupportHandler{
    @Override
    public void handleRequest(String requestType) {
        if(requestType.equals("DELIVERY")){
            System.out.println("DeliverySupport: Handling delivery issue");
        }
        else if(nextHandler != null){
            nextHandler.handleRequest(requestType);
        }
        else{
            System.out.println("DeliverySupport: No handler found for request");
        }
    }
}


public class ChainOfResponsibilityDesignPattern {
    static void main() {
        SupportHandler general = new GeneralSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler technical = new TechnicalSupport();
        SupportHandler delivery = new DeliverySupport();

        // Setting up chain: general -> billing -> technical -> delivery
        general.setNextHandler(billing);
        billing.setNextHandler(technical);
        technical.setNextHandler(delivery);

        general.handleRequest("REFUND");
        general.handleRequest("DELIVERY");
        general.handleRequest("TECHNICAL");
        general.handleRequest("UNKNOWN");
    }
}
