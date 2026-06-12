package structural_design_pattern;

import java.util.HashMap;
import java.util.Map;

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

// SOLUTION
interface VideoDownload{
    String downloadVideo(String videoUrl);
}

class RealVideoDownloaderClone implements VideoDownload{
    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
}

class CachedVideoDownloader implements VideoDownload{
    private RealVideoDownloaderClone realVideoDownloader;
    private Map<String, String> cache;
    public CachedVideoDownloader(){
        this.realVideoDownloader = new RealVideoDownloaderClone();
        this.cache = new HashMap<>();
    }
    @Override
    public String downloadVideo(String videoUrl) {
        for(Map.Entry<String, String> entry : cache.entrySet()){
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        if(cache.containsKey(videoUrl)){
            System.out.println("Returning cached video for: " + videoUrl);
            return cache.get(videoUrl);
        }
        System.out.println("Cache miss. Downloading...");
        String video = realVideoDownloader.downloadVideo(videoUrl);
        cache.put(videoUrl, video);
        return video;
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

        System.out.println("\n");
        // SOLUTION
        VideoDownload client3 = new CachedVideoDownloader();
        client3.downloadVideo("composite-design-pattern");
        client3.downloadVideo("composite-design-pattern");
    }
}
