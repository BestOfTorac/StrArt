package com.strart.model.domain;

public class Credentials {

    static String username;
    static String password;
    static Role role;

    public Credentials(String username, String password, Role role) {
        Credentials.username = username;
        Credentials.password = password;
        Credentials.role = role;
    }

    public Credentials(String username, String password) {
        Credentials.username = username;
        Credentials.password = password;
    }

    public String getUsername() {
            return username;
        }
    public String getPassword() {
            return password;
        }

    public Role getRole() {
            return role;
        }

    public void setRole(Role role){
        Credentials.role=role;
    }
}
