package structural_design_pattern;

class RealImage{
    private final String fileName;
    public RealImage(String fileName){
        this.fileName = fileName;
        loadImageFromDisk();
    }
    private void loadImageFromDisk(){
        System.out.println("Loading image: " + fileName);
    }
    public void display(){
        System.out.println("Displaying: " + fileName);
    }
}

public class ProxyDesignPatternChatGPT {
    static void main() {
        // PROBLEM - Even if we never display it.
        // This wastes resources by loading - calling "loadImageFromDisk"
        RealImage image = new RealImage("photo.jpg");
    }
}
