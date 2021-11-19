package com.yoco.service;

import com.google.common.collect.Lists;
import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.services.dao.ContactDao;
import com.yoco.services.impl.ContactImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ContactService {

    ContactDao contactDao = new ContactImpl();

    public String test(){

        return "from module 1";
    }

    public Contact saveContact(Contact contact){

        log.info(" save contact " + new com.google.gson.Gson().toJson(contact));

        contact.setId(UUID.randomUUID().toString());
        return contactDao.save(contact);
    }

    public Contact getContact(String key) {

        log.info(" fetching contact from db " + key);

        return contactDao.get(key);
    }

//    public Contact getContact(Long key) {
//
//        log.info(" fetching contact from db " + key);
//
//        return contactDao.get(key);
//    }

    public Contact getContactByFilter(String email, String password) {

        log.info(" fetching contact from db via filter " + email + " password " + password);

        return contactDao.getByFilter(email,password);
    }

    public List<Contact> getAll(String email,String password){

        log.info(" fetching contact from db all " + email + " password " + password);

        return contactDao.getAll(email,password);
    }

    public CollectionResponse<Contact> getAllByCursor(String email, String password, int limit, String cursor){

        log.info(" fetching contact from db cursor  " + email + " password " + password + " limit " + limit + " cursor : " + cursor);

        return contactDao.getByCursorQuery(email,password,limit,cursor);
    }

    public List<Contact> getAllInOrder(String email){

        log.info(" fetching contact from db all-sorted " + email );

        return contactDao.getAllInOrder(email);
    }

    public Contact getByEmail(String email){

        log.info(" fetching contact from getByName " + email );

        return contactDao.getByEmail(email);
    }

    public List<Contact> getAllContacts(){

        log.info(" fetching contact from db all : " );

        return contactDao.getAllContacts();
    }

    public List<Contact> getAddress(String email, String address){

        log.info(" fetching addresss : " );

        ArrayList<String> addresses = new ArrayList<>(Arrays.asList(address.split(",")));

        return contactDao.getFilteredContactAddresses(email,addresses);
    }

}
