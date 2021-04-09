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

public class Load {

    private static String userFilePath = "file/user.txt";
    private static String productFilePath = "file/product.txt";
    private static String billsFilePath = "file/bills.txt";
    private static String billDetailFilePath = "file/billDetail.txt";

    public static ArrayList<User> loadUser() throws IOException {

        ArrayList<User> users = new ArrayList<>();
        FileReader userFile = new FileReader(userFilePath);
        BufferedReader br = new BufferedReader(userFile);

        String line = null;

        while((line = br.readLine()) != null) {
            String in[] = line.split("[,]");
            User user = new User(in[0], in[1], in[2], in[3], in[4], Long.parseLong(in[5]));
            users.add(user);
        }

        return users;
    }

    public static ArrayList<Product> loadProduct() throws IOException {

        ArrayList<Product> products = new ArrayList<>();
        FileReader productFile = new FileReader(productFilePath);
        BufferedReader br = new BufferedReader(productFile);

        String line = null;

        while((line = br.readLine()) != null) {
            String in[] = line.split("[,]");
            Product product = new Product(in[0], in[1], in[2], in[3], Double.parseDouble(in[4]),
                    Double.parseDouble(in[5]), Integer.parseInt(in[6]), Double.parseDouble(in[7]), in[8]);
            products.add(product);
        }
        return products;
    }


    public static Stack<Bill> loadBills(ArrayList<Bill_detail> allBillDetail) throws IOException {

        Stack<Bill> bills = new Stack<>();
        FileReader billFile = new FileReader(billsFilePath);
        BufferedReader br = new BufferedReader(billFile);

        String line = null;

        while((line = br.readLine()) != null) {
            String in[] = line.split("[,]");
            Bill bill = new Bill(in[0], Time.valueOf(in[1]), Date.valueOf(in[2]), in[3], Long.parseLong(in[4]), in[5]);
            bills.push(bill);
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
        FileReader bdtFile = new FileReader(billDetailFilePath);
        BufferedReader br = new BufferedReader(bdtFile);

        String line = null;

        while((line = br.readLine()) != null) {
            String in[] = line.split("[,]");
            Product product = new Product();
            for (Product p : products) {
                if (p.getProductName().equals(in[1])) {
                    product = p;
                }
            }
            Bill_detail billDetail = new Bill_detail(in[0], product, Integer.parseInt(in[2]));
            bill_details.add(billDetail);
        }
        return bill_details;
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



}
