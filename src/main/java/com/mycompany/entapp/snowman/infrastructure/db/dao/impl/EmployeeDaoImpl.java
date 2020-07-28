/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.entapp.snowman.domain.model.Employee;
import com.mycompany.entapp.snowman.infrastructure.db.dao.AbstractHibernateDao;
import com.mycompany.entapp.snowman.infrastructure.db.dao.EmployeeDao;

@Repository
@EnableTransactionManagement
public class EmployeeDaoImpl extends AbstractHibernateDao implements EmployeeDao {
    @Override
    @Transactional
    public Employee retrieveEmployee(int employeeId) {

        // return null;
        return (Employee) getCurrentSession().get(Employee.class, employeeId);

    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(int employeeId) {
        getCurrentSession().delete(employeeId);
    }

    @Override
    public List<Employee> retrieveAllEmployee() {
        // TODO Auto-generated method stub

        Session session = getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Employee> employeeList = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        return employeeList;
    }
}
