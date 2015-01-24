package net.predictblty.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by berkgokden on 1/4/15.
 */
public class Application extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public Application () {
        register(DomainResource.class);
        register(MyResource.class);
        register(JacksonFeature.class);
    }
}
