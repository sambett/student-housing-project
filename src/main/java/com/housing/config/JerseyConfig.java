package com.housing.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // Register resources package
        packages("com.housing.resources");

        // Register Jackson for JSON processing
        register(JacksonFeature.class);
    }
}