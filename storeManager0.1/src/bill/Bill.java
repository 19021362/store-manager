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
    protected String employee;
    protected ArrayList<Bill_detail> bill_details = new ArrayList<Bill_detail>();
    protected long profit;

    public Bill() {
    }

    public Bill(String idBill, Time time, Date date, String type, long sum, String employee) {
        this.idBill = idBill;
        this.time = time;
        this.date = date;
        this.type = type;
        this.sum = sum;
        this.employee = employee;
        this.status = true;
    }

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

    public void setEmployee(String employee) {
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

    public long getProfit() {
        for (Bill_detail i : bill_details) {
            profit += i.getProfitInLine();
        }
        return profit;
    }

    public String getType() {
        return type;
    }

    public String getEmployee() {
        return employee;
    }

    public void addBillDetail(Bill_detail bill_detail) {
        bill_details.add(bill_detail);
    }

    @Override
    public String toString() {
        return  "idBill=" + idBill +
                "|  time=" + time +
                ", date=" + date +
                ", employee=" + employee +
                ", type='" + type +
                ", sum=" + sum;
    }

    public String toSave() {
        return idBill + "," +
                time + "," +
                date + "," +
                type + "," +
                sum + "," +
                employee;
    }
}
