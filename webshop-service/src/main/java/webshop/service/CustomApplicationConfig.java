package webshop.service;


import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import webshop.persistence.Context;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomApplicationConfig extends ResourceConfig {

	public CustomApplicationConfig()
	{
		register(new CorsFilter());

		packages("webshop.service.resources");

		register(new DependencyBinder(Context.Memory));

		//register(new AuthenticationFilter());

		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));
	}
}
