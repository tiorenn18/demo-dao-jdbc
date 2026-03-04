package database.application;

import java.sql.SQLException;
import java.util.List;

import database.classes.DaoFactory;
import database.classes.Department;
import database.classes.Seller;
import database.interfaces.SellerDao;

public class program {
    public static void main(String[] args) throws SQLException {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: Seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

        System.out.println("\n === TEST 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List <Seller> list = sellerDao.findBydepartment(department);
        
        for (Seller obj : list) {
            System.out.println(obj);
        }
    }
}
