package controller;

import bean.CredentialsBean;
import bean.UserBean;
import model.DAOfactory;
import model.user.User;
import utils.UserLogged;

import java.util.List;

public class UserAccess {

        public UserAccess(){

        }

        public UserBean login(CredentialsBean cred) {
            List<User> users = DAOfactory.getDAOfactory().createUserDAO().getAllUser();

            for (User user : users) {
                if (user.getUsername().equals(cred.getUsername()) && user.getPassword().equals(cred.getPassword())) {
                    UserLogged.getInstance().setUser(user);
                    return new UserBean(null,null,user.getRole());
                }
            }
            return null;
        }


        public void register(UserBean user) {
            DAOfactory.getDAOfactory().createUserDAO().saveUser(new User(user.getUsername(), user.getPassword(), user.getRole()));
        }


}
