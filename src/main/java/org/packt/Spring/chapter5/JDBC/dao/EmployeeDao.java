
package org.packt.Spring.chapter5.JDBC.dao;

import org.packt.Spring.chapter5.JDBC.model.Employee;

/**
 *
 * 
 */
public interface EmployeeDao {

    Employee getEmployeeById(int id);

    /**
     * create employee table
     */
    void createEmployee();

    void insertEmployee(Employee employee);

}
