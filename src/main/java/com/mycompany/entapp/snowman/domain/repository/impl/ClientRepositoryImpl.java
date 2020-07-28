/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.domain.repository.impl;

import com.mycompany.entapp.snowman.domain.model.Client;
import com.mycompany.entapp.snowman.domain.repository.ClientRepository;
import com.mycompany.entapp.snowman.infrastructure.db.dao.ClientDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private ClientDao clientDao;

    @Cacheable(value = "clientfindcache", key = "#clientId")
    @CachePut(value = "clientfindcache", key = "#clientId")
    @Override
    public Client getClient(int clientId){
        return clientDao.getClient(clientId);
    }

    @Override
    @CachePut(value = "clientfindcache")
    public void createClient(Client client){
        clientDao.saveClient(client);
    }

    // @CachePut(value = "clientfindcache", key = "#clientId")
    @Override
    public void updateClient(Client client){
        clientDao.updateClient(client);
    }

    @CacheEvict(value = "clientfindcache", key = "#clientId")
    @Override
    public void deleteClient(int clientId){
        clientDao.removeClient(clientId);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }
}
