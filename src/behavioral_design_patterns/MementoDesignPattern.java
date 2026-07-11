package behavioral_design_patterns;

// ResumeEditor

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class ResumeEditor{
    public String name;
    public String education;
    public String experience;
    public List<String> skills;
}

// PROBLEM
// When we created ResumeSnapshot class we exposed all the member(name, education, experience, skills) of the ResumeEditor class.
// We exposed internal details of ResumeEditor class.
// Here we are violating Encapsulation principle.
class ResumeSnapshot{
    public String name;
    public String education;
    public String experience;
    public List<String> skills;

    public ResumeSnapshot(ResumeEditor editor){
        this.name = editor.name;
        this.education = editor.education;
        this.experience = editor.experience;
        this.skills = editor.skills;
    }

    public void restore(ResumeEditor editor){
        editor.name = this.name;
        editor.education = this.education;
        editor.experience = this.experience;
        editor.skills = new ArrayList<>(this.skills);
    }
}

// SOLUTION
// Memento Design Patter has tree components.
// 1. Originator: The object whose state we want to save.
// 2. Memento: An object that stores the state of the originator.
// 3. Caretaker: The object responsible for saving and restoring the Memento.

// Originator with Memento inside
class ResumeEditorClone{
    private String name;
    private String education;
    private String experience;
    private List<String> skills;

    public void setName(String name){
        this.name = name;
    }

    public void setEducation(String education){
        this.education = education;
    }

    public void setExperience(String experience){
        this.experience = experience;
    }

    public void setSkills(List<String> skills){
        this.skills = skills;
    }

    public void printResume(){
        System.out.println("------ Resume ------");
        System.out.println("Name: " + name);
        System.out.println("Education: " + education);
        System.out.println("Experience: " + experience);
        System.out.println("Skills: " + skills);
        System.out.println("--------------------");
    }

    public Memento save(){
        return new Memento(name, education, experience, List.copyOf(skills));
    }

    public void restore(Memento memento){
        this.name = memento.getName();
        this.education = memento.getEducation();
        this.experience = memento.getExperience();
        this.skills = memento.getSkills();
    }

    // Inner Memento Class
    public static class Memento{
        private final String name;
        private final String education;
        private final String experience;
        private final List<String> skills;

        private Memento(String name, String education, String experience, List<String> skills){
            this.name = name;
            this.education = education;
            this.experience = experience;
            this.skills = skills;
        }

        private String getName(){
            return name;
        }

        private String getEducation(){
            return education;
        }

        private String getExperience(){
            return experience;
        }

        private List<String> getSkills(){
            return skills;
        }
    }
}

// Caretaker
class ResumeHistory{
    private final Stack<ResumeEditorClone.Memento> history = new Stack<>();

    public void save(ResumeEditorClone editor){
        history.push(editor.save());
    }

    public void undo(ResumeEditorClone editor){
        if(!history.empty()){
            editor.restore(history.pop());
        }
    }
}

public class MementoDesignPattern {
    static void main() {
        ResumeEditorClone editor = new ResumeEditorClone();
        ResumeHistory history = new ResumeHistory();

        editor.setName("Alice");
        editor.setEducation("B.Tech CSE");
        editor.setExperience("Fresher");
        editor.setSkills(Arrays.asList("Java", "DSA"));
        history.save(editor);

        editor.setExperience("SDE Intern at TUF+");
        editor.setSkills(Arrays.asList("Java", "DSA", "LLD", "Sprint Boot"));
        history.save(editor);

        editor.setExperience("SDE-1 at TUF+");
        editor.setSkills(Arrays.asList("Java", "DSA", "LLD", "Sprint Boot", "MySql", "MongoDB", "Docker"));

        editor.printResume(); // Shows updated experience and skills

        history.undo(editor);
        editor.printResume(); // Shows resume after undo

        history.undo(editor);
        editor.printResume(); // Shows resume after second undo (initial state)
    }
}
