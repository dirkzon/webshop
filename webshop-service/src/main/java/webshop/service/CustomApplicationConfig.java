package webshop.service;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import webshop.service.filters.AuthenticationFilter;
import webshop.service.filters.AuthorisationFilter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomApplicationConfig extends ResourceConfig {


	public CustomApplicationConfig()
	{
		packages("webshop.service.resources");

		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));

		register(new CorsFilter());

		register(new DependencyBinder("SQL"));

		register(new AuthorisationFilter());
		register(new AuthenticationFilter());
	}
}
