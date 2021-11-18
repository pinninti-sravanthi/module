package com.yoco.services;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Component;

@Component
public class OfyService {

    public static <T> T get(Class<T> clazz, String key) {
        return ObjectifyService.ofy().load().type(clazz).id(key).now();
    }

    public static <T> Key<T> save(T entity) {
        return ObjectifyService.ofy().save().entity(entity).now();
    }
}
