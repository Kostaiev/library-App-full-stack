package com.library.springbootlibrary.config;

import com.library.springbootlibrary.entity.Book;
import com.library.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configurer,
                                                     CorsRegistry corsRegistry){
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.PATCH,
                HttpMethod.PUT,
                HttpMethod.DELETE,
                HttpMethod.POST};

        configurer.exposeIdsFor(Book.class);
        configurer.exposeIdsFor(Review.class);

        disableHttpMethod(Book.class, configurer,theUnsupportedActions);
        disableHttpMethod(Review.class, configurer,theUnsupportedActions);

        /* Configure CORS Mapping */
        corsRegistry.addMapping(configurer.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
        
    }

    private void disableHttpMethod(Class theClass, RepositoryRestConfiguration configurer, HttpMethod[] theUnsupportedActions) {
        configurer.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}
