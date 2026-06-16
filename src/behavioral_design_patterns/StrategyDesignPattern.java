package behavioral_design_patterns;
// PROBLEM - Imagine you're booking a ride.
// You can pay using:
// UPI
// Credit Card
// PayPal
// Net Banking
// The ride booking system doesn't care how payment is made.
// It simply calls: paymentStrategy.pay(amount);
// The actual payment method depends on the strategy chosen by the user.

class PaymentService{
    public void pay(String paymentType, double amount){
        if(paymentType.equals("UPI")){
            System.out.println("Paying via UPI");
        }
        else if(paymentType.equals("CARD")){
            System.out.println("Paying via Credit Card");
        }
        else if(paymentType.equals("PAYPAL")){
            System.out.println("Paying via PayPal");
        }
    }
}
// Problems
// Huge if-else chain, Violates Open/Closed Principle
// As new payment methods arrive:
// UPI
// CARD
// PAYPAL
// APPLE PAY
// GOOGLE PAY
// CRYPTO
public class StrategyDesignPattern {
    static void main() {
        PaymentService paymentService = new PaymentService();
        paymentService.pay("UPI", 9999);
    }
}
