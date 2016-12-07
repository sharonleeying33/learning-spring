
package org.packt.Spring.chapter5.JDBC.main;

import org.packt.Spring.chapter5.JDBC.dao.EmployeeDao;
import org.packt.Spring.chapter5.JDBC.dao.EmployeeDaoImpl;
import org.packt.Spring.chapter5.JDBC.model.Employee;

/**
 *
 * 
 */
public class HrPayrollSystem {
    
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeDao.createEmployee();
        employeeDao.insertEmployee(new Employee(1, "Jay Lee"));
        Employee employee = employeeDao.getEmployeeById(1);
        System.out.println("Employee name : " + employee.getName() );
    }
    
}
