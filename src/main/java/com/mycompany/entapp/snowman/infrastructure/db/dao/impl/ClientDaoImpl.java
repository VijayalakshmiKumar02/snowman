/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.db.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.entapp.snowman.EnterpriseApplication;
import com.mycompany.entapp.snowman.domain.model.Client;
import com.mycompany.entapp.snowman.infrastructure.db.dao.AbstractHibernateDao;
import com.mycompany.entapp.snowman.infrastructure.db.dao.ClientDao;


@Repository
@EnableTransactionManagement
public class ClientDaoImpl extends AbstractHibernateDao implements ClientDao {

    @Override
    @Transactional
    public Client getClient(int clientId) {
        return (Client) getCurrentSession().get(Client.class, clientId);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseApplication.class);

    @Override
    @Transactional
    public void saveClient(Client client) {
        LOGGER.info("Client ID {}", client.getId());
        LOGGER.info("Client ID {}", client.getClientName());
        LOGGER.info("Client Projects {}", client.getProjects());
        getCurrentSession().save(client);
    }


    @Override
    @Transactional
    public void updateClient(Client client) {
        getCurrentSession().update(client);
    }



    @Override
    @Transactional
    public void removeClient(int clientId) {
        Client clientToDelete = getClient(clientId);
        getCurrentSession().delete(clientToDelete);
    }

    @Override
    public List<Client> getAllClients() {
        // TODO Auto-generated method stub

        Session session = getCurrentSession();
        session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Client> clientList = session.createQuery("from Client").list();
        session.getTransaction().commit();
        return clientList;
    }
}
