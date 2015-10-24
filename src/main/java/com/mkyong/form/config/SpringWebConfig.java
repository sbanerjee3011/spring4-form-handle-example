package com.mkyong.form.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.interceptors.ExecuteTimeInterceptor;
import com.interceptors.LoggingInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.mkyong.form.web", "com.mkyong.form.service", "com.mkyong.form.dao",
		"com.mkyong.form.exception", "com.mkyong.form.validator" })
       public class SpringWebConfig extends WebMvcConfigurerAdapter {

	//* Add a resource handler for properites file ..
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	// for view resolver... 
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	    public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasenames(new String[] { "messages/messagemvc", "messages/validation" });
	    rb.setBasenames(new String[] { "messages/messagemvc", "messages/validation" });
		return rb;
	}
	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoggingInterceptor());
	   // registry.addInterceptor(new ExecuteTimeInterceptor());
	   // registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");
	
	/* we can use addPathPatterns() method. If we do not add path pattern for
	 *  an interceptor, that will be available for every request. In the above code 
	 *  snippet, LoggingInterceptor is for all path pattern and TransactionInterceptor 
	 *  is only for /person/save/ path pattern request. 
	*/
	}

}