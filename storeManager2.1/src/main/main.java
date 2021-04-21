package main;

import main.Manager;
import application.Login;
import application.Login;
import user.User;

import javax.swing.text.PasswordView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Load.connectSQL();
        Manager.loadUser();
        
        
        Login login = new Login();
        login.show();
        
        Manager.loadProduct();
        Manager.loadBillDetail();
        Manager.loadBills();
        






    }
}
