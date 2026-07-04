package behavioral_design_patterns;

// ResumeEditor

import java.util.ArrayList;
import java.util.List;

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

public class MementoDesignPattern {
    static void main() {

    }
}
