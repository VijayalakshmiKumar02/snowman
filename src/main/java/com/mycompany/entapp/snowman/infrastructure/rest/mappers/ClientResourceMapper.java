/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.rest.mappers;

import com.mycompany.entapp.snowman.EnterpriseApplication;
import com.mycompany.entapp.snowman.infrastructure.rest.resources.ClientResource;
import com.mycompany.entapp.snowman.domain.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClientResourceMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseApplication.class);

    private ClientResourceMapper() {
    }

    public static Client mapToClient(ClientResource clientResource) {
        Client client = new Client();
        client.setId(clientResource.getClientId());
        client.setClientName(clientResource.getClientName());
        client.setProjects(ProjectResourceMapper.mapToProjects(clientResource.getProjects()));
        return client;
    }

    public static ClientResource mapToClientResource(Client client) {
        ClientResource clientResource = new ClientResource();
        clientResource.setClientId(client.getId());
        clientResource.setClientName(client.getClientName());
        clientResource.setProjects(ProjectResourceMapper.mapToProjectResources(client.getProjects()));
        return clientResource;
    }
}
