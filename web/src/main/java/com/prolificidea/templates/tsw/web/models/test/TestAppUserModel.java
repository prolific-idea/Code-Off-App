package com.prolificidea.templates.tsw.web.models.test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestAppUserModel {

    @NotNull
    private String username;

    @Size(min = 5)
    private String password;
    private String firstName;
    private String lastName;

    public TestAppUserModel() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
