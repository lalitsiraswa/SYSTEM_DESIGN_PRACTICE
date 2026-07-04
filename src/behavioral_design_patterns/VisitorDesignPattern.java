package behavioral_design_patterns;

// Amazon E-Commerce checkout service can have multiple products like:
// 1. Physical Product
// 2. GiftCard
// 3. DigitalProduct

import java.util.ArrayList;
import java.util.List;

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

// SOLUTION
// Element Interface
interface Item{
    void accept(ItemVisitor visitor);
}

// Concrete Elements (implementation)
class PhysicalProductClone implements Item{
    String name;
    double weight;
    public PhysicalProductClone(String name, double weight){
        this.name = name;
        this.weight = weight;
    }
    @Override
    public void accept(ItemVisitor visitor) {
        // Second Dispatch -> Which ItemVisitor Type
        visitor.visit(this);
    }
}

class DigitalProductClone implements Item{
    String name;
    int downloadSizeInMB;
    public DigitalProductClone(String name, int downloadSizeInMB){
        this.name = name;
        this.downloadSizeInMB = downloadSizeInMB;
    }
    @Override
    public void accept(ItemVisitor visitor) {
        // Second Dispatch -> Which ItemVisitor Type
        visitor.visit(this);
    }
}

class GiftCardClone implements Item{
    String code;
    double amount;
    public GiftCardClone(String code, double amount){
        this.code = code;
        this.amount = amount;
    }
    @Override
    public void accept(ItemVisitor visitor) {
        // Second Dispatch -> Which ItemVisitor Type
        visitor.visit(this);
    }
}

// Visitor Interface
interface ItemVisitor{
    void visit(PhysicalProductClone item);
    void visit(DigitalProductClone item);
    void visit(GiftCardClone item);
}

class InvoiceVisitor implements ItemVisitor{
    @Override
    public void visit(PhysicalProductClone item) {
        System.out.println("Invoice: " + item.name + " - Shipping to customer");
    }
    @Override
    public void visit(DigitalProductClone item) {
        System.out.println("Invoice: " + item.name + " - Email with download link");
    }
    @Override
    public void visit(GiftCardClone item) {
        System.out.println("Invoice: Gift Card - Code " + item.code);
    }
}

class ShippingCostVisitor implements ItemVisitor{
    @Override
    public void visit(PhysicalProductClone item) {
        System.out.println("Shipping cost for " + item.name + ": ₹" + (item.weight * 10));
    }
    @Override
    public void visit(DigitalProductClone item) {
        System.out.println(item.name + " is digital - No shipping cost.");
    }
    @Override
    public void visit(GiftCardClone item) {
        System.out.println("GiftCard delivery via email - No shipping cost.");
    }
}

public class VisitorDesignPattern {
    static void main() {
        List<Item> items = new ArrayList<>();
        items.add(new PhysicalProductClone("Shoes", 1.2));
        items.add(new DigitalProductClone("EBook", 150));
        items.add(new GiftCardClone("TUF5000", 5000));

        ItemVisitor invoiceGenerator = new InvoiceVisitor();
        ItemVisitor shippingCostCalculator = new ShippingCostVisitor();

        for(Item item : items){
            // Fist Dispatch -> Which Item Type
            item.accept(invoiceGenerator);
            item.accept(shippingCostCalculator);
        }
    }
}
