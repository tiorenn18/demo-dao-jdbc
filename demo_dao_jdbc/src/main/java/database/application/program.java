package database.application;

import java.sql.SQLException;
import java.util.Date;
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
        List<Seller> list = sellerDao.findBydepartment(department);

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n === TEST 3: Seller findAll ===");
        list = sellerDao.findaAll();

        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n === TEST 4: Seller Insert ===");
        Seller newSeller = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new Id = "+ newSeller.getId()); 
        
        System.out.println("\n === TEST 5: Seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Marta Waine");
        sellerDao.update(seller);
        System.out.println("Updated Completed! ");
    }
}
