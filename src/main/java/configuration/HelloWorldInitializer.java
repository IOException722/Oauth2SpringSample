package configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	System.out.println("HelloWorldInit.getRootConfigClasses");

        return new Class[] { HelloWorldConfiguration.class };
    }
    
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	System.out.println("HelloWorldInit.getServletConfigClasses");

        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
    	System.out.println("HelloWorldInit.getServletMappings");
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	System.out.println("HelloWorldInit.getServletFilters");

    	Filter [] singleton = { new CORSFilter()};
    	return singleton;
    }
 
}