package org.packt.Spring.chapter5.JDBC.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getEmployeeCount() {
        String sql = "select count(*) from employee";
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    @Override
    public int insertEmployee(Employee employee) {
        String insertQuery = "insert into employee (Id, Name,Age) values (?, ?, ?) ";
        Object[] args = new Object[]{employee.getId(), employee.getName(), employee.getAge()};
        Object[] argTypes = new Object[]{Types.INTEGER, Types.VARCHAR, Types.INTEGER};
        return jdbcTemplate.update(insertQuery, args, argTypes);
    }

    @Override
    public int deleteEmployeeById(int id) {
        String delQuery = "delete from employee where Id =?";
        return jdbcTemplate.update(delQuery, id);
    }

    @Override
    public Employee getEmployeeById(int empId) {
        String query = "select * from Employee where Id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{1}, new RowMapper<Employee>() {

            @Override
            public Employee mapRow(ResultSet rs, int i) throws SQLException {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
            }
        });
    }

    @Override
    public void insertEmployees(List<Employee> employees) {
        String insertSql = "insert into employee (Id, Name,Age) values (?, ?, ?)";
        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Employee employee = employees.get(i);
                ps.setInt(1, employee.getId());
                ps.setString(2, employee.getName());
                ps.setInt(3, employee.getAge());
            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        
        });
    }    
}
