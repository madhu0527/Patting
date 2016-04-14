package mobile.patting.Utils;

import android.app.Application;
import android.location.Geocoder;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by goodworklabs on 30/03/2016.
 */
public class Aplication extends Application{
   public static Cloudinary cloudinary;
   public static Geocoder coder;
    @Override
    public void onCreate() {
        super.onCreate();
        coder = new Geocoder(this);
        cloudinarySetup();
    }

    private void cloudinarySetup() {
        Map config = new HashMap();
        config.put("cloud_name", "dvljocfp1");
        config.put("api_key", "765484764583211");
        config.put("api_secret", "d3EWJm-y_L0c5iLileIHC7UFTDI");
        cloudinary = new Cloudinary(config);
    }

}
