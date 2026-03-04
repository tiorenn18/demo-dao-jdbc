package database.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try (PreparedStatement st = conn.prepareStatement(
                " INSERT INTO seller "
                        + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                        + "VALUES "
                        + "(?, ?, ?, ?, ?)",
                        java.sql.Statement.RETURN_GENERATED_KEYS
            );
        ) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0 ) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                } catch (SQLException e) {
                    System.out.println("Unexpected error! No rows! ");
                }
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Seller obj) {
        try (PreparedStatement st = conn.prepareStatement(
                " UPDATE seller "
                        + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                        + "WHERE ID = ?"
            );
        ) {
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.getStackTrace();
        }
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
                    Department dep = instantiaeteDepartment(rs);
                    Seller obj = instantiaeteSeller(rs, dep);
                    return obj;
                }
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro em achar o Id: " + e.getMessage());
        }
        return null;
    }

    private Department instantiaeteDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiaeteSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    @Override
    public List<Seller> findaAll() {
        try (PreparedStatement st = conn.prepareStatement(
                "Select seller. *, department.Name as DepName "
                        + "FROM seller INNER JOIN department "
                        + "ON seller.DepartmentId = department.Id "
                        + "ORDER BY Name");) {

            try (ResultSet rs = st.executeQuery();) {
                List<Seller> list = new ArrayList<>();
                Map<Integer, Department> map = new HashMap<>();

                while (rs.next()) {
                    Department dep = map.get(rs.getInt("DepartmentId"));
                    if (dep == null) {
                        dep = instantiaeteDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                    }

                    Seller obj = instantiaeteSeller(rs, dep);
                    list.add(obj);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todos os vendedores: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Seller> findBydepartment(Department department) {
        try (PreparedStatement st = conn.prepareStatement(
                "Select seller. *, department.Name as DepName "
                        + "FROM seller INNER JOIN department "
                        + "ON seller.DepartmentId = department.Id "
                        + "WHERE department.Id = ? "
                        + "ORDER BY Name");) {

            st.setInt(1, department.getId());
            try (ResultSet rs = st.executeQuery();) {
                List<Seller> list = new ArrayList<>();
                Map<Integer, Department> map = new HashMap<>();

                while (rs.next()) {
                    Department dep = map.get(rs.getInt("DepartmentId"));
                    if (dep == null) {
                        dep = instantiaeteDepartment(rs);
                        map.put(rs.getInt("DepartmentId"), dep);
                    }

                    Seller obj = instantiaeteSeller(rs, dep);
                    list.add(obj);
                }
                return list;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar vendedores por departamento: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
