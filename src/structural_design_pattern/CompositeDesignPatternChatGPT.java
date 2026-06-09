package structural_design_pattern;

import java.util.ArrayList;
import java.util.List;

//Root
//│
//├── file1.txt
//├── file2.txt
//│
//└── Documents
//      │
//      ├── resume.pdf
//      └── notes.txt

interface FileSystemComponent {
    void show();
}

class File implements FileSystemComponent {
    private final String name;
    public File(String name) {
        this.name = name;
    }
    @Override
    public void show() {
        System.out.println(name);
    }
}

class Folder implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();
    public Folder(String name) {
        this.name = name;
    }
    public void add(FileSystemComponent component) {
        children.add(component);
    }
    @Override
    public void show() {
        System.out.println("Folder: " + name);
        for(FileSystemComponent child : children) {
            child.show();
        }
    }
}

//Root
//│
//├── file1.txt
//├── file2.txt
//│
//└── Documents
//      │
//      ├── resume.pdf
//      └── notes.txt

public class CompositeDesignPatternChatGPT {
    static void main() {
        Folder root = new Folder("Root");
        File file1 = new File("File-1");
        File file2 = new File("File-2");
        root.add(file1);
        root.add(file2);

        Folder documents = new Folder("Documents");
        File resume = new File("resume.pdf");
        File notes = new File("notes.txt");
        documents.add(resume);
        documents.add(notes);

        root.add(documents);

        root.show();
    }
}
