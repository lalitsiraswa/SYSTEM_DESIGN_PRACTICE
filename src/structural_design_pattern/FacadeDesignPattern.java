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

class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservedService seatReservedService;
    private NotificationService notificationService;
    private LoyaltyPointService loyaltyPointService;
    private TicketService ticketService;

    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.seatReservedService = new SeatReservedService();
        this.notificationService = new NotificationService();
        this.loyaltyPointService = new LoyaltyPointService();
        this.ticketService = new TicketService();
    }

    // Here we can use builder pattern
    public void bookMovieTicket(String accountId, String movieId, String seatNumber, String userEmail, double amount){
        paymentService.makePayment(accountId, amount);
        seatReservedService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        loyaltyPointService.addPoints(accountId, 760);
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully!");
    }
}

class MovieBookingFacadeWithBuilderPattern{
    private final String accountId;
    private final String movieId;
    private final String seatNumber;
    private final String userEmail;
    private final double amount;
    private final int points;
    private PaymentService paymentService;
    private SeatReservedService seatReservedService;
    private NotificationService notificationService;
    private LoyaltyPointService loyaltyPointService;
    private TicketService ticketService;

    public MovieBookingFacadeWithBuilderPattern(MovieBookingFacadeWithBuilder bookingFacadeWithBuilder){
        this.accountId = bookingFacadeWithBuilder.accountId;
        this.movieId = bookingFacadeWithBuilder.movieId;
        this.seatNumber = bookingFacadeWithBuilder.seatNumber;
        this.userEmail = bookingFacadeWithBuilder.userEmail;
        this.amount = bookingFacadeWithBuilder.amount;
        this.points = 760;
        this.paymentService = new PaymentService();
        this.seatReservedService = new SeatReservedService();
        this.notificationService = new NotificationService();
        this.loyaltyPointService = new LoyaltyPointService();
        this.ticketService = new TicketService();
    }

    public static class MovieBookingFacadeWithBuilder{
        private String accountId;
        private String movieId;
        private String seatNumber;
        private String userEmail;
        private double amount;

        public MovieBookingFacadeWithBuilder setAccountID(String accountId){
            this.accountId = accountId;
            return this;
        }

        public MovieBookingFacadeWithBuilder setMovieId(String movieId){
            this.movieId = movieId;
            return this;
        }

        public MovieBookingFacadeWithBuilder setSeatNumber(String seatNumber){
            this.seatNumber = seatNumber;
            return this;
        }

        public MovieBookingFacadeWithBuilder setUserEmail(String userEmail){
            this.userEmail = userEmail;
            return this;
        }

        public MovieBookingFacadeWithBuilder setAmount(double amount){
            this.amount = amount;
            return this;
        }

        public MovieBookingFacadeWithBuilderPattern build(){
            return new MovieBookingFacadeWithBuilderPattern(this);
        }
    }

    // Here we can use builder pattern
    public void bookMovieTicket(){
        paymentService.makePayment(accountId, amount);
        seatReservedService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        loyaltyPointService.addPoints(accountId, 760);
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully!");
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
        System.out.println();
        // Using MovieBookingFacade
        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovieTicket("lalitsiraswa", "movie3108", "F10", "lalitsiraswa@gmail.com", 599);
        System.out.println();
        // Using MovieBookingFacadeWithBuilderPattern
        MovieBookingFacadeWithBuilderPattern facadeWithBuilderPattern = new MovieBookingFacadeWithBuilderPattern.MovieBookingFacadeWithBuilder()
                .setAccountID("lalitsiraswa")
                .setMovieId("movie3108")
                .setSeatNumber("F10")
                .setUserEmail("lalitsiraswa@gmail.com")
                .setAmount(599)
                .build();
        facadeWithBuilderPattern.bookMovieTicket();
    }
}
