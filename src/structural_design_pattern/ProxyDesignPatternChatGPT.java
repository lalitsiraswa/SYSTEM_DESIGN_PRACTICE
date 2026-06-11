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

// SOLUTION
interface Image{
    void display();
}

class RealImageClone implements Image{
    private final String fileName;
    public RealImageClone(String fileName){
        this.fileName = fileName;
        loadImageFromDisk();
    }
    private void loadImageFromDisk(){
        System.out.println("Loading image from disk: " + fileName);
    }
    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}

class ProxyImage implements Image{
    private final String fileName;
    private RealImageClone realImageClone;
    public ProxyImage(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void display() {
        if(realImageClone == null){
            realImageClone = new RealImageClone(fileName);
        }
        realImageClone.display();
    }
}

public class ProxyDesignPatternChatGPT {
    static void main() {
        // PROBLEM - Even if we never display it.
        // This wastes resources by loading - calling "loadImageFromDisk"
        RealImage image = new RealImage("photo.jpg");

        System.out.println("\n");

        // SOLUTION
        Image image1 = new ProxyImage("image.jpg");
        System.out.println("Image object created");
        // Now it only load the resource when need - It only loading the image when we want to display.
        image1.display();
    }
}
