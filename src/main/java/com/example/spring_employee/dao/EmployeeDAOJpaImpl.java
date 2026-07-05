package com.example.spring_employee.dao;

import com.example.spring_employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    //    setup constructor injection
    @Autowired

    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
//        create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

//        execute query
        List<Employee> employees = theQuery.getResultList();

//        return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        Employee theEmployee = entityManager.find(Employee.class, theId);

//        remove employee

        entityManager.remove(theEmployee);

    }
}
