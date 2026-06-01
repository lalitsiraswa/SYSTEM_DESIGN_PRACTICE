package creational_design_patterns;

//interface EmailTemplate{
//    void setContent(String content);
//    void send(String to);
//}

//class WelcomeEmail implements EmailTemplate{
//    private String subject;
//    private String content;
//
//    public WelcomeEmail(){
//        this.subject = "Welcome to TUF+!";
//        this.content = "Hi there! Thanks for joining us.";
//    }
//
//    @Override
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public void send(String to) {
//        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
//    }
//}
// Use Prototype when object creation is expensive or complex, and creating new objects by copying existing ones is
// more efficient than constructing them from scratch.
// Why Do We Need It?
// > Suppose creating an object is:
//    1. expensive
//    2. time-consuming
//    3. involves database calls
//    4. involves complex initialization

// Problem We are creating two object.
//public class PrototypeDesignPattern {
//    static void main() {
//        WelcomeEmail welcomeEmailTuf = new WelcomeEmail();
//        welcomeEmailTuf.setContent("Welcome to TUF");
//
//        WelcomeEmail welcomeEmailTufPlus = new WelcomeEmail();
//        welcomeEmailTufPlus.setContent("Welcome to TUF+");
//    }
//}

import java.util.HashMap;
import java.util.Map;

interface EmailTemplate extends Cloneable{
    EmailTemplate clone(); // Deep Copy
    void setContent(String content);
    void send(String to);
}

// This is NOT really a Deep Copy
// super.clone() performs a shallow copy.
// Why does it still work?
// Because your class contains only:
// private String subject;
// private String content;
// Both are Strings, and String is immutable.
// So even though super.clone() is shallow, there is no risk of shared mutable state.
class WelcomeEmail implements EmailTemplate{
    private String subject;
    private String content;

    public WelcomeEmail(){
        this.subject = "Welcome to TUF+!";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public EmailTemplate clone() {
        try {
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e){
            throw new RuntimeException("Clone Failed!", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }

    @Override
    public String toString() {
        return "WelcomeEmail{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

class EmailTemplateRegistry{
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static{
        templates.put("welcome", new WelcomeEmail());
        // Add more templates like "discount", "feature-update" etc.
    }

    public static EmailTemplate getTemplate(String type){
        return templates.get(type).clone(); // Clone avoids modifying the original
    }
}

public class PrototypeDesignPattern {
    static void main() {
        EmailTemplate welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Welcome to TUF");
        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Welcome to TUF+");
        System.out.println(welcomeEmail1);
        System.out.println(welcomeEmail2);
    }
}
