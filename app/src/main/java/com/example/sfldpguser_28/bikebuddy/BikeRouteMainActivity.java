package com.example.sfldpguser_28.bikebuddy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.sfldpguser_28.Apis.Api;
import com.example.sfldpguser_28.Utils.Constants;
import com.example.sfldpguser_28.model.BikeBuddyImplOkHttp;
import com.example.sfldpguser_28.model.Bikes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BikeRouteMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final LatLng NEW_YORK = new LatLng(40.6741806, -73.9564947);
    static final LatLng KIEL = new LatLng(40.67, -73.95);
    static final LatLng A = new LatLng(40.50, -73.70);
    static final LatLng B = new LatLng(40.56, -72.90);
    static final LatLng C = new LatLng(40.70, -73.87);

    private GoogleMap map;
    private static final String TAG = "BikeRouteMainActivity";
    private AutoCompleteTextView autoCompletePickUp, autoCompleteDropOff;
    private List<Bikes> listBikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.sfldpguser_28.bikebuddy.R.layout.activity_bike_route_main);
        Toolbar toolbar = (Toolbar) findViewById(com.example.sfldpguser_28.bikebuddy.R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getString(com.example.sfldpguser_28.bikebuddy.R.string.app_name));

        FloatingActionButton fab = (FloatingActionButton) findViewById(com.example.sfldpguser_28.bikebuddy.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        init();
    }

    private void init(){

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(com.example.sfldpguser_28.bikebuddy.R.id.map);
        mapFragment.getMapAsync(this);

        autoCompletePickUp = (AutoCompleteTextView) findViewById(com.example.sfldpguser_28.bikebuddy.R.id.autoCompTvPickUp);
        autoCompleteDropOff = (AutoCompleteTextView) findViewById(com.example.sfldpguser_28.bikebuddy.R.id.autoCompTvDropOff);
        autoCompletePickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ListBikes().execute();
                //call web service pickup locations
              /*String url = Constants.URL_GET_LOCATION_DROP_OFF + Constants.latitude +NEW_YORK.latitude+ Constants.Longitude +NEW_YORK.longitude;
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                Api api = retrofit.create(Api.class);

                Call<List<Bikes>> call = api.getBikes();
                call.enqueue(new Callback<List<Bikes>>() {
                    @Override
                    public void onResponse(Call<List<Bikes>> call, Response<List<Bikes>> response) {
                        listBikes = response.body();

                        for(Bikes bike : listBikes){
                            Log.d(TAG, "Bike: "+ toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Bikes>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

        autoCompleteDropOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call web service dropoff locations
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Add a marker in Sydney and move the camera
        Log.d(TAG, "onMapReady called");
        map.addMarker(new
                MarkerOptions().position(NEW_YORK).title("New York"));
        map.moveCamera(CameraUpdateFactory.newLatLng(NEW_YORK));

        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);


    }

    private void createPickUpMarker(LatLng latLng){
        map.addMarker(new
                MarkerOptions().position(latLng).title("New York"));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        map.addMarker(new
                MarkerOptions().position(KIEL).title("New York"));


        map.addMarker(new
                MarkerOptions().position(A).title("New York"));


        map.addMarker(new
                MarkerOptions().position(B).title("New York"));


        map.addMarker(new
                MarkerOptions().position(C).title("New York"));


    }

    class ListBikes extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            createPickUpMarker(NEW_YORK);
            // ArrayAdapter<Bikes> adapter = new ArrayAdapter<Bikes>(this,android.R.layout.simple_list_item_1,listBikes);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            BikeBuddyImplOkHttp example = new BikeBuddyImplOkHttp();
            try {
                listBikes = example.getAllBikes();
                for(Bikes bike : listBikes){
                    System.out.println(bike.nameOfStation());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }



}
