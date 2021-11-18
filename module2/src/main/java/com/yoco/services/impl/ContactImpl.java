package com.yoco.services.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.services.OfyService;
import com.yoco.services.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContactImpl implements ContactDao {

    @Autowired
    OfyService ofyService;

    @Override
    public Contact save(Contact contact) {

        String jsonString = new com.google.gson.Gson().toJson(contact);

        System.out.println(" in save contact impl " + jsonString);

        Key<Contact> resp = ofyService.save(contact);

        System.out.println(resp);

        return contact;
    }

    @Override
    public Contact get(long id) {

        System.out.println(" in fetch contact impl " + id);

        Contact resp =  ofyService.get(Contact.class,id);

        System.out.println(resp);
        return resp;
    }

    @Override
    public Contact getByFilter(String name, String password) {

        Contact obj  =   ofyService.ofy().load().type(Contact.class)
                .filter("name", name)
                .filter("password",password)
                .limit(1).first().now();

        return obj;
    }

    @Override
    public List<Contact> getAll(String name, String password) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("name", name)
                .filter("password",password);

        return query.list();

    }

    @Override
    public CollectionResponse<Contact> getByCursorQuery(String name,String password,int limit,String cursor) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("name", name)
                .filter("password",password);

        CollectionResponse<Contact>  response  =  ofyService.fetchCursorQuery(query,limit,cursor);

        return response;
    }

    @Override
    public List<Contact> getAllInOrder(String name) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("name", name);

        query = query.order("-dateAddedLongTime");

        return query.list();

    }

    @Override
    public Contact getByName(String name) {

        System.out.println(" in fetch contact impl " + name);

        Contact resp =  ofyService.ofy().load().type(Contact.class)
                .filter("name", name).first().now();

        System.out.println(resp);

        return resp;
    }

    @Override
    public List<Contact> getAllContacts() {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .order("dateAddedLongTime");

        return query.list();
    }
}
