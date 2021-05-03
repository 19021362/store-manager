/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Report {
    private Date date;
    private int total;
    private int profit;

    public Report(Date date, int total, int profit) {
        this.date = date;
        this.total = total;
        this.profit = profit;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public Date getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public int getProfit() {
        return profit;
    }

    @Override
    public String toString() {
        return "Report{" + "date=" + date + ", total=" + total + ", profit=" + profit + '}';
    }
    
    
           
}
