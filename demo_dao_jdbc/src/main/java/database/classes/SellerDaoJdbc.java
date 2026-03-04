package database.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.interfaces.SellerDao;

public class SellerDaoJdbc implements SellerDao {

    private Connection conn;

    public SellerDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    public SellerDaoJdbc(Class<ConnectorFactory> class1) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void insert(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Seller findById(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "Select seller. *, department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "WHERE seller.Id = ?");) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery();) {
                if (rs.next()) {
                    Department dep = new Department();
                    dep.setId(rs.getInt("DepartmentId"));
                    dep.setName(rs.getString("DepName"));
                    Seller obj = new Seller();
                    obj.setId(rs.getInt("Id"));
                    obj.setName(rs.getString("Name"));
                    obj.setEmail(rs.getString("Email"));
                    obj.setBaseSalary(rs.getDouble("BaseSalary"));
                    obj.setBirthDate(rs.getDate("BirthDate"));
                    obj.setDepartment(dep);
                    return obj;

                }
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro em achar o Id: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Seller> findaAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findaAll'");
    }

}
