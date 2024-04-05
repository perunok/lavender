package com.act.lavender.request;

public class ReplaceBookRequest extends BookRequest {
    private String oldTitle;

    public ReplaceBookRequest(String oldTitle, String title, String description, String name, String email) {
        super(title, description, name, email);
        this.oldTitle = oldTitle;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle;
    }

}
