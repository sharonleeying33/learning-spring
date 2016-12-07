package org.packt.Spring.chapter5.JDBC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    private DataSource dataSource;

    Connection conn = null;
    Employee employee = null;

    @Override
    public Employee getEmployeeById(int id) {
        try {
            conn = dataSource.getConnection();
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
            conn = dataSource.getConnection();
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
            conn = dataSource.getConnection();
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
