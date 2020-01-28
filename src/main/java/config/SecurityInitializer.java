package config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
//2 - means this initializer works after WebInitializer that has order = 1
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //to help Spring to see SecurityConfig.java file
}
