package model.user;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import exception.SystemErrorException;
import model.DAOfactory;
import model.cafeteria.Cafeteria;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOfs extends UserDAO{

    private static final String CSV_FILE_NAME = "src/main/resources/csv/user.csv";

    private File fd;

    private final int getIndex_username = 0;
    private final int getIndex_password = 1;
    private final int getIndex_role = 2;
    private final int getIndex_cafeteria = 3;

    public UserDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    private void saveUser(User user, String cafeteria) throws SystemErrorException {

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] record = new String[4];

            record[getIndex_username] = user.getUsername();
            record[getIndex_password] = user.getPassword();
            record[getIndex_role] = user.getRole();
            if(cafeteria == null){
                cafeteria = "";
            }
            record[getIndex_cafeteria] = cafeteria;

            csvWriter.writeNext(record);
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
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                String username = record[getIndex_username];
                String password = record[getIndex_password];
                String role = record[getIndex_role];

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
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                String username = record[getIndex_username];
                String password = record[getIndex_password];
                String role = record[getIndex_role];
                String cafeteriaName = record[getIndex_cafeteria];

                Barista user = DAOfactory.getDAOfactory().createUserDAO().createNewUserBarista(username, password, role);
                if(user.getRole().equals("barista")) baristas.add(user);

            }

            csvReader.close();

        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return baristas;
    }

    public void changeBaristaCafeteria(Barista barista, Cafeteria cafeteria) throws SystemErrorException{

        List<String[]> records = new ArrayList<>();

        try {

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            records = csvReader.readAll();

            for(String[] record : records){
                if(record[getIndex_username].equals(barista.getUsername())){
                    record[getIndex_cafeteria] = cafeteria.getName();
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
