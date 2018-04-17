package vn.smartdev.book.manager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import vn.smartdev.book.manager.utils.WebAttributes;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter implements WebAttributes{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/*",
                "/controller/**",
                "/service/**")
                .addResourceLocations(
                        "classpath:/static/",
                        "classpath:/static/controller/",
                        "classpath:/static/service/");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
