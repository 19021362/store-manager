package bill;

import product.*;

public class Bill_detail {
    protected Product productInline;
    protected int quantity;
    protected String id_bill;


    public Bill_detail() {
        this.productInline = null;
        this.quantity = 0;
    }

    public Bill_detail(String id_bill, Product productInline, int quantity) {
        this.id_bill = id_bill;
        this.productInline = productInline;
        this.quantity = quantity;
    }

    public Bill_detail(String id_bill,String product_name, int quantity) {
        this.quantity = quantity;
        this.id_bill = id_bill;
        this.productInline = new Product();
        this.productInline.setProductName(product_name);
    }
    
    



    public void setProductInline(Product productInline) {
        this.productInline = productInline;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id_bill = id;
    }

    public String getId_bill() {
        return id_bill;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public Product getProductInline() {
        return productInline;
    }

    public double getSumInLine() {
        return productInline.getPriceOut()*quantity*(1 - productInline.getDiscount());
    }

    public double getProfitInLine() {
        return getSumInLine() - (productInline.getPriceIn()*quantity);
    }

    @Override
    public String toString() {
        return "| " + productInline.getProductName() + "\t\t" +
                "| " + quantity + "\t" +
                "| " + productInline.getPriceOut() + "\t" +
                "| " + productInline.getDiscount() + "\t" +
                "|= " + getSumInLine() + "\t";
    }

    public String toSave() {
        return id_bill + "," +
                productInline.getProductName() + "," +
                quantity;
    }
}
