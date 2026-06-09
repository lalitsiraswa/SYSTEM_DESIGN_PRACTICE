package structural_design_pattern;

import java.util.ArrayList;
import java.util.List;

class Product{
    private String name;
    private double price;

    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public void display(String indent){
        System.out.println(indent + " Product: " + name + " - ₹" + price);
    }
}

class ProductBundle{
    private String bundleName;
    private List<Product> products = new ArrayList<>();

    public ProductBundle(String bundleName){
        this.bundleName = bundleName;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public double getPrice(){
        double total = 0;
        for(Product product : products){
            total += product.getPrice();
        }
        // TODO: Discount Logic
        return total;
    }

    public void display(String indent){
        System.out.println(indent + "Bundle: " + bundleName);
        for(Product product : products){
            product.display(indent);
        }
    }
}

public class CompositeDesignPattern {
    static void main() {
        // Individual Product
        Product book = new Product("Atomic Habits", 599);
        Product phone = new Product("IPhone 17", 150000);
        Product earbuds = new Product("AirPods", 24999);
        Product charger = new Product("20W Charger", 3999);

        // Bundle: IPhone Combo
        ProductBundle iphoneCombo = new ProductBundle("IPhone Essentials Combo");
        iphoneCombo.addProduct(phone);
        iphoneCombo.addProduct(earbuds);
        iphoneCombo.addProduct(charger);

        // Bundle: School Kit
        ProductBundle schoolKit = new ProductBundle("Back to School Kit");
        schoolKit.addProduct(new Product("Notebook Pack", 599));
        schoolKit.addProduct(new Product("Pen Set", 199));
        schoolKit.addProduct(new Product("Highlighter", 149));

        // Add to cart - Problem Begins!
        List<Object> cart = new ArrayList<>();
        cart.add(book);
        cart.add(iphoneCombo);
        cart.add(schoolKit);

        // Display Cart
        System.out.println("🛒 Your Cart (Without Composite Pattern):");
        double total = 0;
        for(Object item : cart){
            if(item instanceof Product){
                Product product = (Product) item;
                product.display(" ");
                total += product.getPrice();
            }
            else if(item instanceof ProductBundle){
                ProductBundle productBundle = (ProductBundle) item;
                productBundle.display(" ");
                total += productBundle.getPrice();
            }
        }

        System.out.println("\n💰 Total Amount : ₹" + total);
    }
}
