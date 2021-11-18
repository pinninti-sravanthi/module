package com.yoco.config;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import com.yoco.entity.User;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Configuration
public class ObjectifyConfig {

    @Bean
    public FilterRegistrationBean<ObjectifyFilter> objectifyFilterRegistration(){
        final FilterRegistrationBean<ObjectifyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ObjectifyFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<ObjectifyListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ObjectifyListener> bean =
                new ServletListenerRegistrationBean<>();
        bean.setListener(new ObjectifyListener());
        return bean;
    }

    @WebListener
    public class ObjectifyListener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {

            // if ("local".equals(System.getenv("SPRING_PROFILES_ACTIVE"))) {

            ObjectifyService.init();

//            ObjectifyService.init(new ObjectifyFactory(
//                    DatastoreOptions.newBuilder()
//                            .setHost("http://localhost:8080")
//                            .setProjectId("gajendran-mohan")
//                            .build()
//                            .getService()
//            ));

//        } else {
//                // on appengine
//                ObjectifyService.init(new ObjectifyFactory(
//                        DatastoreOptions.getDefaultInstance().getService()
//                ));
//        }

            ObjectifyService.register(User.class);
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {

        }
    }

}
