package com.strart.model.bean;

public class CredentialsBean {

        private final String username;
        private final String password;


        public CredentialsBean(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }


}
