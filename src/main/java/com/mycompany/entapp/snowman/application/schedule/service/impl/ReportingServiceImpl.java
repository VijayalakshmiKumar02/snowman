/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.application.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.entapp.snowman.application.schedule.ReportingData;
import com.mycompany.entapp.snowman.application.schedule.service.ReportingService;
import com.mycompany.entapp.snowman.domain.exception.BusinessException;
import com.mycompany.entapp.snowman.domain.model.Client;
import com.mycompany.entapp.snowman.domain.model.Employee;
import com.mycompany.entapp.snowman.domain.model.EmployeeProject;
import com.mycompany.entapp.snowman.domain.model.Project;
import com.mycompany.entapp.snowman.domain.model.User;
import com.mycompany.entapp.snowman.domain.service.ApplicationInfoService;
import com.mycompany.entapp.snowman.domain.service.EmployeeService;
import com.mycompany.entapp.snowman.domain.service.UserService;

@Service
public class ReportingServiceImpl implements ReportingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportingServiceImpl.class);

    @Autowired
    private ApplicationInfoService applicationInfoService;

    // @Autowired
    // private ClientService clientService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingData retrieveReportingData() throws BusinessException {

        ReportingData reportingData = new ReportingData();

        List<User> userList = userService.findAllUsers();

        List<Employee> employeeList = employeeService.findAllEmployee();

        List<Project> projectList = new ArrayList<>();

        List<Client> clientList = new ArrayList<>();

        if (null != employeeList && !employeeList.isEmpty()) {

            for (Employee employee : employeeList) {
                Set<EmployeeProject> employeeProjectList = employee.getProjects();

                if (null != employeeProjectList && !employeeProjectList.isEmpty()) {
                    for (EmployeeProject employeeProject : employeeProjectList) {
                        Project projectForEachEmployee = employeeProject.getProject();
                        if (null != projectForEachEmployee) {
                            Client projectClient = projectForEachEmployee.getClient();
                            clientList.add(projectClient);
                            projectList.add(projectForEachEmployee);
                        } else {
                            LOGGER.info("No Project Found For the Employee Id : {}, Name : {} ", employee.getId(),
                                    employee.getFirstname());
                        }

                    }
                } else {
                    LOGGER.info(
                            "Project and Employee is Not Mapped in Employee_Project Entity For the Employee Id : {}, Name : {} ",
                            employee.getId(), employee.getFirstname());
                }

            }
        }

        // projectList = projectService.getAllProjects();

        // clientList = clientService.getAllClients();

        reportingData.setAppInfo(applicationInfoService.getAppInfo());
        reportingData.setUsers(userList);
        reportingData.setEmployees(employeeList);
        reportingData.setProjects(projectList);
        reportingData.setClients(clientList);
        return reportingData;
    }
}
