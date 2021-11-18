package com.yoco.service;

import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.services.dao.ContactDao;
import com.yoco.services.impl.ContactImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    ContactDao contactDao = new ContactImpl();

    public String test(){

        return "from module 1";
    }

    public Contact saveContact(String name, String password,String[] address){

        System.out.println(" saving contact to db name " + name + " password " + password);

        Contact contact = new Contact();
        contact.setName(name);
        contact.setPassword(password);
        contact.setAddresses(List.of(address));

        String jsonString = new com.google.gson.Gson().toJson(contact);

        System.out.println(" in save contact " + jsonString);

        return contactDao.save(contact);
    }

    public Contact getContact(long key) {

        System.out.println(" fetching contact from db " + key);

        return contactDao.get(key);
    }

    public Contact getContactByFilter(String name, String password) {

        System.out.println(" fetching contact from db via filter " + name + " password " + password);

        return contactDao.getByFilter(name,password);
    }

    public List<Contact> getAll(String name,String password){

        System.out.println(" fetching contact from db all " + name + " password " + password);

        return contactDao.getAll(name,password);
    }

    public CollectionResponse<Contact> getAllByCursor(String name, String password, int limit, String cursor){

        System.out.println(" fetching contact from db cursor  " + name + " password " + password + " limit " + limit + " cursor : " + cursor);

        return contactDao.getByCursorQuery(name,password,limit,cursor);
    }

    public List<Contact> getAllInOrder(String name){

        System.out.println(" fetching contact from db all-sorted " + name );

        return contactDao.getAllInOrder(name);
    }

    public Contact getByName(String name){

        System.out.println(" fetching contact from getByName " + name );

        return contactDao.getByName(name);
    }

    public List<Contact> getAllContacts(){

        System.out.println(" fetching contact from db all : " );

        return contactDao.getAllContacts();
    }

}
