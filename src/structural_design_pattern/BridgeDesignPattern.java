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
// And these all are tightly coupled.
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
public class BridgeDesignPattern {
}
