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

public class LoginController {

        public UserBean login(CredentialsBean cred) throws NoUserFoundException, SystemErrorException {

            User user = DAOfactory.getDAOfactory().createUserDAO().getUserByName(cred.getUsername());
            if (user == null) throw new NoUserFoundException();

            if(user.getRole().equals("barista")) SessionManager.getInstance().setUserBarista((Barista) user);
            else SessionManager.getInstance().setUserClient((Client) user);
            return new UserBean(null,null,user.getRole());
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
