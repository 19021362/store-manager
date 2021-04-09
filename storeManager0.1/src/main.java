import user.User;

import javax.swing.text.PasswordView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Manager manager  = new Manager();

        manager.loadUser();
        manager.Login();
        if (manager.statusLog) {
            manager.loadProduct();
            manager.loadBillDetail();
            manager.loadBills();
            manager.mainMenu();
        }



    }
}
