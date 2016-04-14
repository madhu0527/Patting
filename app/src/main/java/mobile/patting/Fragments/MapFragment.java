package mobile.patting.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.mobile.patting.R;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mobile.patting.Adapters.PetsListAdapter;
import mobile.patting.Design.MaterialProgressBar;
import mobile.patting.Interface.HttpInterface;
import mobile.patting.Pojo.PetsPojo;
import mobile.patting.UI.MainActivity;
import mobile.patting.Utils.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MapFragment extends Fragment {
    View v;
    Context mContext;
    MapView mMapView;
    Constants utils = new Constants();
    @Bind(R.id.progressBar)
    MaterialProgressBar progressBar;
    List<PetsPojo> petsPojo;
    LatLng countryLatLong;
    MarkerOptions marker;
    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.map, container,
                false);
        ButterKnife.bind(this, v);
        mContext = getActivity();
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        callLocations();


        return v;
    }

    private void callLocations() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .build();
        HttpInterface api = adapter.create(HttpInterface.class);
        api.getPetsList(new Callback<List<PetsPojo>>() {
            @Override
            public void success(List<PetsPojo> petsPojos, Response response) {
                System.out.println(response);
                petsPojo = petsPojos;
                new LoadMap().execute();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("GET ERROR", String.valueOf(error));
                //   utils.showMsg("Oops! Something went wrong!", mainLayout);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        setHasOptionsMenu(true);
        ((MainActivity) mContext).setHomeAsEnabled(false);
        ((MainActivity) mContext).manageDrawerLayout(false);
        ((MainActivity) mContext).changeToolbarTitle("Pets Location");
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public class LoadMap extends AsyncTask<Void, Void, Void> {
        ArrayList<LatLng> latLongArr=new ArrayList<LatLng>();
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < petsPojo.size(); i++) {
                latLongArr.add(utils.convertAddress(petsPojo.get(i).getCity()+","+petsPojo.get(i).getLocation()));
                countryLatLong = utils.getCurrentCountry(mContext);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            for (int i = 0; i < latLongArr.size(); i++) {
                marker = new MarkerOptions().position(
                        new LatLng(latLongArr.get(i).latitude, latLongArr.get(i).longitude)).title(petsPojo.get(i).getPetNameTitle());
                marker.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                googleMap.addMarker(marker);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(countryLatLong.latitude, countryLatLong.longitude)).zoom(5).build();
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }

            progressBar.setVisibility(View.GONE);
        }
    }
}
