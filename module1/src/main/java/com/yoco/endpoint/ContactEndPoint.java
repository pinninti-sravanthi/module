package com.yoco.endpoint;

import com.yoco.entity.CollectionResponse;
import com.yoco.entity.Contact;
import com.yoco.service.ContactService;
import com.yoco.taskHandler.TaskCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ContactEndPoint{

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String hello() {

        log.info( " came here ");
        return contactService.test();
    }

    @GetMapping("/get/{id}")
    public Contact get(@PathVariable String id) {

        log.info(" get contact with id " + id);

        return contactService.getContact(id);
    }

//    @GetMapping("/get/long/{id}")
//    public Contact getWithLong(@PathVariable Long id) {
//
//        log.info(" get contact with id " + id);
//
//        return contactService.getContact(id);
//    }

    @PostMapping("/save")
    public Contact save(@RequestBody Contact contact){

        log.info(" save contact with request body  ");

        return contactService.saveContact(contact);
    }


    @GetMapping("/getByFilter/{email}/{password}")
    public Contact getByFilter(@PathVariable String email, @PathVariable String password) {

        log.info(" getByFilter ");
        return contactService.getContactByFilter(email,password);
    }

    @GetMapping("/getAll/{email}/{password}")
    public List<Contact> getAll(@PathVariable String email, @PathVariable String password) {

        log.info(" getAll ");

        return contactService.getAll(email,password);
    }

    @GetMapping("/getByCursor/{email}/{password}")
    public Map<String,Object> getAllByCursor(@PathVariable String email, @PathVariable String password,
                                             @RequestParam int limit, @RequestParam String cursor) {

        Map<String,Object> response = new HashMap<>();

        log.info(" in end point - cursor ");

        CollectionResponse<Contact> collectionResponse = contactService.getAllByCursor(email,password,limit,cursor);

        response.put("contact", collectionResponse.getItems());
        response.put("cursor", collectionResponse.getCursor());

        return response;
    }

    @GetMapping("/getAllSorted/{email}")
    public List<Contact> getAllSorted(@PathVariable String email) {

        log.info(" in end point - all sorted ");

        return contactService.getAllInOrder(email);
    }

    @GetMapping("/getByName/{email}")
    public Contact getByEmail(@PathVariable String email) {

        log.info(" in end point - getByName " + email);

        return contactService.getByEmail(email);
    }

    @GetMapping("/getAllContacts")
    public List<Contact> getAllContacts() {

        log.info(" in end point - all  ");

        return contactService.getAllContacts();
    }

    @GetMapping("/getAddress/{email}")
    public List<Contact> getAddress(@PathVariable String email,@RequestParam String address ) {

        log.info(" in end point - address  ");

        return contactService.getAddress(email,address);
    }

    @GetMapping("/createTask")
    public String createTaskService(){
        System.out.println(" came inside end point");
        TaskCreator.createTask();
        return "true";
    }

    @GetMapping("/tasks/create")
    public void test(HttpServletResponse resp){

        log.info(" in task handler ");

        ContactService contactService = new ContactService();
        Contact contact = new Contact();
        contact.setEmail("test1@gmail.com");
        contact.setPassword("1111");
        contactService.saveContact(contact);

        resp.setStatus(HttpServletResponse.SC_OK);

    }


}
