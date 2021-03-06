/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.domain.service.impl;

import com.mycompany.entapp.snowman.domain.model.Project;
import com.mycompany.entapp.snowman.domain.repository.ProjectRepository;
import com.mycompany.entapp.snowman.domain.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project getProject(int projectId) {
        return projectRepository.findProject(projectId);
    }

    @Override
    @Transactional
    public void createProject(Project project) {
        projectRepository.saveProject(project);
    }

    @Override
    @Transactional
    public void updateProject(Project project) {
        Project existingProject = getProject(project.getId());
        if (existingProject == null) {
            throw new RuntimeException("Can't update an unknown project " + project);
        }

        projectRepository.saveProject(project);
    }

    @Override
    @Transactional
    public void deleteProject(int projectId) {
        Project existingProject = getProject(projectId);
        if (existingProject == null) {
            throw new RuntimeException("Can't remove an unknown project with id: " + projectId);
        }

        projectRepository.removeProject(projectId);
    }
}
