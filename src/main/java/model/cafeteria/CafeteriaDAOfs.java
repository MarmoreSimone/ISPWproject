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

    private final int getIndex_name = 0;
    private final int getIndex_city = 1;
    private final int getIndex_address = 2;
    private final int getIndex_number = 3;
    private final int getIndex_description = 4;
    private final int getIndex_photo = 5;

    public CafeteriaDAOfs() {
        this.fd = new File(CSV_FILE_NAME);
    }

    public void saveCafeteria(Cafeteria cafe) throws SystemErrorException{

        try {
            CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

            String[] record = new String[6];

            record[getIndex_name] = cafe.getName();
            record[getIndex_city] = cafe.getCity();
            record[getIndex_address] = cafe.getAddress();
            record[getIndex_number] = cafe.getNumber();
            record[getIndex_description] = cafe.getDescription();
            record[getIndex_photo] = cafe.getPhoto();

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
                String name = record[getIndex_name];
                String city = record[getIndex_city];
                String address = record[getIndex_address];
                String number = record[getIndex_number];
                String description = record[getIndex_description];
                String photo = record[getIndex_photo];

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

                String name = record[getIndex_name];
                String city = record[getIndex_city];
                String address = record[getIndex_address];
                String number = record[getIndex_number];
                String description = record[getIndex_description];
                String photo = record[getIndex_photo];

                Cafeteria cafe = new Cafeteria(name, city, address, number, description, photo);
                cafes.add(cafe);

            }

        }catch(IOException | CsvValidationException e){
            throw new SystemErrorException(e.getMessage());
        }

        return cafes;
    }
}

