package webshop.service;


import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import webshop.persistence.Context;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomApplicationConfig extends ResourceConfig {

	public static final Context context = Context.Memory;

	public CustomApplicationConfig()
	{
		packages("webshop.service.resources");

		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
				Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));

		register(new CorsFilter());

		register(new DependencyBinder(context));

		register(new AuthenticationFilter());
	}
}
