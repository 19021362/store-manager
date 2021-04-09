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
import java.util.Stack;

public class Manager {

    protected boolean statusLog = false;
    private ArrayList<User> users = new ArrayList<User>();
    private User currentUser;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Stack<Bill> bills = new Stack<Bill>();
    private ArrayList<Bill_detail> allBill_details = new ArrayList<>();

    //nạp dữ liệu nhân viên.
    public void loadUser() throws IOException {
        users = Load.loadUser();
    }

    //nap du lieu sp.
    public void loadProduct() throws IOException {
        products = Load.loadProduct();
    }

    public void loadBillDetail() throws IOException {
        allBill_details = Load.loadBillDetail(products);
    }

    public void loadBills() throws IOException {
        bills = Load.loadBills(allBill_details);
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
        bill.setIdBill("" + (bills.size()+1));
        bill.setEmployee(currentUser.getName());
        bill.setType("out");
        bill.setTime(Time.valueOf(LocalTime.now()));
        bill.setDate(Date.valueOf(LocalDate.now()));
        while (!in.equals("end")) {
            boolean isFound = false;

            System.out.print("nhập id: ");
            String id = sc.next();

            for (Product i : products) {
                if (i.getId_product().equals(id)) {
                    isFound = true;

                    System.out.print("nhập số lượng: ");
                    int quantity = sc.nextInt();

                    Bill_detail billDetail = new Bill_detail(bill.getIdBill(), i, quantity);

                    i.updateQuantity(quantity, bill.getType());
                    bill.addBillDetail(billDetail);
                    allBill_details.add(billDetail);
                }
            }

            if (!isFound) {
                System.out.println("Không có sp!");
            }
            System.out.print("có thêm sp? (con/end): ");
            in = sc.next();
        }

        bills.add(bill);
    }


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
    }
}
