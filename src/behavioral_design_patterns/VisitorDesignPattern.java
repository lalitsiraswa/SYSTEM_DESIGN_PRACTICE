package behavioral_design_patterns;

// Amazon E-Commerce checkout service can have multiple products like:
// 1. Physical Product
// 2. GiftCard
// 3. DigitalProduct

// PROBLEM
// lets say tomorrow we might decide to add more functionalities like: warehouse cost, printInvoice, calculateTax, exportPDF, exportExcel etc.
// for that we have to add that functionality to each of these classes and that will break the Single Responsibility Principle.
class PhysicalProduct{
    void printInvoice(){
        // TODO: IMPLEMENT LOGIC
    }
    double calculateShippingCost(){
        // TODO: IMPLEMENT LOGIC
        return 31.08;
    }
}

class DigitalProduct{
    void printInvoice(){
        // TODO: IMPLEMENT LOGIC
    }
    // No Shipping Needed For Digital Product
}

class GiftCard{
    void printInvoice(){
        // TODO: IMPLEMENT LOGIC
    }
    double calculateDiscount(){
        // TODO: IMPLEMENT LOGIC
        return 31.08;
    }
}

public class VisitorDesignPattern {
    static void main() {
    }
}
