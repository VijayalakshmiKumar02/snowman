/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.db.dao;

import java.util.List;

import com.mycompany.entapp.snowman.domain.model.Client;

public interface ClientDao {

    Client getClient(int clientId);

    void saveClient(Client client);

    void updateClient(Client client);

    void removeClient(int clientId);

    List<Client> getAllClients();

}
