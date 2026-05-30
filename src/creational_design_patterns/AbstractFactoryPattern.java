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
//class CheckoutService{
//    private String gatewayType;
//    public CheckoutService(String gatewayType){
//        this.gatewayType = gatewayType;
//    }
//
//    public void checkout(double amount){
//        if(gatewayType.equals("razorpay")){
//            PaymentGateway paymentGateway = new RazorpayGateway();
//            paymentGateway.processPayment(amount);
//        }
//        else if(gatewayType.equals("payu")){
//            PaymentGateway paymentGateway = new PayUGateway();
//            paymentGateway.processPayment(amount);
//        }
//        Invoice invoice = new GSTInvoice();
//        invoice.generateInvoice();
//    }
//}

class IndiaFactory{
    public static PaymentGateway createPaymentGateway(String gatewayType) throws IllegalAccessException {
        return switch (gatewayType.toLowerCase()) {
            case "razorpay" -> new RazorpayGateway();
            case "payu" -> new PayUGateway();
            default -> throw new IllegalAccessException("UnSupported payment gateway in india: " + gatewayType);
        };
    }

    public static Invoice createInvoice(){
        return new GSTInvoice();
    }
}
// Now think we have to dimplement the CheckoutService for US also.- PROBLEM
// Whenever we have to deal with multiple factories and object creations, then we have to use Abstract Factory Pattern.
class CheckoutService{
    private String gatewayType;
    private String countryCode;
    public CheckoutService(String gatewayType, String countryCode){
        this.gatewayType = gatewayType;
        this.countryCode = countryCode;
    }

    public void checkout(double amount) throws IllegalAccessException {
        // We can use if else condition, but it will again break the SOLID principles.
        if(countryCode == "INDIA") {
            PaymentGateway paymentGateway = IndiaFactory.createPaymentGateway(gatewayType);
            paymentGateway.processPayment(amount);
            Invoice invoice = IndiaFactory.createInvoice();
            invoice.generateInvoice();
        }
        else if(countryCode == "US"){
            // DO THIS
        }
    }
}

public class AbstractFactoryPattern {
    static void main() {

    }
}
