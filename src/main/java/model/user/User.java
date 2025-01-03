package model.user;

public class User {

        String username;
        String password;
        String role;

        //TODO vedi se fare una extends/usare role come campo per mantenere la caffetteria, al get role controlla se != da client ed in caso torna barista, in getCafeteria torna direttamente role
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
