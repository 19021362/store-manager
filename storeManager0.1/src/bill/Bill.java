package bill;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import product.Product;
import user.*;

public class Bill {

    protected String idBill;
    protected Time time;
    protected Date date;
    protected String type;
    protected boolean status;
    protected long sum;
    protected User employee;
    protected ArrayList<Bill_detail> bill_details = new ArrayList<Bill_detail>();

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBill_details(ArrayList<Bill_detail> bill_details) {
        this.bill_details = bill_details;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Bill_detail> getBill_details() {
        return bill_details;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public String getIdBill() {
        return idBill;
    }

    public long getSum() {
        for (Bill_detail i : bill_details) {
            sum += i.getSumInLine();
        }
        return sum;
    }

    public String getType() {
        return type;
    }

    public User getEmployee() {
        return employee;
    }

    public void addBillDetail(Bill_detail bill_detail) {
        bill_details.add(bill_detail);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "idBill='" + idBill + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", sum=" + sum +
                ", employee=" + employee +
                ", bill_details=" + bill_details +
                '}';
    }
}
