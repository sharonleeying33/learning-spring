package org.packt.Spring.chapter5.JDBC.main;

import java.util.ArrayList;
import java.util.List;
import org.packt.Spring.chapter5.JDBC.dao.EmployeeDao;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 */
public class HrPayrollBatchUpdate {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        EmployeeDao dao = (EmployeeDao) context.getBean("employeeDaoImpl");

        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(10001, "Ravi", 10);
        Employee employee2 = new Employee(23330, "Kant", 20);
        Employee employee3 = new Employee(12568, "Soni", 30);
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        dao.insertEmployees(employeeList);
        System.out.println(dao.getEmployeeCount());
    }

}
