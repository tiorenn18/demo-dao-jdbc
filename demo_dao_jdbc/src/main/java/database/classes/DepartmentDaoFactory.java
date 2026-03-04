package database.classes;

import java.sql.SQLException;

import database.interfaces.DepartmentDao;

public class DepartmentDaoFactory {
    public static DepartmentDao creatDepartmentDao() throws SQLException{
       return new DepartmentDaojdbc(ConnectorFactory.getConnection());
    }
}
