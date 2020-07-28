/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman.infrastructure.rest.endpoint;

import com.mycompany.entapp.snowman.EnterpriseApplication;
import com.mycompany.entapp.snowman.domain.exception.SnowmanException;
import com.mycompany.entapp.snowman.infrastructure.rest.mappers.ClientResourceMapper;
import com.mycompany.entapp.snowman.infrastructure.rest.resources.ClientResource;
import com.mycompany.entapp.snowman.domain.model.Client;
import com.mycompany.entapp.snowman.domain.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientRestEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRestEndpoint.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<ClientResource> getClientInfo(@PathVariable("clientId") Integer clientId) {
        Client client = clientService.getClient(clientId);
        ClientResource clientResource = ClientResourceMapper.mapToClientResource(client);
        return ResponseEntity.ok(clientResource);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public void createClientInfo(@RequestBody ClientResource clientResource) {
        Client client = ClientResourceMapper.mapToClient(clientResource);
        try {
            clientService.createClient(client);
        } catch (SnowmanException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateClientInfo(@RequestBody ClientResource clientResource) {
        Client client = ClientResourceMapper.mapToClient(clientResource);
        try {
            LOGGER.info("Client Name {}",client.getClientName());
            clientService.updateClient(client);
        } catch (SnowmanException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
    public void deleteClientInfo(@PathVariable("clientId") Integer clientId) {
        try {
            clientService.deleteClient(clientId);
        } catch (SnowmanException e) {
            throw new RuntimeException(e);
        }
    }
}
