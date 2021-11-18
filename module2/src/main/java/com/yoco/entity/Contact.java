package com.yoco.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Contact {

    @Id
    Long id;

    @Index
    String name;

    @Index
    String password;

    @Index
    Long dateAddedLongTime = new Date().getTime();

    @Index
    private List<String> addresses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDateAddedLongTime() {
        return dateAddedLongTime;
    }

    public void setDateAddedLongTime(Long dateAddedLongTime) {
        this.dateAddedLongTime = dateAddedLongTime;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dateAddedLongTime=" + dateAddedLongTime +
                ", addresses=" + addresses +
                '}';
    }
}
