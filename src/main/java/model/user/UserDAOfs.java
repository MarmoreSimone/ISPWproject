package model.user;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOfs extends UserDAO{

    private static final String CSV_FILE_NAME = "src/main/resources/csv/user.csv";

    private File fd;

    private static final int GET_INDEX_USERNAME = 0;
    private static final int GET_INDEX_PASSWORD = 1;
    private static final int GET_INDEX_ROLE = 2;
    private static final int GET_INDEX_CAFETERIA = 3;

    public UserDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    private void saveUser(User user, String cafeteria) throws SystemErrorException {

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] myRecord = new String[4];

            myRecord[GET_INDEX_USERNAME] = user.getUsername();
            myRecord[GET_INDEX_PASSWORD] = user.getPassword();
            myRecord[GET_INDEX_ROLE] = user.getRole();
            if(cafeteria == null){
                cafeteria = "";
            }
            myRecord[GET_INDEX_CAFETERIA] = cafeteria;

            csvWriter.writeNext(myRecord);
            csvWriter.flush();
            csvWriter.close();

        }catch(Exception e){
            throw new SystemErrorException(e.getMessage());
        }

    }

    public void saveBarista(Barista bar) throws SystemErrorException{
        if(bar.getCafeteria() == null) saveUser(bar,null);
        else saveUser(bar,bar.getCafeteria().getName());
    }

    public void saveClient(Client user) throws SystemErrorException{
        saveUser(user,null);
    }


    public List<User> getAllUserCredentials() throws SystemErrorException{
        List<User> users = new ArrayList<>();

        users.addAll(getAllBaristas());
        users.addAll(getAllClients());

        return users;
    }

    private List<Client> getAllClients() throws SystemErrorException{

        List<Client> clients = new ArrayList<Client>();

        try{

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] myRecord;

            while ((myRecord = csvReader.readNext()) != null) {
                String username = myRecord[GET_INDEX_USERNAME];
                String password = myRecord[GET_INDEX_PASSWORD];
                String role = myRecord[GET_INDEX_ROLE];

                Client user = DAOfactory.getDAOfactory().createUserDAO().createNewUserClient(username, password, role);

                if(user.getRole().equals("client")) clients.add(user);

            }

            csvReader.close();
        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return clients;
    }


    private List<Barista> getAllBaristas() throws SystemErrorException{

        List<Barista> baristas = new ArrayList<Barista>();

        try{
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] myRecord;
            while ((myRecord = csvReader.readNext()) != null) {
                String username = myRecord[GET_INDEX_USERNAME];
                String password = myRecord[GET_INDEX_PASSWORD];
                String role = myRecord[GET_INDEX_ROLE];
                String cafeteriaName = myRecord[GET_INDEX_CAFETERIA];

                Barista user = DAOfactory.getDAOfactory().createUserDAO().createNewUserBarista(username, password, role);
                if(user.getRole().equals("barista")){
                    user.setCafeteria(DAOfactory.getDAOfactory().createCafeteriaDAO().getCafeteriaByName(cafeteriaName));
                    baristas.add(user);
                }

            }

            csvReader.close();

        }catch(IOException | CsvValidationException | NoCafeteriasFoundException e){
            throw new SystemErrorException(e.getMessage());
        }

        return baristas;
    }

    public void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria) throws SystemErrorException{

        List<String[]> records = new ArrayList<>();

        try {

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            records = csvReader.readAll();

            for(String[] rec : records){
                if(rec[GET_INDEX_USERNAME].equals(barista.getUsername())){
                    rec[GET_INDEX_CAFETERIA] = cafeteria.getName();
                }
            }

            CSVWriter writer = new CSVWriter(new FileWriter(fd, false));
            writer.writeAll(records);
            writer.close();

        }catch(IOException | CsvException e){
            throw new SystemErrorException(e.getMessage());
        }

    }

}
