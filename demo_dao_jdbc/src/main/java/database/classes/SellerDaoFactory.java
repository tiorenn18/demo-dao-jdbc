package database.classes;

import java.sql.SQLException;

import database.interfaces.SellerDao;

public class SellerDaoFactory {
    public static SellerDao createSellerDao() throws SQLException{
        return new SellerDaoJdbc(ConnectorFactory.getConnection());
    }
}
