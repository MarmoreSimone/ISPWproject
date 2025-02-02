package model.cafeteria;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import exception.NoCafeteriasFoundException;
import exception.SystemErrorException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CafeteriaDAOfs extends CafeteriaDAO {

    private static final String CSV_FILE_NAME = "src/main/resources/csv/cafeteria.csv";

    private File fd;

    private static final int GET_INDEX_NAME = 0;
    private static final int GET_INDEX_CITY = 1;
    private static final int GET_INDEX_ADDRESS = 2;
    private static final int GET_INDEX_NUMBER = 3;
    private static final int GET_INDEX_DESCRIPTION = 4;
    private static final int GET_INDEX_PHOTO = 5;

    public CafeteriaDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    public void saveCafeteria(Cafeteria cafe) throws SystemErrorException{

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] myRecord = new String[6];

            myRecord[GET_INDEX_NAME] = cafe.getName();
            myRecord[GET_INDEX_CITY] = cafe.getCity();
            myRecord[GET_INDEX_ADDRESS] = cafe.getAddress();
            myRecord[GET_INDEX_NUMBER] = cafe.getNumber();
            myRecord[GET_INDEX_DESCRIPTION] = cafe.getDescription();
            myRecord[GET_INDEX_PHOTO] = cafe.getPhoto();

            csvWriter.writeNext(myRecord);
            csvWriter.flush();
            csvWriter.close();

        }catch(Exception e){
            throw new SystemErrorException(e.getMessage());
        }
    }

    public Cafeteria getCafeteriaByName(String cafeName) throws NoCafeteriasFoundException,SystemErrorException{

        try{

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] myRecord;

            while ((myRecord = csvReader.readNext()) != null) {
                String name = myRecord[GET_INDEX_NAME];
                String city = myRecord[GET_INDEX_CITY];
                String address = myRecord[GET_INDEX_ADDRESS];
                String number = myRecord[GET_INDEX_NUMBER];
                String description = myRecord[GET_INDEX_DESCRIPTION];
                String photo = myRecord[GET_INDEX_PHOTO];

                Cafeteria cafe = new Cafeteria(name, city, address, number, description, photo);
                if(cafe.getName().equals(cafeName)){
                    return cafe;
                }
            }

        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return null;

    }

    public List<Cafeteria> getAllCafeterias() throws SystemErrorException{
        List<Cafeteria> cafes = new ArrayList<Cafeteria>();

        try{

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] myRecord;

            while ((myRecord = csvReader.readNext()) != null) {

                String name = myRecord[GET_INDEX_NAME];
                String city = myRecord[GET_INDEX_CITY];
                String address = myRecord[GET_INDEX_ADDRESS];
                String number = myRecord[GET_INDEX_NUMBER];
                String description = myRecord[GET_INDEX_DESCRIPTION];
                String photo = myRecord[GET_INDEX_PHOTO];

                Cafeteria cafe = new Cafeteria(name, city, address, number, description, photo);
                cafes.add(cafe);

            }

        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return cafes;
    }
}

