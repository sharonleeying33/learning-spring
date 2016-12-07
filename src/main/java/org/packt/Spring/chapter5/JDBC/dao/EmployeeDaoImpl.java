package org.packt.Spring.chapter5.JDBC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.packt.Spring.chapter5.JDBC.model.Employee;

/**
 *
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/hr";
    static final String USER = "root";
    static final String PWD = "root";

    Connection conn = null;
    Employee employee = null;

    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try {
            registerDriver();
            
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            //==> prepareStatement
            PreparedStatement ps = conn.prepareStatement("select * from EMPLOYEE where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee(id, rs.getString("name"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
        return employee;
    }

    @Override
    public void createEmployee() {

        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            //==> createStatement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("create table EMPLOYEE (ID int, NAME varchar (50))");
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void insertEmployee(Employee employee) {
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            //==> createStatement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into employee(id, name) values (1, '" +  employee.getName() + "')");
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }
}
