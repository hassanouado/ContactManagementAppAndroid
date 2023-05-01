package com.example.contact.entities;

import java.util.List;

public class Contact {
        private long id;
        String firstName;
        String lastName;
        String email;
        String phone;
    String ville;
    String job;
    public Contact(){
        id=0;
         firstName="";
         lastName="";
         email="";
         phone="";
         ville="";
         job="";
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Contact(long id, String firstName, String lastName, String email, String phone, String job, String ville) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.job=job;
        this.ville=ville;

    }

    public Contact(Contact contact) {
        this.id = contact.getId();
        this.firstName =  contact.getFirstName();
        this.lastName =  contact.getLastName();
        this.email =  contact.getEmail();
        this.phone =  contact.getPhone();
        this.ville=contact.getVille();
        this.job=contact.getJob();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}