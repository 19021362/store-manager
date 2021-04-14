package main;

import bill.Bill;
import bill.Bill_detail;
import product.Product;
import user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Load {

    private static String userFilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\manageStore\\src\\main\\java\\file\\user.txt";
    private static String productFilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\manageStore\\src\\main\\java\\file\\product.txt";
    private static String billsFilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\manageStore\\src\\main\\java\\file\\bills.txt";
    private static String billDetailFilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\manageStore\\src\\main\\java\\file\\billDetail.txt";
    
    private static Connection connection = null;
    
    public static void connectSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/manage_store", "root", "");
            System.out.println("connect success.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<User> loadUser() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        try {
            
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));
                users.add(user);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public static ArrayList<Product> loadProduct() throws IOException {

        ArrayList<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                Product product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                    Double.parseDouble(rs.getString(5)), Double.parseDouble(rs.getString(6)),
                                    Integer.parseInt(rs.getString(7)), Double.parseDouble(rs.getString(9)), rs.getString(8));
                products.add(product);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    public static Stack<Bill> loadBills(ArrayList<Bill_detail> allBillDetail) throws IOException {

        Stack<Bill> bills = new Stack<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery("SELECT * FROM bills");

            while (rs.next()) {
                Bill bill = new Bill(rs.getString(1), rs.getDate(2), rs.getTime(3),
                                rs.getString(4), rs.getLong(5), rs.getString(6));
                bills.push(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Bill b : bills) {
            for (Bill_detail bd : allBillDetail) {
                if (bd.getId_bill().equals(b.getIdBill())) {
                    b.addBillDetail(bd);
                }
            }
        }

        return bills;
    }


    public static ArrayList<Bill_detail> loadBillDetail(ArrayList<Product> products) throws IOException {

        ArrayList<Bill_detail> bill_details = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs =  statement.executeQuery("SELECT * FROM bill_details");

            while (rs.next()) {
                Product product = new Product();
                for(Product p : products) {
                    if (rs.getString(1).equals(p.getProductName())) {
                        product = p;
                    }
                }
                Bill_detail bill_detail = new Bill_detail(rs.getString(1), product, rs.getInt(3));
                bill_details.add(bill_detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill_details;
    }
    
    
    public static ArrayList<String> newRow(String productName, int quantity, int row) {
        ArrayList<String> objects = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/manage_store", "root", "");
            Statement statement = connection.createStatement();
            String queryString = "SELECT `product_name`, `priceOut`,`discount` FROM products WHERE `product_name` = " + "'" + productName + "'";
            ResultSet rs = statement.executeQuery(queryString);
            while(rs.next()) {
                objects.add(row + "");
                objects.add(rs.getString(1));
                objects.add(quantity + "");
                objects.add(rs.getDouble(2) + "");                
                objects.add(rs.getDouble(3) + "");
                objects.add((rs.getDouble(2)*quantity*(1-rs.getDouble(3))) + "");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return objects;
    }



    public static void saveData(ArrayList<User> users, ArrayList<Product> products, Stack<Bill> bills, ArrayList<Bill_detail> bill_details) throws IOException {
        FileWriter fileUser = new FileWriter(userFilePath);
        for (User u : users) {
            fileUser.write(u.toSave() + "\n");
        }
        fileUser.close();

        FileWriter fileProduct = new FileWriter(productFilePath);
        for (Product p : products) {
            fileProduct.write(p.toSave() + "\n");
        }
        fileProduct.close();

        FileWriter fileBills = new FileWriter(billsFilePath);
        for (Bill b : bills) {
            fileBills.write(b.toSave() + "\n");
        }
        fileBills.close();

        FileWriter fileBillDetail = new FileWriter(billDetailFilePath);
        for (Bill_detail bd : bill_details) {
            fileBillDetail.write(bd.toSave() + "\n");
        }
        fileBillDetail.close();
    }

    static void saveBill(Bill bill) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/manage_store", "root", "");
            String query = " insert into bills (id_bill, date, time, type, total, employee_name)"
                + " values (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, bill.getIdBill());
            preparedStmt.setDate(2, (Date) bill.getDate());
            preparedStmt.setTime(3, bill.getTime());
            preparedStmt.setString(4, bill.getType());
            preparedStmt.setDouble(5, bill.getSum());
            preparedStmt.setString(6, bill.getEmployee());

            // execute the preparedstatement
            preparedStmt.execute();
      
            connection.close();                                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saveBillDetail(bill.getBill_details());
    }

    private static void saveBillDetail(ArrayList<Bill_detail> bill_details) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/manage_store", "root", "");
            String query1 = " insert into `bill_details` (`bill_id`, `name_product`, `each_quantity`)"
                    + " values (?, ?, ?);" + "\n";  
                    
            for (int i = 0; i < bill_details.size(); i++) {
                
                Bill_detail get = bill_details.get(i);
                
                PreparedStatement preparedStmt = connection.prepareStatement(query1);
                preparedStmt.setString (1, get.getId_bill());
                preparedStmt.setString(2, get.getProductInline().getProductName());
                preparedStmt.setInt(3, get.getQuantity());

                // execute the preparedstatement
                preparedStmt.execute();
            }
            
            for (int i = 0; i < bill_details.size(); i++) {
                Bill_detail get = bill_details.get(i);
                String query2 = " UPDATE `products`" + " " +
                    " SET `quantity` = `quantity` - " + get.getQuantity() + " " +
                    " WHERE `product_name` = '" + get.getProductInline().getProductName() + "' ;";
                
                PreparedStatement st = connection.prepareStatement(query2);
                st.execute();
            }

            connection.close();                                    
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}