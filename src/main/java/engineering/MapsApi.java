package engineering;

import com.google.maps.*;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElementStatus;


import java.util.ArrayList;
import java.util.List;

public class MapsApi {

    private String apiKey = "AIzaSyDRAkOShcwhDWFFN_DgvLezUaA4QhADM9M";

    public List<Long> getDistances(String fixedAddress, List<String> addressList){

        List<Long> distances = new ArrayList<Long>();
        String[] addresses = new String[addressList.size()];

        try{

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
                    System.out.printf("error", addresses[i]);
                }
            }

            context.shutdown();

        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        return distances;
    }

}
