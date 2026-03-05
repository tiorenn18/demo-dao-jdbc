package database.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import database.interfaces.DepartmentDao;

public class DepartmentDaojdbc implements DepartmentDao {

    private Connection conn;

    public DepartmentDaojdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        try (PreparedStatement st = conn.prepareStatement(
                "INSERT INTO department (Name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);) {
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department obj) {
        try (PreparedStatement st = conn.prepareStatement(
                " UPDATE department SET Name = ? WHERE ID = ?");) {

                    st.setString(1, obj.getName());
                    st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");) {
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department findById(Integer id) {
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM department WHERE Id = ?")) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Department dep = new Department();
                    dep.setId(id);
                    dep.setName(rs.getString("Name"));
                    return dep;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> findaAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findaAll'");
    }

}
