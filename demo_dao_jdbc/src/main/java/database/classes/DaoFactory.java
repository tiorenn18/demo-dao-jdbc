package database.classes;

import database.interfaces.SellerDao;

public class DaoFactory {
    public static SellerDao createSellerDao(){
        return new SellerDaoJdbc();
    }
}
