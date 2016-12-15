package org.packt.Spring.chapter5.JDBC.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    private DataSource dataSource;
    
    private SimpleJdbcCall jdbcCall;
    
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcCall = new SimpleJdbcCall(this.dataSource).withProcedureName("getEmployee");
    }

    @Override
    public Employee useStoreProcedure2GetEmployee(int id) {
        
        // procedure中 Emp_Name,Emp_Age 是大寫, 但回傳皆是小寫
        SqlParameterSource in = new MapSqlParameterSource().addValue("_id", id);
        Map<String, Object>result = jdbcCall.execute(in);

        // varchar 取出來型態是 byte[]
        byte[] name = (byte[])result.get("emp_name");
        System.out.printf("name = {%s}%n " , new String(name));
       
        return new Employee(id, new String((byte[])result.get("emp_name")), (int)result.get("emp_age"));
    }
    
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
