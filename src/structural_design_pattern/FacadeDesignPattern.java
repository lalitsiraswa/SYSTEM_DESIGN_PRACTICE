package structural_design_pattern;

// Book MyShow

class PaymentService{
    public void makePayment(String accountId, double amount){
        System.out.println("Payment of ₹" + amount + " successful for account " + accountId);
    }
}

class SeatReservedService{
    public void reserveSeat(String movieId, String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}

class NotificationService{
    public void sendBookingConfirmation(String userEmail){
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointService{
    public void addPoints(String accountId, int points){
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

class TicketService{
    public void generateTicket(String movieId, String seatNumber){
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}

public class FacadeDesignPattern {
    static void main() {
        // Booking a movie ticket manually - PROBLEM - Client itself is handling all the services, and we don't want this.
        PaymentService paymentService = new PaymentService();
        paymentService.makePayment("lalitsiraswa", 599);
        SeatReservedService seatReservedService = new SeatReservedService();
        seatReservedService.reserveSeat("movie3108", "F10");
        NotificationService notificationService = new NotificationService();
        notificationService.sendBookingConfirmation("lalitsiraswa@gmail.com");
        LoyaltyPointService loyaltyPointService = new LoyaltyPointService();
        loyaltyPointService.addPoints("lalitsiraswa", 760);
        TicketService ticketService = new TicketService();
        ticketService.generateTicket("movie3108", "F10");
    }
}
