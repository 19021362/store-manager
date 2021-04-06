import bill.Bill;
import bill.Bill_detail;
import product.Product;
import user.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

    protected boolean statusLog = false;
    private ArrayList<User> users = new ArrayList<User>();
    private User currentUser;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Bill> bills = new ArrayList<Bill>();

    //nạp dữ liệu nhân viên.
    public void loadUser() throws IOException {
        users = Load.loadUser();
    }

    //nap du lieu sp.
    public void loadProduct() throws IOException {
        products = Load.loadProduct();
    }

    public boolean isLogIn(ArrayList<User> users, User user) {
        boolean status = false;
        for (User u: users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                status = true;
                user.setIdUser(u.getIdUser());
                user.setJobTitle(u.getJobTitle());
                user.setName(u.getName());
                user.setSalary(u.getSalary());
                break;
            }
        }
        return status;
    }

    //Đăng nhập
    public void Login() {
        Scanner scanner = new Scanner(System.in);
        String again = "y";
        while(again.equals("y")) {
            System.out.print("usename: ");
            String username = scanner.next();
            System.out.print("password: ");
            String password = scanner.next();

            User newUser = new User(username, password);

            if (isLogIn(users, newUser)) {
                System.out.println("Xin chào " + newUser.getName());
                currentUser = newUser;
                statusLog = true;
                break;
            } else {
                System.out.println("Incorrect username or password!");
                System.out.print("Login again? (y/n): ");
                again = scanner.next();
            }
        }
    }


    //Tạo hóa đơn mới.
    public void newBill() {
        Scanner sc = new Scanner(System.in);
        String in = "";

        Bill bill = new Bill();
        bill.setIdBill("1");
        bill.setEmployee(users.get(0));
        bill.setType("out");
        bill.setTime(Time.valueOf(LocalTime.now()));
        bill.setDate(Date.valueOf(LocalDate.now()));
        while (!in.equals("end")) {
            Product product = new Product();

            System.out.print("nhập id: ");
            String id = sc.next();

            for (Product i : products) {
                if (i.getId_product().equals(id)) {
                    product = i;
                }
            }

            if (product.getProductName() == null) {
                System.out.println("Không có sp!");
            } else {
                Bill_detail billDetail = new Bill_detail();
                billDetail.setProductInline(product);

                System.out.print("nhập số lượng: ");
                billDetail.setQuantity(sc.nextInt());

                bill.addBillDetail(billDetail);
            }
            System.out.print("có thêm sp? (con/end): ");
            in = sc.next();
        }

        bills.add(bill);
    }



    //In hóa đơn.
    public void printBill() {
        System.out.println(bills.get(bills.size()-1).toString());
    }


    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        String in = "";
        while (!in.equals("8")) {

            Display.menu(currentUser.getName());

            in = scanner.next();
            switch (in) {
                case "1":
                    newBill();
                    Display.printBill(bills.get(bills.size()-1));
                    break;
                default:
                    in = "8";
                    break;
            }

        }
    }
}
