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

// SOLUTION
interface DocumentSessionMediator{
    void broadcastChange(String change, UserClone sender);
    void join(UserClone user);
}

class CollaborativeDocument implements DocumentSessionMediator{
    private final List<UserClone> users = new ArrayList<>();
    @Override
    public void broadcastChange(String change, UserClone sender) {
        for(UserClone user : users){
            if(user != sender){
                user.receiveChange(change, sender);
            }
        }
    }
    @Override
    public void join(UserClone user) {
        users.add(user);
    }
}

class UserClone{
    protected String name;
    protected DocumentSessionMediator mediator;
    public UserClone(String name, DocumentSessionMediator mediator){
        this.name = name;
        this.mediator = mediator;
    }
    public void makeChange(String change){
        System.out.println(name + " edited the document: " + change);
        mediator.broadcastChange(change, this);
    }
    public void receiveChange(String change, UserClone send){
        System.out.println(name + " saw change from " + send.name + ": \"" + change +"\"");
    }
}

public class MediatorDesignPattern {
    static void main() {
        CollaborativeDocument document = new CollaborativeDocument();

        UserClone alice = new UserClone("Alice", document);
        UserClone bob = new UserClone("Bob", document);
        UserClone charlie = new UserClone("Charlie", document);

        document.join(alice);
        document.join(bob);
        document.join(charlie);

        alice.makeChange("Added project title");
        bob.makeChange("Corrected grammar in paragraph - 2");
    }
}
