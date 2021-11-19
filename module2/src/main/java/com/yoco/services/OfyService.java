package com.yoco.services;

import com.google.api.client.util.Lists;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.Cursor;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;
import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfyService {

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static <T> T get(Class<T> clazz, String key) {
        return ofy().load().type(clazz).id(key).now();
    }

    public static <T> T get(Class<T> clazz, long key) {
        return ofy().load().type(clazz).id(key).now();
    }

    public static <T> Key<T> save(T entity) {
        return ofy().save().entity(entity).now();
    }

    public static <T> CollectionResponse<T> fetchCursorQuery(Query<T> query, int limit, String cursor) {

        try {

            if (cursor != null){
                query = query.startAt(Cursor.fromUrlSafe(cursor));
            }

            query = query.limit(limit);

            QueryResults<T> iterator =  query.iterator();

            List<T> items = Lists.newArrayList(iterator);

            String cursorString = null;

            if(iterator.getCursorAfter() != null){
                cursorString = items.size() < limit ? null : iterator.getCursorAfter().toUrlSafe();
            }

            return CollectionResponse.<T>builder()
                    .setItems(items)
                    .setCursor(cursorString)
                    .build();

        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
