public class Product {
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double salePrice() {
        // default 10% discount
        return price * 0.9;
    }

    public double salePrice(double discountRatio){
        return price * (1.0 - discountRatio);
    }

    public static void main(String[] args) {
        Product widget = new Product();
        widget.setPrice(20.00);
        System.out.println(widget.salePrice()); //10% off is 18.00
        System.out.println(widget.salePrice(0.3)); //30% off is 14.00
    }
}
