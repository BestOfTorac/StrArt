package com.strart.model.domain;

public class Credentials {

    static String username;
    static String password;
    static Role role;


    public static String getUsername() {
            return username;
        }
    public static String getPassword() {
            return password;
        }

    public static Role getRole() {
            return role;
        }

    public static void setRole(Role role){
        Credentials.role=role;
    }

    public static void setUsername(String use){
        username=use;
    }
    public static void setPassword(String pas){
        password=pas;
    }


}
