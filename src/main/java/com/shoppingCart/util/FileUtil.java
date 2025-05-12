package com.shoppingCart.util;

import com.shoppingCart.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<Product> readFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Product>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void writeToFile(String path, List<Product> cart) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(cart);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
