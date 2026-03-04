package database.interfaces;

import java.util.List;

import database.classes.Department;
import database.classes.Seller;

public interface SellerDao {

    abstract void insert(Seller obj);
    abstract void update(Seller obj);
    abstract void deleteById(Integer id);
    abstract Seller findById(Integer id);
    abstract List<Seller> findaAll();
    abstract List<Seller> findBydepartment(Department department);
}
