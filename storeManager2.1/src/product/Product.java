package product;

public class Product extends ProductLine {

    protected String id_product;
    protected String productName;
    protected String described;
    protected String brand;
    protected Double priceIn;
    protected Double priceOut;
    protected int quantity;
    protected double discount;

    public Product(String id_product, String productName, String described, String brand, Double priceIn, Double priceOut, int quantity, double discount, String productLine) {
        this.id_product = id_product;
        this.productName = productName;
        this.described = described;
        this.brand = brand;
        this.priceIn = priceIn;
        this.priceOut = priceOut;
        this.quantity = quantity;
        this.discount = discount;
        this.productLines = productLine;
    }

    public Product() {
        id_product = null;
        productName = null;
        described = null;
        brand = null;
        priceIn = 0.0;
        priceOut = 0.0;
        quantity = 0;
        discount = 0.0;
    }

    public Product(String product_name) {
        Product p = new Product();
        p.setProductName(product_name);
    }

    public Product(String product_name, double priceOut, double discount) {
        this.productName = product_name;
        this.discount = discount;
        this.priceOut = priceOut;
    }

    public String getId_product() {
        return id_product;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescribed() {
        return described;
    }
   

    public String getBrand() {
        return brand;
    }

    public Double getPriceIn() {
        return priceIn;
    }

    public Double getPriceOut() {
        return priceOut;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public String getIdProductLine() {
        return id_productLine;
    }

    public String getProductLine() {
        return productLines;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPriceIn(Double priceIn) {
        this.priceIn = priceIn;
    }

    public void setPriceOut(Double priceOut) {
        this.priceOut = priceOut;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setProductLines(String productLines) {
        this.productLines = productLines;
    }
    
    

    public void updateQuantity(int changeQuantity, String type) {
        if (type.equals("out") || type.equals("OUT")) {
            this.quantity -= changeQuantity;
        }
        if (type.equals("in") || type.equals("IN")) {
            this.quantity += changeQuantity;
        }
    }


    public String toSave() {
        return id_product + "," +
                productName + "," +
                described + "," +
                brand + "," +
                priceIn + "," +
                priceOut + "," +
                quantity + "," +
                discount + "," +
                productLines;
    }
}
