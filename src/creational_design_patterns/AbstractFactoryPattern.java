package creational_design_patterns;

interface PaymentGateway{
    void processPayment(double amount);
}

class RazorpayGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: ₹" + amount);
    }
}

class PayUGateway implements PaymentGateway{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: ₹" + amount);
    }
}

interface Invoice{
    void generateInvoice();
}

class GSTInvoice implements Invoice{
    @Override
    public void generateInvoice() {
        System.out.println("Generating GST invoice for India.");
    }
}

// Currently it is violating the Single Responsible Principle of the SOLID principles.
// Because currently it has three responsibilities:
// 1. Handling the object creation logic.
// 2. Processing the payments.
// 3. Generating the invoices.
class CheckoutService{
    private String gatewayType;
    public CheckoutService(String gatewayType){
        this.gatewayType = gatewayType;
    }

    public void checkout(double amount){
        if(gatewayType.equals("razorpay")){
            PaymentGateway paymentGateway = new RazorpayGateway();
            paymentGateway.processPayment(amount);
        }
        else if(gatewayType.equals("payu")){
            PaymentGateway paymentGateway = new PayUGateway();
            paymentGateway.processPayment(amount);
        }
        Invoice invoice = new GSTInvoice();
        invoice.generateInvoice();
    }
}

public class AbstractFactoryPattern {
    static void main() {

    }
}
