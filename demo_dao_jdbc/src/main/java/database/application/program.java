package database.application;

import java.util.Date;

import database.classes.DaoFactory;
import database.classes.Department;
import database.classes.Seller;
import database.interfaces.SellerDao;

public class program {
    public static void main(String[] args) {
        Department obj = new Department(1, "books");
        System.out.println(obj);

        Seller seller = new Seller(21, "Bob", "Bob@gmail.com", new Date(), 3000.0, obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(seller);
    }
}
