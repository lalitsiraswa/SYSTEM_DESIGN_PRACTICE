package structural_design_pattern;

// UBER -> Car, Bike, Auto Objects
// Google Maps -> TREE Object

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Tree{
    // Coordinates (x, y)
    // These Properties/Attributes are keep Changing - these are also called Extrinsic Properties/Attributes
    private int x;
    private int y;
    // Below three Properties/Attributes are Co nstant/Common - these are also called Intrinsic Properties/Attributes
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

// SOLUTION
class TreeType{
    private String name;
    private String color;
    private  String texture;
    public TreeType(String name, String color, String texture){
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
    public void draw(int x, int y){
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " +  name);
    }
}

class TreeClone{
    private int x;
    private int y;
    private TreeType treeType;
    public TreeClone(int x, int y, TreeType treeType){
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }
    public void draw(){
        treeType.draw(x, y);
    }
}

// Factory Design Pattern
class TreeFactory{
    static Map<String, TreeType> treeTypeMap = new HashMap<>();
    public static TreeType getTreeType(String name, String color, String texture){
        String key = name + "-" + color + "-" + texture;
        if(!treeTypeMap.containsKey(key)){
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        return treeTypeMap.get(key);
    }
}

class ForestClone{
    private List<TreeClone> treeClones = new ArrayList<>();
    public void plantTree(int x, int y, String name, String color, String texture){
        TreeType treeType = TreeFactory.getTreeType(name, color, texture);
        TreeClone treeClone = new TreeClone(x, y, treeType);
        treeClones.add(treeClone);
    }
    public void draw(){
        for(TreeClone treeClone : treeClones){
            treeClone.draw();
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
        System.out.println();

        // SOLUTION CODE
        ForestClone forestClone = new ForestClone();
        for(int i = 1; i <= 50; i++){
            forestClone.plantTree(i, i, "Oak", "Green", "Rough");
        }
        forestClone.draw();
    }
}
