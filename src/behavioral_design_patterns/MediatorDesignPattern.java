package behavioral_design_patterns;

import java.util.ArrayList;
import java.util.List;

// Google Document: User want to share or provide access to certain other users.

// PROBLEM
// Each user has references to every other user.
// Adding/removing a user breaks the structure, means Very tightly coupled with the other users.
class User{
    private String name;
    private List<User> others;
    public User(String name){
        this.name = name;
        this.others = new ArrayList<>();
    }
    public void addCollaboration(User user){
        others.add(user);
    }
    public void makeChange(String change){
        System.out.println(name + " made a change " + change);
        for(User user : others){
            user.receiveChange(change, this);
        }
    }
    public void receiveChange(String change, User from){
        System.out.println(name + " received: \"" + change + "\" from " + from.name);
    }
}

public class MediatorDesignPattern {
    static void main() {

    }
}
