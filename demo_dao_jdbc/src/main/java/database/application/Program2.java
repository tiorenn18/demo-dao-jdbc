package database.application;

import java.sql.SQLException;

import database.classes.Department;
import database.classes.DepartmentDaoFactory;
import database.interfaces.DepartmentDao;

public class Program2 {
    public static void main(String[] args) throws SQLException {

        DepartmentDao departmentDao = DepartmentDaoFactory.creatDepartmentDao();

        System.out.println("\n === TEST 4: Seller Insert ===");
        Department newdepartment = new Department(5, "Carros");
        departmentDao.insert(newdepartment);
        System.out.println("Inserted! new Id = "+ newdepartment.getId());
    }
}
