package structural_design_pattern;

interface PlayQuality{
    void play(String title);
}

class WebHDPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in HD");
    }
}

class MobileHDPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in HD");
    }
}

class SmartTVUltraHDPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Smart TV: Playing " + title + " in ultra HD");
    }
}

// PROBLEM - Imagine in future 4K/8k is introduced or a new Device is introduced then we have to create lots of classes.
// And these all are tightly coupled. Streaming platform (Web Player, Mobile Player, Smart TV) and Quality (HD, 4k)
class Web4KPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in 4k");
    }
}

class Mobile4KPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in 4k");
    }
}

class SmartTVUltra4KPlayer implements PlayQuality{
    @Override
    public void play(String title) {
        System.out.println("Smart TV: Playing " + title + " in ultra 4k");
    }
}

// SOLUTION
interface VideoQuality{
    void load(String title);
}

class SDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in SD Quality");
    }
}

class HDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in HD Quality");
    }
}

class UltraHDQuality implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in 4K Ultra HD Quality");
    }
}

// Image 8k Introduced
class K8Player implements VideoQuality{
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in 8k Quality");
    }
}

abstract class VideoPlayer{
    protected VideoQuality videoQuality;
    public VideoPlayer(VideoQuality videoQuality){
        this.videoQuality = videoQuality;
    }
    public abstract void play(String title);
}

class MobilePlayer extends VideoPlayer{
    public MobilePlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Mobile Platform");
        videoQuality.load(title);
    }
}

class WebPlayer extends VideoPlayer{
    public WebPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }
    @Override
    public void play(String title) {
        System.out.println("Web Platform");
        videoQuality.load(title);
    }
}

class SmartTVPlayer extends VideoPlayer{
    public SmartTVPlayer(VideoQuality videoQuality) {
        super(videoQuality);
    }

    @Override
    public void play(String title) {
        System.out.println("Smart TV Platform");
        videoQuality.load(title);
    }
}
// Now both Streaming platform (Web Player, Mobile Player, Smart TV) and Quality (HD, 4k, 8k) are decoupled.
public class BridgeDesignPattern {
    static void main() {
        VideoPlayer webPlayer = new WebPlayer(new HDQuality());
        webPlayer.play("Take You Forward");
        System.out.println();
        VideoPlayer smartTvPlayer = new SmartTVPlayer(new UltraHDQuality());
        smartTvPlayer.play("APEX");
        System.out.println();
        VideoPlayer smartTvPlayer8K = new SmartTVPlayer(new K8Player());
        smartTvPlayer8K.play("ABCD");
    }
}
