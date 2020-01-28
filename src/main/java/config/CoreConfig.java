package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy //doesn't work! I added EnableAspectJAutoProxy annotation to WebConfig.java file
@ComponentScan({"app.aspect", "app.service", "app.utils"})
public class CoreConfig {
}