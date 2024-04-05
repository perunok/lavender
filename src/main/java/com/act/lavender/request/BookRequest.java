package com.act.lavender.request;

// this model doesn't reside in the actual database. it is only used as a way of enforcing user input in controller classe.

public class BookRequest {

    private String title;
    private String description;
    private String name;
    private String email;
    public BookRequest(String title, String description, String name, String email) {
        this.title = title;
        this.description = description;
        this.name = name;
        this.email = email;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}
