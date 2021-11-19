package com.yoco.services.dao;

import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public interface ContactDao {

     Contact save(Contact contact);

     Contact get(String id);

//     Contact get(Long id);

     Contact getByFilter(String email,String password);

     List<Contact> getAll(String email,String password);

     CollectionResponse<Contact> getByCursorQuery(String email,String password, int limit, String cursor);

     List<Contact> getAllInOrder(String email);

     Contact getByEmail(String email);

     List<Contact> getAllContacts();

     List<Contact> getFilteredContactAddresses(String email, ArrayList<String> address);

}
