package creational_design_patterns;

interface EmailTemplate{
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome to TUF+!";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}
// Use Prototype when object creation is expensive or complex, and creating new objects by copying existing ones is
// more efficient than constructing them from scratch.
// Why Do We Need It?
// > Suppose creating an object is:
//    1. expensive
//    2. time-consuming
//    3. involves database calls
//    4. involves complex initialization

// Problem We are creating two object.
public class PrototypeDesignPattern {
    static void main() {
        WelcomeEmail welcomeEmailTuf = new WelcomeEmail();
        welcomeEmailTuf.setContent("Welcome to TUF");

        WelcomeEmail welcomeEmailTufPlus = new WelcomeEmail();
        welcomeEmailTufPlus.setContent("Welcome to TUF+");
    }
}
