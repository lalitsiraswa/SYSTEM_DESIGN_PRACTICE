package structural_design_pattern;

import java.util.ArrayList;
import java.util.List;

class Product{
    private final String name;
    private final double price;

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
    private final String bundleName;
    private final List<Product> products = new ArrayList<>();

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

// SOLUTION

interface CartItem{
    double getPrice();
    void display(String indent);
}

class ProductClone implements CartItem{
    private final String name;
    private final double price;
    public ProductClone(String name, double price){
        this.name = name;
        this.price = price;
    }
    @Override
    public double getPrice(){
        return price;
    }
    @Override
    public void display(String indent){
        System.out.println(indent + " Product: " + name + " - ₹" + price);
    }
}

class ProductBundleClone implements CartItem{
    private final String bundleName;
    private final List<CartItem> products = new ArrayList<>();

    public ProductBundleClone(String bundleName){
        this.bundleName = bundleName;
    }

    public void addProduct(CartItem product){
        products.add(product);
    }
    @Override
    public double getPrice(){
        double total = 0;
        for(CartItem product : products){
            total += product.getPrice();
        }
        // TODO: Discount Logic
        return total;
    }
    @Override
    public void display(String indent){
        System.out.println(indent + "Bundle: " + bundleName);
        for(CartItem product : products){
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

        System.out.println("\n");
        // SOLUTION CODE - CLIENT SIDE

        // Individual Product
        CartItem bookClone = new ProductClone("Atomic Habits", 599);
        CartItem phoneClone = new ProductClone("IPhone 17", 150000);
        CartItem earbudsClone = new ProductClone("AirPods", 24999);
        CartItem chargerClone = new ProductClone("20W Charger", 3999);

        // Bundle: IPhone Combo
        ProductBundleClone iphoneComboClone = new ProductBundleClone("IPhone Essentials Combo");
        iphoneComboClone.addProduct(phoneClone);
        iphoneComboClone.addProduct(earbudsClone);
        iphoneComboClone.addProduct(chargerClone);

        // Bundle: School Kit
        ProductBundleClone schoolKitClone = new ProductBundleClone("Back to School Kit");
        schoolKitClone.addProduct(new ProductClone("Notebook Pack", 599));
        schoolKitClone.addProduct(new ProductClone("Pen Set", 199));
        schoolKitClone.addProduct(new ProductClone("Highlighter", 149));

        // Add to cart - Problem Begins!
        List<CartItem> cartClone = new ArrayList<>();
        cartClone.add(bookClone);
        cartClone.add(iphoneComboClone);
        cartClone.add(schoolKitClone);

        // Display Cart
        System.out.println("🛒 Your Cart (Without Composite Pattern):");
        double totalClone = 0;
        for(CartItem item : cartClone){
            item.display(" ");
            totalClone += item.getPrice();
        }

        System.out.println("\n💰 Total Amount : ₹" + totalClone);
    }
}
