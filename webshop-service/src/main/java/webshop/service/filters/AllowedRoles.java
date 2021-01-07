package webshop.service.filters;

import webshop.service.models.UserRole;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *Specifies the list of security roles permitted to access method(s) in an
 *application.
 */

@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface AllowedRoles {
    /**
     * List of roles that are permitted access, in enum style.
     */
    UserRole[] value();
}
