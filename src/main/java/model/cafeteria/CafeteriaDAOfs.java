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

    private static final int getIndexName = 0;
    private static final int getIndexCity = 1;
    private static final int getIndexAddress = 2;
    private static final int getIndexNumber = 3;
    private static final int getIndexDescription = 4;
    private static final int getIndexPhoto = 5;

    public CafeteriaDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    public void saveCafeteria(Cafeteria cafe) throws SystemErrorException{

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] record = new String[6];

            record[getIndexName] = cafe.getName();
            record[getIndexCity] = cafe.getCity();
            record[getIndexAddress] = cafe.getAddress();
            record[getIndexNumber] = cafe.getNumber();
            record[getIndexDescription] = cafe.getDescription();
            record[getIndexPhoto] = cafe.getPhoto();

            csvWriter.writeNext(record);
            csvWriter.flush();
            csvWriter.close();

        }catch(Exception e){
            throw new SystemErrorException(e.getMessage());
        }
    }

    public Cafeteria getCafeteriaByName(String cafeName) throws NoCafeteriasFoundException,SystemErrorException{
        List<Cafeteria> cafes = new ArrayList<Cafeteria>();

        try{

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] record;

            while ((record = csvReader.readNext()) != null) {
                String name = record[getIndexName];
                String city = record[getIndexCity];
                String address = record[getIndexAddress];
                String number = record[getIndexNumber];
                String description = record[getIndexDescription];
                String photo = record[getIndexPhoto];

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

    public List<Cafeteria> getAllCafeterias() throws NoCafeteriasFoundException, SystemErrorException{
        List<Cafeteria> cafes = new ArrayList<Cafeteria>();

        try{

            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            String[] record;

            while ((record = csvReader.readNext()) != null) {

                String name = record[getIndexName];
                String city = record[getIndexCity];
                String address = record[getIndexAddress];
                String number = record[getIndexNumber];
                String description = record[getIndexDescription];
                String photo = record[getIndexPhoto];

                Cafeteria cafe = new Cafeteria(name, city, address, number, description, photo);
                cafes.add(cafe);

            }

        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return cafes;
    }
}

