package com.yoco.services.dao;

import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;

import java.util.List;

public interface ContactDao {

     Contact save(Contact contact);

     Contact get(long id);

     Contact getByFilter(String name,String password);

     List<Contact> getAll(String name,String password);

     CollectionResponse<Contact> getByCursorQuery(String name,String password, int limit, String cursor);

     List<Contact> getAllInOrder(String name);

     Contact getByName(String name);

     List<Contact> getAllContacts();

}
