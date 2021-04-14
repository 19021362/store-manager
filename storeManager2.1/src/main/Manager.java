package main;

import main.Load;
import main.Display;
import bill.Bill;
import bill.Bill_detail;
import product.Product;
import user.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Manager {

    protected static boolean statusLog = false;
    private static ArrayList<User> users = new ArrayList<User>();
    public static  User currentUser;
    private static ArrayList<Product> products = new ArrayList<Product>();
    public static Stack<Bill> bills = new Stack<Bill>();
    private static ArrayList<Bill_detail> allBill_details = new ArrayList<>(); 

    //nạp dữ liệu nhân viên.
    public static void loadUser() throws IOException {
        users = Load.loadUser();
    }

    //nap du lieu sp.
    public static void loadProduct() throws IOException {
        products = Load.loadProduct();
    }

    public static void loadBillDetail() throws IOException {
        allBill_details = Load.loadBillDetail(products);
    }

    public static void loadBills() throws IOException {
        bills = Load.loadBills(allBill_details);
    }

    public static boolean isLogIn(String username, String password) {
        
        boolean status = false;
        for (User u: users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                status = true;
                currentUser = u;
                break;
            }
        }
        return status;
    }

     //Đăng nhập
    public static void Login(String username, String password) {
        
    }


    //Tạo hóa đơn mới.
    public static Bill newBill(ArrayList<Bill_detail> bill_details, String sumOfBill) {

        Bill bill = new Bill();
        bill.setIdBill((bills.size()+1) + "");
        bill.setEmployee(currentUser.getUsername());
        bill.setType("out");
        bill.setTime(Time.valueOf(LocalTime.now()));
        bill.setDate(Date.valueOf(LocalDate.now()));
        bill.setSum(Double.parseDouble(sumOfBill));
        bill.setBill_details(bill_details);
        
        
        bills.add(bill);
        
        Load.saveBill(bill);
        
        return bill;
    }

    

    //xem lich su hoa don.
    public void showHistoryBill() {
        Scanner scanner = new Scanner(System.in);
        String in = "";
        while (!in.equals("no")) {
            boolean isFound = false;

            Display.showHistoryBill(bills);
            System.out.println("Xem hoa don co id: ");
            in = scanner.next();
            for (Bill i : bills) {
                if (i.getIdBill().equals(in)) {
                    Display.printBill(i);
                    isFound = true;
                }
            }

            if (!isFound && !in.equals("no")) {
                System.out.println("khong co hoa don nay!");
            }
        }
    }


    //them san pham moi.
    public void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean isFound = false;
        System.out.print("Nhập ID sản phẩm mới | Id = ");
        String id = scanner.nextLine();
        for (Product p : products) {

            if (p.getId_product().equals(id)) {
                System.out.println("Sản phẩm này đã tồn tại!");
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.print("Nhập tên sản phẩm    | Product_Name = ");
            String name = scanner.nextLine();
            System.out.print("Nhập mô tả           | Describe = ");
            String describe = scanner.nextLine();
            System.out.print("Nhập hãng sản phẩm   | Brand = ");
            String brand = scanner.nextLine();
            System.out.print("Nhập giá mua         | PriceIn = ");
            String priceIn = scanner.nextLine();
            System.out.print("Nhập giá bán         | PriceOut = ");
            String priceOut = scanner.nextLine();
            System.out.print("Nhập số lượng        | Quantity = ");
            String quantity = scanner.nextLine();
            System.out.print("Nhập giảm giá        | Discount = ");
            String discount = scanner.nextLine();
            System.out.print("Nhập dòng sản phẩm   | Product_Line = ");
            String productLine = scanner.nextLine();

            products.add(new Product(id, name, describe, brand, Double.parseDouble(priceIn),
                    Double.parseDouble(priceOut), Integer.parseInt(quantity), Double.parseDouble(discount), productLine));

            System.out.println("Thêm thành công sản phẩm:");
            System.out.println(products.get(products.size()-1).toSave());
        }
    }

    //menu
    /*
    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String in = "";
        while (!in.equals("8")) {

            Display.menu(currentUser.getName());
            System.out.print("Chọn chức năng: ");
            in = scanner.next();
            switch (in) {
                case "1":
                    newBill();
                    Display.printBill(bills.peek());
                    break;
                case "2":
                    Display.printStore(products);
                    break;
                case "3":
                    showHistoryBill();
                    break;
                case "4":
                    addNewProduct();
                    break;
                default:
                    in = "8";
                    try {
                        Load.saveData(users, products, bills, allBill_details);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }*/
}
