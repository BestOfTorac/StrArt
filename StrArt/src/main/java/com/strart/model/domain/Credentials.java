package com.strart.model.domain;

public class Credentials {

    String username;
    String password;
    Role role;

    public Credentials(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
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
        this.role=role;
    }
}
