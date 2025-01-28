package engineering;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;
import exception.SystemErrorException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MapsApi {

    public List<Long> getDistances(String fixedAddress, List<String> addressList) throws SystemErrorException {

        List<Long> distances = new ArrayList<Long>();
        String[] addresses = new String[addressList.size()];

        try{

            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/java/utils/config.properties");
            properties.load(fis);
            String apiKey = properties.getProperty("api.key");

            for (int i = 0; i < addressList.size(); i++) {
                addresses[i] = addressList.get(i);
            }

            // Inizializza il contesto per le API di Google Maps
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

            // Richiesta alla Distance Matrix API
            DistanceMatrix result = DistanceMatrixApi.newRequest(context)
                    .origins(fixedAddress)      // Punto di partenza
                    .destinations(addresses)    // Destinazioni
                    .await();


            for (int i = 0; i < addresses.length; i++) {

                if (result.rows[0].elements[i].status == DistanceMatrixElementStatus.OK) {

                    long distanceMeters = result.rows[0].elements[i].distance.inMeters;
                    distances.add(distanceMeters);

                } else {
                    System.out.println("api error");
                }
            }

            context.shutdown();

        } catch(IOException | ApiException e){
            throw new SystemErrorException(e.getMessage());
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        return distances;
    }

}
