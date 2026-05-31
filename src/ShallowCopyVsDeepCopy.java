// we did perform a deep copy for Address.
// copy.address = address.clone(); This line inside Employee.clone() creates a new Address object instead of sharing the old one.
// Address contains only: String city; And String is immutable in Java.
// So when super.clone() copies city, it's perfectly safe because the String object cannot be modified.
class Address implements Cloneable{
    String city;

    Address(String city){
        this.city = city;
    }

    @Override
    protected Address clone() throws CloneNotSupportedException{
        return (Address) super.clone(); // Shallow Copy
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}

class EmployeeShallowCopy implements Cloneable{
    String name;
    Address address;

    EmployeeShallowCopy(String name, Address address){
        this.name = name;
        this.address = address;
    }

    @Override
    protected EmployeeShallowCopy clone() throws CloneNotSupportedException{
        return (EmployeeShallowCopy) super.clone(); // Shallow Copy
    }

    @Override
    public String toString() {
        return "EmployeeShallowCopy{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class EmployeeDeepCopy implements Cloneable{
    String name;
    Address address;

    EmployeeDeepCopy(String name, Address address){
        this.name = name;
        this.address = address;
    }

    @Override
    protected EmployeeDeepCopy clone() throws CloneNotSupportedException{
        // Deep Copy
        EmployeeDeepCopy employeeDeepCopy = (EmployeeDeepCopy) super.clone();
        employeeDeepCopy.address = address.clone();
        return employeeDeepCopy;
    }

    @Override
    public String toString() {
        return "EmployeeDeepCopy{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

public class ShallowCopyVsDeepCopy {
    static void main() throws CloneNotSupportedException {
        System.out.println("Shallow Copy vs Deep Copy!");
        EmployeeShallowCopy emp1 = new EmployeeShallowCopy("Lalit Siraswa", new Address("Sikar"));
        EmployeeShallowCopy emp2 = emp1.clone();
        emp2.address.city = "Bangalore";
        System.out.println(emp1);
        System.out.println(emp2);

        EmployeeDeepCopy emp3 = new EmployeeDeepCopy("Lavik Siraswa", new Address("Nawalgarh"));
        EmployeeDeepCopy emp4 = emp3.clone();
        emp4.address.city = "Mangalore";
        System.out.println(emp3);
        System.out.println(emp4);
    }
}
