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

// SOLUTION

// Step 1: Element Interface
interface Product {
    void accept(ProductVisitor visitor);
}

// Step 2: Concrete Elements
class BookClone implements Product {
    double price = 500;
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

class LaptopClone implements Product {
    double price = 80000;
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

class MobileClone implements Product {
    double price = 30000;
    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}

// Step 3: Visitor Interface
interface ProductVisitor {
    void visit(BookClone book);
    void visit(LaptopClone laptop);
    void visit(MobileClone mobile);
}

// Step 4: Tax Visitor
class TaxVisitor implements ProductVisitor {
    @Override
    public void visit(BookClone book) {
        System.out.println("Book Tax = " + book.price * 0.05);
    }
    @Override
    public void visit(LaptopClone laptop) {
        System.out.println("Laptop Tax = " + laptop.price * 0.18);
    }
    @Override
    public void visit(MobileClone mobile) {
        System.out.println("Mobile Tax = " + mobile.price * 0.18);
    }
}

// Step 5: Discount Visitor
class DiscountVisitor implements ProductVisitor {
    @Override
    public void visit(BookClone book) {
        System.out.println("Book Discount 10%");
    }
    @Override
    public void visit(LaptopClone laptop) {
        System.out.println("Laptop Discount 5%");
    }
    @Override
    public void visit(MobileClone mobile) {
        System.out.println("Mobile Discount 8%");
    }
}
public class VisitorDesignPatternChatGpt {
    static void main() {
        Product[] products = {
                new BookClone(),
                new LaptopClone(),
                new MobileClone()
        };
        ProductVisitor taxVisitor = new TaxVisitor();
        ProductVisitor discountVisitor = new DiscountVisitor();
        for(Product product : products) {
            product.accept(taxVisitor);
        }
        System.out.println();
        for(Product product : products) {
            product.accept(discountVisitor);
        }
    }
}
