package clube_desportivo.beans;

public class Space {

    private String name;
    private double price;

    public Space() {
    }

    public Space(String n, double p) {
        name = n;
        price = p;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double p) {
        this.price = p;
    }

    public double getPrice() {
        return this.price;
    }
    
    public String toString(){
        return "Space: " + getName() + ", " + getPrice();
    }
}
