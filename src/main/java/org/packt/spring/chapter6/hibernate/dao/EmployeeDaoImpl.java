
package org.packt.spring.chapter6.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 
 */

@Repository
@Transactional(readOnly = true)
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<org.packt.spring.chapter6.hibernate.model.Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        String hql = "from Employee";
        return session.createQuery(hql).list();
    }

    @Override
    @Transactional(readOnly = false)
    public void insertEmployee(org.packt.spring.chapter6.hibernate.model.Employee employee) {
        Session session = sessionFactory.openSession();
        session.save(employee);
    }
    
}
