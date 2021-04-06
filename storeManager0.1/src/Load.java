import product.Product;
import user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {

    private static String userFilePath = "file/user.txt";
    private static String productFilePath = "file/product.txt";

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



}
