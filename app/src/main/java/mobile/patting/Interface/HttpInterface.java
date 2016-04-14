package mobile.patting.Interface;

import java.util.HashMap;
import java.util.List;

import mobile.patting.Pojo.PetsPojo;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by goodworklabs on 30/03/2016.
 */
public interface HttpInterface {
    //posting ad
    @POST("/pets.json")
    void postAd(@Body HashMap<String,HashMap<String,String>> map, retrofit.Callback<PetsPojo> response);
    //get pets list
    @GET("/pets.json")
     void getPetsList(Callback<List<PetsPojo>> response);
}
