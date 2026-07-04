package behavioral_design_patterns;

// Imagine you're building an Online Shopping System.
// You have different product types:
// Book
// Laptop
// Mobile

// PROBLEM
// lets say tomorrow we might decide to add more functionalities like: exportXML, exportJSON, generatePDF, calculateDiscount, analytics etc.
// for that we have to add that functionality to each of these classes and that will break the Single Responsibility Principle.

class Book {
    double price = 500;
    public double calculateTax() {
        return price * 0.05;
    }
}

class Laptop {
    double price = 80000;
    public double calculateTax() {
        return price * 0.18;
    }
}
class Mobile {
    double price = 30000;
    public double calculateTax() {
        return price * 0.18;
    }
}

public class VisitorDesignPatternChatGpt {
    static void main() {

    }
}
