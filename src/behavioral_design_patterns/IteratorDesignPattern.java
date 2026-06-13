package behavioral_design_patterns;

import java.util.ArrayList;
import java.util.List;

class Video{
    String title;
    public Video(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
}

class YouTubePlayList{
    private List<Video> videos = new ArrayList<>();
    public void addVideo(Video video){
        videos.add(video);
    }
    public List<Video> getVideos(){
        return videos;
    }
}

public class IteratorDesignPattern {
    static void main() {
        // PROBLEM - Video is exposed on the client side.
        // Videos should not expose to the client side.
        // The "getVideos()" method of YouTubePlayList class is exposed to the client.
        // And internal structure of the Video class is also expose "getTitle() method".
        YouTubePlayList playList = new YouTubePlayList();
        playList.addVideo(new Video("LLD Tutorial"));
        playList.addVideo(new Video("System Design Basics"));
        // We are iterating through videos on the client side. // Traversal logic is exposed.
        // We don't have to expose the traversal algorithm, internal structure of the data type (YouTubePlayList, Video) and
        // what data structure the class is using to store the data.
        for(Video video : playList.getVideos()){
            System.out.println(video.getTitle());
        }
    }
}
