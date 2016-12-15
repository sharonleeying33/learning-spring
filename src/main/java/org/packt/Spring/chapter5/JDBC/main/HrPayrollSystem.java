
package org.packt.Spring.chapter5.JDBC.main;

import org.packt.Spring.chapter5.JDBC.dao.EmployeeDao;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 
 */
public class HrPayrollSystem {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        EmployeeDao employeeDao = context.getBean("employeeDaoImpl", EmployeeDao.class);
        
        employeeDao.createEmployee();
//        employeeDao.insertEmployee(new Employee(1, "Jay Lee"));
//        Employee employee = employeeDao.getEmployeeById(1);
//        System.out.println("Employee name : " + employee.getName() );
    }
    
}
