package Task7;

import java.sql.*;
import java.util.Scanner;

public class EmployeeDatabaseApp {

    // Database credentials
    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root"; // change to your MySQL username
    static final String PASSWORD = "password"; // change to your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Connected to the database!");

            while (true) {
                System.out.println("\n====== Employee Database Menu ======");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> {
                        System.out.println("🚪 Exiting...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("❌ Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add Employee
    public static void addEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);

            int rows = ps.executeUpdate();
            System.out.println("✅ Employee added successfully! Rows affected: " + rows);

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Employees
    public static void viewEmployees(Connection conn) {
        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📋 Employee List:");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("department"), rs.getDouble("salary"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Employee
    public static void updateEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Employee ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Department: ");
            String dept = sc.nextLine();
            System.out.print("Enter new Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            System.out.println("✅ Employee updated! Rows affected: " + rows);

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Employee
    public static void deleteEmployee(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Employee ID to delete: ");
            int id = sc.nextInt();
            sc.nextLine();

            String sql = "DELETE FROM employees WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println("🗑 Employee deleted! Rows affected: " + rows);

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
