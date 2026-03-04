package database.application;

import java.sql.SQLException;

import database.classes.DaoFactory;
import database.classes.Seller;
import database.interfaces.SellerDao;

public class program {
    public static void main(String[] args) throws SQLException {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    }
}
