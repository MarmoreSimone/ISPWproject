package model.user;

public class User {

        String username;
        String password;
        String role;

        String cafeteria;
        public User(){

        }

        public User(String username, String password, String role) {
            this.username = username;
            this.password = password;
            this.role = role;
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

        public String getRole() {
            if(!this.role.equals("client")){
                return "barista";
            }

            return "client";

        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCafeteria() {
            return this.role;
        }

        public void setCafeteria(String cafeteria) {
            this.role = cafeteria;
        }



}
