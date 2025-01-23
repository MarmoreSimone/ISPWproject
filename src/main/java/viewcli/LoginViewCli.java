package viewcli;

import bean.CredentialsBean;

public class LoginViewCli extends UtilsCli{

    public CredentialsBean drawGetCredentials(){

        String username;
        String password;

        System.out.println("username: ");
        username = getString();

        System.out.println("password: ");
        password = getString();

        return new CredentialsBean(username,password);
    }




}
