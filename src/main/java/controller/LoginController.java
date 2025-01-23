package controller;

import bean.CredentialsBean;
import bean.UserBean;
import engineering.SessionManager;
import exception.NoUserFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.user.Barista;
import model.user.Client;
import model.user.User;

import java.util.List;

public class LoginController {

        public UserBean login(CredentialsBean cred) throws NoUserFoundException, SystemErrorException {
            List<User> users = DAOfactory.getDAOfactory().createUserDAO().getAllUserCredentials();

            for (User user : users) {
                if (user.getUsername().equals(cred.getUsername()) && user.getPassword().equals(cred.getPassword())) {

                    if(user.getRole().equals("barista")) SessionManager.getInstance().setUserBarista((Barista) user);
                    else SessionManager.getInstance().setUserClient((Client) user);
                    return new UserBean(null,null,user.getRole());

                }
            }
            throw new NoUserFoundException();
        }


        public void register(UserBean user) throws SystemErrorException{
            if(user.getRole().equals("barista")){

                Barista barista = DAOfactory.getDAOfactory().createUserDAO().createNewUserBarista(user.getUsername(), user.getPassword(), user.getRole());
                DAOfactory.getDAOfactory().createUserDAO().saveBarista(barista);

            }
            else {

                Client client = DAOfactory.getDAOfactory().createUserDAO().createNewUserClient(user.getUsername(), user.getPassword(), user.getRole());
                DAOfactory.getDAOfactory().createUserDAO().saveClient(client);

            }
        }

}
