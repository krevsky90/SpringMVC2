package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * instead of dispatcher-servlet.xml
 */
@Configuration
@EnableWebMvc   //equals <mvc:annotation-driven/>
@ComponentScan("app")
@ImportResource({"WEB-INF/persistence-config.xml", "WEB-INF/spring-aop.xml"})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FreeMarkerViewResolver viewResolver() {//name of method equals id of bean in xml
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setSuffix(".ftl");
        viewResolver.setCache(false);
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/templates");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        freeMarkerConfigurer.setFreemarkerSettings(new Properties() {
            {
                this.put("defaultEncoding", "UTF-8");
            }
        });
        return freeMarkerConfigurer;
    }
}
