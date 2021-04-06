package bill;

import product.*;

public class Bill_detail {
    protected Product productInline;
    protected int quantity;
    protected double sumInLine;

    public Bill_detail() {
        this.productInline = null;
        this.quantity = 0;

    }

    public Bill_detail(Product productInline, int quantity) {
        this.productInline = productInline;
        this.quantity = quantity;
    }

    public void setProductInline(Product productInline) {
        this.productInline = productInline;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "| " + productInline.getProductName() +
                "    | " + quantity +
                "    | " + productInline.getPriceOut() +
                "    | " + productInline.getDiscount() +
                "    |= " + getSumInLine();
    }
}
