/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.domain.service;

import java.util.List;

import com.mycompany.entapp.snowman.domain.model.Employee;

public interface EmployeeService {
    Employee getEmployee(int employeeId);

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    List<Employee> findAllEmployee();
}
