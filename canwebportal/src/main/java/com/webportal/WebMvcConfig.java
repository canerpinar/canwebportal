package com.webportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpEncodingProperties;
import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {	
	
	  @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	}

	@Bean
	  public SpringResourceTemplateResolver templateResolver() {
	    final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setCacheable(false);
	    templateResolver.setPrefix("classpath:/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setCharacterEncoding("UTF-8");
	    return templateResolver;
	  }

	  @Bean
	  public SpringTemplateEngine springTemplateEngine() {
	    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
	    springTemplateEngine.setTemplateResolver(templateResolver());	    
	    return springTemplateEngine;
	  }

	  @Bean
	  public ThymeleafViewResolver viewResolver() {
	    ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
	    thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
	    thymeleafViewResolver.setCharacterEncoding("UTF-8");
	    return thymeleafViewResolver;
	  }
	
	@Autowired
	private HttpEncodingProperties httpEncodingProperties;

	@Bean
	public OrderedCharacterEncodingFilter characterEncodingFilter() {
	    OrderedCharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
	    filter.setEncoding(this.httpEncodingProperties.getCharset().name());
	    filter.setForceEncoding(this.httpEncodingProperties.isForce());
	    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return filter;
	}
}
