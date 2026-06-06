package structural_design_pattern;

interface PaymentGateway{
    void pay(String orderId, double amount);
}

class PayUGateway implements PaymentGateway{
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid ₹" + amount + " using PayU for order: " + orderId);
    }
}

class CheckoutService{
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount){
        paymentGateway.pay(orderId, amount);
    }
}

// For now, it is fine, but let assume after some time we decide to use RazorpayAPI instead of PayU, then it will cause issue and
// RazorpayAPI looks like the below as
class RazorpayAPI{
    public void makePayment(String invoiceID, double amountInRupees){
        System.out.println("Paid ₹" + amountInRupees + " using Razorpay for invoice: " + invoiceID);
    }
}

class RazorpayAdapter implements PaymentGateway{
    private RazorpayAPI razorpayAPI;

    public RazorpayAdapter(RazorpayAPI razorpayAPI){
        this.razorpayAPI = razorpayAPI;
    }

    @Override
    public void pay(String orderId, double amount) {
        razorpayAPI.makePayment(orderId, amount);
    }
}

// If we are planning to shift to Razorpay it should be easy, but currently it is hard, the RazorpayAPI is not implementing the PaymentGateway, it
// has its own method.
public class AdapterDesignPattern {
    static void main() {
        // CheckoutService checkoutServicePayU = new CheckoutService(new PayUGateway());
        PaymentGateway paymentGateway = new RazorpayAdapter(new RazorpayAPI());
        CheckoutService checkoutService = new CheckoutService(paymentGateway);
        checkoutService.checkout("3108", 1999);
    }
}
