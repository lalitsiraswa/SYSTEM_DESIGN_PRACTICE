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

class FileClone{
    private String name;
    public FileClone(String name){
        this.name = name;
    }
    public void show(){
        System.out.println("File: " + name);
    }
}

class FolderClone{
    private String name;
    private List<FileClone> files = new ArrayList<>();
    private List<FolderClone> folders = new ArrayList<>();
    public FolderClone(String name){
        this.name = name;
    }
    public void addFile(FileClone file){
        files.add(file);
    }
    public void addFolder(FolderClone folder){
        folders.add(folder);
    }
    public void show(){
        System.out.println("Folder: " + name);
        for(FileClone file : files){
            file.show();
        }
        for(FolderClone folder : folders){
            folder.show();
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
    // Problem - instanceof
    public static void display(Object object){
        if(object instanceof FileClone){
            ((FileClone)object).show();
        }
        else if(object instanceof FolderClone){
            ((FolderClone)object).show();
        }
    }
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

        System.out.println("\n");

        // Problem CLIENT CODE
        FileClone resume_file = new FileClone("resume.pdf");
        FileClone notes_file = new FileClone("notes.txt");
        FolderClone documents_folder = new FolderClone("Documents");
        documents_folder.addFile(resume_file);
        documents_folder.addFile(notes_file);
        FolderClone root_folder = new FolderClone("Root");
        root_folder.addFolder(documents_folder);
        root_folder.show();
        System.out.println("\n");
        display(resume_file);
        display(root_folder);
    }
}
