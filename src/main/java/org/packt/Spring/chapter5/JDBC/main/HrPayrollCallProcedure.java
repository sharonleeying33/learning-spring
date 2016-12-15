
package org.packt.Spring.chapter5.JDBC.main;

import org.packt.Spring.chapter5.JDBC.dao.EmployeeDao;
import org.packt.Spring.chapter5.JDBC.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 
 */
public class HrPayrollCallProcedure {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        EmployeeDao dao = (EmployeeDao) context.getBean("employeeDaoImpl");
        dao.useStoreProcedure2GetEmployee(10001);
        
    }
    
    
    
    
}
