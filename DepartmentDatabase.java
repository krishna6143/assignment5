import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class DepartmentDatabase {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/departments";
    static final String USERNAME = "your_username";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {

        Department department = new Department(1, "IT");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
      
            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, department.getId());
            statement.setString(2, department.getName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new department was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
