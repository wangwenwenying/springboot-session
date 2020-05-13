package com.www.springbootsession;

public class User {
    private String name,password;
    public String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;}
    public String getPassword() {return password;
    }
    public void setPassword(String password) {
        this.password = password;
        }
    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + "]";    
        }
}
