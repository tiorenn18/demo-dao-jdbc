package database.application;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import database.classes.Department;
import database.classes.DepartmentDaoFactory;
import database.interfaces.DepartmentDao;

public class Program2 {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DepartmentDaoFactory.creatDepartmentDao();     
        
        System.out.println("=== TEST 1: findById =======");
		Department dep = departmentDao.findById(12);
		System.out.println(dep);

        System.out.println("\n=== TEST 2: findAll =======");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}

        System.out.println("\n === TEST 3: department Insert ===");
        Department newdepartment = new Department(null, "Carros");
        departmentDao.insert(newdepartment);
        System.out.println("Inserted! new Id = " + newdepartment.getId());

        System.out.println("\n=== TEST 4: update =======");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");

        System.out.println("\n === TEST 5: department delete ===");
        System.out.print("Digite um código Id: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");
        sc.close();


    }
}
