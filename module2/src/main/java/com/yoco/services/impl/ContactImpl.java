package com.yoco.services.impl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.services.OfyService;
import com.yoco.services.dao.ContactDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ContactImpl implements ContactDao {

    @Autowired
    OfyService ofyService;

    @Override
    public Contact save(Contact contact) {

        String jsonString = new com.google.gson.Gson().toJson(contact);

        log.info(" in save contact impl " + jsonString);

        Key<Contact> resp = ofyService.save(contact);

        log.info(" resp : " + resp);

        return contact;
    }

    @Override
    public Contact get(String id) {

        log.info(" in fetch contact impl " + id);

        Contact resp =  ofyService.get(Contact.class,id);

        log.info(" resp :: " + resp);

        return resp;
    }

//    @Override
//    public Contact get(Long id) {
//
//        log.info(" in fetch contact impl long " + id);
//
//        Contact resp =  ofyService.get(Contact.class,id);
//
//        log.info(" resp :: " + resp);
//
//        return resp;
//    }

    @Override
    public Contact getByFilter(String email, String password) {

        Contact obj  =   ofyService.ofy().load().type(Contact.class)
                .filter("email", email)
                .filter("password",password)
                .limit(1).first().now();

        return obj;
    }

    @Override
    public List<Contact> getAll(String email, String password) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("email", email)
                .filter("password",password);

        return query.list();

    }

    @Override
    public CollectionResponse<Contact> getByCursorQuery(String email,String password,int limit,String cursor) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("email", email)
                .filter("password",password);

        CollectionResponse<Contact>  response  =  ofyService.fetchCursorQuery(query,limit,cursor);

        return response;
    }

    @Override
    public List<Contact> getAllInOrder(String email) {

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("email", email);

        query = query.order("-dateAddedLongTime");

        return query.list();

    }

    @Override
    public Contact getByEmail(String email) {

        log.info(" in fetch contact impl " + email);

        Contact resp =  ofyService.ofy().load().type(Contact.class)
                .filter("email", email).first().now();

        log.info(" resp ::: "  + resp);

        return resp;
    }

    @Override
    public List<Contact> getAllContacts() {

        log.info(" in get all contacts " );

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .order("dateAddedLongTime");

        return query.list();
    }

    @Override
    public List<Contact> getFilteredContactAddresses(String email, ArrayList<String> address) {

        log.info(" address " + address);

        Query<Contact> query = ofyService.ofy().load().type(Contact.class)
                .filter("email", email);


        for(String add : address)
        {
            log.info(" add " + add);
            query = query.filter("addresses",add);
        }

        log.info(" query " + query.toString() );

        return query.list();
    }
}
