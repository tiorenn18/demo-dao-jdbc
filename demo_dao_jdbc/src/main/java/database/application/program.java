package database.application;

import java.sql.SQLException;

import database.classes.DaoFactory;
import database.classes.Department;
import database.classes.Seller;
import database.interfaces.SellerDao;

public class program {
    public static void main(String[] args) throws SQLException {
        Department obj = new Department(1, "books");
        System.out.println(obj);

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    }
}
