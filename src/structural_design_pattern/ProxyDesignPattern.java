package structural_design_pattern;

class RealVideoDownloader{
    public String downloadVideo(String videoUrl){
        // We can implement all the functionalities here like
        // Caching
        // Filterging
        // Restriction/Access eg: Limit on downlods per day
        // But if we implement all these in a single class it will break Simgle Responsibility Principle.
        // that's why we will introduce Proxy Design Pattern
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
}

public class ProxyDesignPattern {
    static void main() {
        // PROBLEM - Both the clients downloading the same video("proxy-pattern"),
        // so it is kind of repetitive task.
        RealVideoDownloader client1 = new RealVideoDownloader();
        client1.downloadVideo("proxy-pattern");

        RealVideoDownloader client2 = new RealVideoDownloader();
        client2.downloadVideo("proxy-pattern");
    }
}
