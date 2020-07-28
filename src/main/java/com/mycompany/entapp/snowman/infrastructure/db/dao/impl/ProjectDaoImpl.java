/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.db.dao.impl;

import com.mycompany.entapp.snowman.domain.model.Project;
import com.mycompany.entapp.snowman.infrastructure.db.dao.AbstractHibernateDao;
import com.mycompany.entapp.snowman.infrastructure.db.dao.ProjectDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public class ProjectDaoImpl extends AbstractHibernateDao implements ProjectDao {
    @Override
    @Transactional
    public Project retrieveProject(int projectId) {
        return (Project) getCurrentSession().get(Project.class, projectId);
    }

    @Override
    @Transactional
    public void saveProject(Project project) {
        getCurrentSession().save(project);
    }

    @Override
    @Transactional
    public void removeProject(int projectId) {
        getCurrentSession().delete(projectId);
    }
}
