package database.interfaces;

import java.util.List;

import database.classes.Department;

public abstract class DepartmentDao {
    
    abstract void insert (Department obj);
    abstract void update (Department obj);
    abstract void deleteById (Integer id);
    abstract Department findById (Integer id);
    abstract List<Department> findaAll();

}
