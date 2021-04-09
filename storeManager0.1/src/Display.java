import bill.Bill;
import bill.Bill_detail;
import product.Product;

import java.util.ArrayList;
import java.util.Stack;

public class Display {

    public static void menu(String name) {
        System.out.println("**********username: " + name + "**********");
        System.out.println("                                               ");
        System.out.println("           1. tạo hóa đơn mới.                 ");
        System.out.println("           2. xem kho.                         ");
        System.out.println("           3. xem lịch sử hóa đơn.             ");
        System.out.println("           4. nhập sản phẩm mới.               ");
        System.out.println("           5. sửa thông tin sản phẩm.          ");
        System.out.println("           6. sửa thông tin tk.                ");
        System.out.println("           7. lợi nhuận.                       ");
        System.out.println("           8. thoát                            ");
        System.out.println("                                               ");
        System.out.println("***********************************************");
    }

    public static void printBill(Bill bill) {
        ArrayList<Bill_detail> bill_details = bill.getBill_details();

        System.out.println("***********************************************");
        System.out.println("* ID: " + bill.getIdBill() + "                 Time: " + bill.getTime() + "  " + bill.getDate());
        System.out.println("* Employee: " + bill.getEmployee());
        System.out.println("*| Stt       | Ten SP        | SL    | CK    | TT     ");
        for (int i = 0; i < bill_details.size(); i++) {
            System.out.println("*| " + (i+1) + " " + bill_details.get(i).toString());
        }
        System.out.println("*                                               ");
        System.out.println("*                           Tong: " + bill.getSum());
        System.out.println("*                                               ");
        System.out.println("*************Cam on quy khach!*******************");
        System.out.println("                                               ");
    }

    public static void printStore(ArrayList<Product> products) {
        System.out.println("***********************STORE***********************");
        System.out.println("*");
        System.out.println("*| id    | ten sp           | sl     ");
        for (Product p : products) {
            System.out.print("*| ");
            System.out.println(p.getId_product() + "   " + p.getProductName() + "    " + p.getQuantity());
        }
        System.out.println("*");
        System.out.println("****************************************************");
        System.out.println("");
    }


    public static void showHistoryBill(Stack<Bill> bills) {
        Stack<Bill> otherBills = (Stack<Bill>) bills.clone();
        System.out.println("************************HISTORY BILLS*************************");
        System.out.println("*");
        while (!otherBills.empty()) {
            Bill bill = otherBills.pop();
            System.out.println(bill.toString());
        }
        System.out.println("*");
        System.out.println("***************************************************************");
        System.out.println("");
    }




}
