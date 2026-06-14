package behavioral_design_patterns;

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

public class ObserverDesignPattern {
    static void main() {

    }
}
