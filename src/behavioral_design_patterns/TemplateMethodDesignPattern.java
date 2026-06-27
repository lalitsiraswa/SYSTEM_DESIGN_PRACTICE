package behavioral_design_patterns;

class EmailNotification{
    public void send(String to, String message){
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating email recipient: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);
        // Compose Email
        String composedMessage = "<html><body<p>" + formatted + "</p></body></html>";
        // Send Email
        System.out.println("Sending EMAIL to " + to + " with content:\n" + composedMessage);
        // Analytics
        System.out.println("Analytics updated for: " + to);
    }
}

class SMSNotification{
    public void send(String to, String message){
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating phone number: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);
        // Compose SMS
        String composedMessage = "[SMS]" + formatted;
        // Send SMS
        System.out.println("Sending SMS to " + to + " with message: " + composedMessage);
        // Analytics (custom)
        System.out.println("Custom SMS analytics for: " + to);
    }
}
// PROBLEM
// Suppose TomorrowYour application now supports:
// 1. Email
// 2. SMS
// 3. Push Notification
// 4. WhatsApp
// You will flow the exact flow (duplicate code)
// 1. Check Rate Limit
//       ↓
// 2. Validate Recipient
//       ↓
// 3. Format Message
//       ↓
// 4. Log Request
//       ↓
// 5. Compose Message
//       ↓
// 6. Send
//       ↓
// 7. Update Analytics
// Only two steps change:
// 1. Validate Recipient
// 2. Compose & Send
// Everything else is duplicated.

// SOLUTION
abstract class NotificationSender{
    // Final template method
    // Enforcing a flow
    public final void send(String to, String rawMessage){
        // Common
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);
        // Logic not common
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);
        // Common
        postSendAnalytics(to);
    }

    // Common Step 1
    private void rateLimitCheck(String to){
        System.out.println("Checking rate limits for: " + to);
    }
    // Common Step 2
    private void validateRecipient(String to){
        System.out.println("Validating recipient: " + to);
    }
    // Common Step 3
    private String formatMessage(String message){
        return message.trim();
    }
    // Common Step 4
    private void preSendAuditLog(String to, String message){
        System.out.println("Logging before send: " + message + " to " + to);
    }
    // Hook for subclasses
    protected abstract String composeMessage(String formattedMessage);
    protected abstract void sendMessage(String to, String message);

    // Common Step 5 (Optional Hook)
    protected  void postSendAnalytics(String to){
        System.out.println("Analytics updated for:" + to);
    }
}

class EmailNotificationClone extends NotificationSender{
    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body<p>" + formattedMessage + "</p></body></html>";
    }
    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending EMAIL to " + to + " with content:\n" + message);
    }
}

class SMSNotificationClone extends NotificationSender{
    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS]" + formattedMessage;
    }
    @Override
    protected void sendMessage(String to, String message) {
        System.out.println("Sending SMS to " + to + " with message: " + message);
    }
    // Overriding optional hook
    @Override
    protected void postSendAnalytics(String to){
        System.out.println("Custom SMS analytics for: " + to);
    }
}

public class TemplateMethodDesignPattern {
    static void main() {
        NotificationSender emailSender = new EmailNotificationClone();
        emailSender.send("laviksiraswa@gmail.com", "   Welcome to TUF+!   ");
        System.out.println();
        NotificationSender smsSender = new SMSNotificationClone();
        smsSender.send("7364527586", "Your OTP is 4857");
    }
}
