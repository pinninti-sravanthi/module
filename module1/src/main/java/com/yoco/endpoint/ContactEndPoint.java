package com.yoco.endpoint;

import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ContactEndPoint {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String hello() {

        System.out.println(" came here ");
        return contactService.test();
    }

    @GetMapping("/get")
    public Contact get(@RequestParam long id) {

        return contactService.getContact(id);
    }

    @PostMapping("/save")
    public Contact save(@RequestParam String name, @RequestParam String password, @RequestParam String[] address){

        return contactService.saveContact(name,password,address);
    }

    @GetMapping("/getByFilter")
    public Contact getByFilter(@RequestParam String name, @RequestParam String password) {

        System.out.println(" in end point");
        return contactService.getContactByFilter(name,password);
    }

    @GetMapping("/getAll")
    public List<Contact> getAll(@RequestParam String name, @RequestParam String password) {

        System.out.println(" in end point - all ");

        return contactService.getAll(name,password);
    }

    @GetMapping("/getByCursor")
    public Map<String,Object> getAllByCursor(@RequestParam String name, @RequestParam String password,
                                             @RequestParam int limit, @Nullable @RequestParam String cursor) {

        Map<String,Object> response = new HashMap<>();

        System.out.println(" in end point - cursor ");

        CollectionResponse<Contact> collectionResponse = contactService.getAllByCursor(name,password,limit,cursor);

        response.put("contact", collectionResponse.getItems());
        response.put("cursor", collectionResponse.getCursor());

        return response;
    }

    @GetMapping("/getAllSorted")
    public List<Contact> getAllSorted(@RequestParam String name) {

        System.out.println(" in end point - all sorted ");

        return contactService.getAllInOrder(name);
    }

    @GetMapping("/getByName")
    public Contact getByName(@RequestParam String name) {

        System.out.println(" in end point - getByName ");

        return contactService.getByName(name);
    }

    @GetMapping("/getAllContacts")
    public List<Contact> getAllContacts() {

        System.out.println(" in end point - all  ");

        return contactService.getAllContacts();
    }

}
