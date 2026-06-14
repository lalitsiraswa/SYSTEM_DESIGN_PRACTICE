package behavioral_design_patterns;

import java.util.ArrayList;
import java.util.List;

// PROBLEM
class YouTubeChannel{
    // This is not following the Single Responsibility Principle, currently it is handling the two responsibilities
    // 1. Uploading the Video
    // 2. Sending the notifications
    public void uploadNewVideo(String videoTitle){
        // Video uploading logic
        System.out.println("Uploading: " + videoTitle);

        // Manually notify users
        // Assume we have a large number of subscribers - it is not scalable
        System.out.println("Sending email to user1@example.com");
        System.out.println("Pushing in-app notification to user2@example.com");
    }
}

// SOLUTION
// Observer/Subscribers Interface
interface Subscriber{
    void update(String videoTitle);
}

// Concrete Observer/Subscriber
class EmailSubscriber implements Subscriber{
    private String email;
    public EmailSubscriber(String email){
        this.email = email;
    }
    @Override
    public void update(String videoTitle) {
        System.out.println("Email send to " + email + ": New video uploaded - " + videoTitle);
    }
}

class MobileAppSubscriber implements Subscriber{
    private String username;
    public MobileAppSubscriber(String username){
        this.username = username;
    }
    @Override
    public void update(String videoTitle) {
        System.out.println("In-app notification for " + username + ": New video uploaded - " + videoTitle);
    }
}

// Subject/Publisher Interface
interface Channel{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

// Concrete Subject/Publisher
class YouTubeChannelClone implements Channel{
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YouTubeChannelClone(String channelName){
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void uploadVideo(String videoTitle){
        System.out.println(channelName + " uploaded: " + videoTitle);
        notifySubscribers(videoTitle);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        for(Subscriber subscriber : subscribers){
            subscriber.update(videoTitle);
        }
    }
}

public class ObserverDesignPattern {
    static void main() {
        // We can't call tuf.uploadVideo("Time and Space Complexity");
        // Channel tuf = new YouTubeChannelClone("takeUforward");
        YouTubeChannelClone tuf = new YouTubeChannelClone("takeUforward");
        tuf.subscribe(new EmailSubscriber("user01@example.com"));
        tuf.subscribe(new MobileAppSubscriber("username01"));
        tuf.subscribe(new EmailSubscriber("user02@example.com"));
        tuf.subscribe(new MobileAppSubscriber("username02"));
        System.out.println();
        tuf.uploadVideo("Time and Space Complexity");
        System.out.println();
        tuf.uploadVideo("Kadane's Algorithm | Maximum Subarray Sum | Finding and Printing");
    }
}
