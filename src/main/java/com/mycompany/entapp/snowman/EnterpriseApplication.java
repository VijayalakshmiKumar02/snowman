/*
 * |-------------------------------------------------
 * | Copyright © 2017 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.entapp.snowman;

import com.mycompany.entapp.snowman.infrastructure.scheduling.ReportingSnapshotJob;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class EnterpriseApplication {

    private static final int DEFAULT_PORT = 8099;

    private EnterpriseApplication() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EnterpriseApplication.class);
@Autowired
private static ReportingSnapshotJob reportingSnapshotJob;
    public static void main(String[] args) throws Exception {

        if(args!=null&&args.length==0 ) {
            final Server server = new Server();

            final ServerConnector serverConnector = new ServerConnector(server);

            serverConnector.setPort(resolvePort());

            LOGGER.info("Port has been set");

            server.setConnectors(new Connector[]{serverConnector});

            WebAppContext webAppContext = new WebAppContext();
            webAppContext.setDescriptor(getResource("webapp/WEB-INF/web.xml"));
            webAppContext.setResourceBase(getResource("webapp"));
            webAppContext.setContextPath("/");
            webAppContext.setParentLoaderPriority(true);

            LOGGER.info("Setting handler");

            server.setHandler(webAppContext);

            LOGGER.info("Web context has been set");

            server.start();

            LOGGER.info("Server started and running on port " + DEFAULT_PORT);


            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    if (server.isStarted()) {
                        server.setStopAtShutdown(true);

                        try {
                            server.stop();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }));

            server.join();
        }
        if(args!=null&&args.length>0 && args[0].equalsIgnoreCase("mycronjob") ){
        LOGGER.info("Execute task");
            reportingSnapshotJob.executeTask();
        }

        }


    private static String getResource(String resourceName) {
        URL resourceURL = EnterpriseApplication.class.getClassLoader().getResource(resourceName);
        if (resourceURL == null) {
            throw new RuntimeException("Unable to fetch specified resource: " + resourceName);
        }
        return resourceURL.toString();
    }

    private static int resolvePort() {
        try {
            return Integer.parseInt(System.getProperty("port"));
        } catch (NumberFormatException ex) {
            return DEFAULT_PORT;
        }
    }
}
