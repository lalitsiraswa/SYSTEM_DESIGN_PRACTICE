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

// SOLUTION
interface PlaylistIterator{
    boolean hasNext();
    Video next();
}

// Concrete iterator - traversal algorithm - 1
class YouTubePlaylistIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;

    public YouTubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}

class YouTubePlaylistCopyrightIterator implements PlaylistIterator{
    private List<Video> videos;
    private int position;

    public YouTubePlaylistCopyrightIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        // TODO: Logic for Copyright and Skip those videos.
        return hasNext() ? videos.get(position++) : null;
    }
}

// Iterable interface
interface Playlist{
    PlaylistIterator createIterator();
    PlaylistIterator createCopyrightIterator();
}

class YouTubePlayListClone implements Playlist{
    private List<Video> videos = new ArrayList<>();
    public void addVideo(Video video){
        videos.add(video);
    }
    @Override
    public PlaylistIterator createIterator() {
        return new YouTubePlaylistIterator(videos);
    }

    @Override
    public PlaylistIterator createCopyrightIterator() {
        return new YouTubePlaylistCopyrightIterator(videos);
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

        // SOLUTION Client - Code
        System.out.println();
        // Here we hide the all the implementation details from the client like traversal logic and what type of data structure
        // it is using for storing data (list, set, graph etc.).
        YouTubePlayListClone youTubePlayListClone = new YouTubePlayListClone();
        youTubePlayListClone.addVideo(new Video("System Design was HARD until I Learned these 30 Concepts"));
        youTubePlayListClone.addVideo(new Video("8 Most Important System Design Concepts You Should Know"));
        PlaylistIterator playlistIterator = youTubePlayListClone.createIterator();
        while(playlistIterator.hasNext()){
            System.out.println(playlistIterator.next().getTitle());
        }
    }
}
