package model.user;

import model.cafeteria.Cafeteria;


public class Barista extends User{

        Cafeteria cafe;

        public Barista(){

        }

        public Barista(String username, String password, String role) {
            super(username, password, role);
        }

        public Cafeteria getCafeteria(){
                return this.cafe;
        }

        public void setCafeteria(Cafeteria cafe){
            this.cafe = cafe;
        }






}
