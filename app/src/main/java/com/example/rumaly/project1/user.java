package com.example.rumaly.project1;

/**
 * Created by hosneara on 10/19/16.
 */

public class user {
    private static String name, email, phone,post;
    private static user instance = new user();
    private user() {}
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

    public String getEmail()
    {return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPost() {
        return this.post;
    }

    public static user getInstance()
    {
        return instance;
    }

}
