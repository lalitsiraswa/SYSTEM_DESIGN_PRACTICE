package structural_design_pattern;

// UBER -> Car, Bike, Auto Objects
// Google Maps -> TREE Object

import java.util.ArrayList;
import java.util.List;

class Tree{
    // Coordinates (x, y)
    // These Properties/Attributes are keep Changing - these are also called Extrinsic Properties/Attributes
    private int x;
    private int y;
    // Below three Properties/Attributes are Constant/Common - these are also called Intrinsic Properties/Attributes
    private String name;
    private String color;
    private String texture;
    public Tree(int x, int y, String name, String color, String texture){
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
    public void draw(){
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " +  name);
    }
}

// Google Maps showing forest (Accumulation of multiple tree's)
class Forest{
    private List<Tree> trees = new ArrayList<>();
    public void plantTree(int x, int y, String name, String color, String texture){
        // PROBLEM - Every time we are creating a new object.
        Tree tree = new Tree(x, y, name, color, texture);
        trees.add(tree);
    }
    public void draw(){
        for(Tree tree : trees){
            tree.draw();
        }
    }
}


public class FlyweightDesignPattern {
    static void main() {
        Forest forest = new Forest();
        for(int i = 1; i <= 50; i++){
            forest.plantTree(i, i, "Oak", "Green", "Rough");
        }
        forest.draw();
    }
}
