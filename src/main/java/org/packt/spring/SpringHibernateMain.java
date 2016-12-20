
package org.packt.spring;

import org.packt.spring.chapter6.hibernate.model.Employee;
import org.packt.spring.chapter6.hibernate.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 
 */
public class SpringHibernateMain {
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("hibernateWithSpring.xml");
        EmployeeService employeeService = context.getBean("employeeServiceImpl", EmployeeService.class);
        
        Employee emp = new Employee();
        emp.setFirstName("Shree");
        emp.setLastName("Kant");
        emp.setJobTitle("Software Engineer");
        emp.setDepartment("Technology");
        emp.setSalary(3000);
        employeeService.insertEmployee(emp);

        for (Employee employee : employeeService.getAllEmployees()) {
            System.out.println(employee);
        }
            
    }
    
}
