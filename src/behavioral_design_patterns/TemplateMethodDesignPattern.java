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

public class TemplateMethodDesignPattern {
    static void main() {

    }
}
