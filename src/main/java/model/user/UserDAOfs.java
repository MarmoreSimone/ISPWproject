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

    private static final int getIndexUsername = 0;
    private static final int getIndexPassword = 1;
    private static final int getIndexRole = 2;
    private static final int getIndexCafeteria = 3;

    public UserDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    private void saveUser(User user, String cafeteria) throws SystemErrorException {

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] record = new String[4];

            record[getIndexUsername] = user.getUsername();
            record[getIndexPassword] = user.getPassword();
            record[getIndexRole] = user.getRole();
            if(cafeteria == null){
                cafeteria = "";
            }
            record[getIndexCafeteria] = cafeteria;

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
                String username = record[getIndexUsername];
                String password = record[getIndexPassword];
                String role = record[getIndexRole];

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
                String username = record[getIndexUsername];
                String password = record[getIndexPassword];
                String role = record[getIndexRole];
                String cafeteriaName = record[getIndexCafeteria];

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
                if(record[getIndexUsername].equals(barista.getUsername())){
                    record[getIndexCafeteria] = cafeteria.getName();
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
