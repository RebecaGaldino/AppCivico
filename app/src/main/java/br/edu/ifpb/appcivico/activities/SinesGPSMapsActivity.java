package br.edu.ifpb.appcivico.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.edu.ifpb.appcivico.R;
import br.edu.ifpb.appcivico.entities.Sine;
import br.edu.ifpb.appcivico.network.APIServices;
import br.edu.ifpb.appcivico.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SinesGPSMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private List<Sine> sines;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        latitude = extras.getDouble("latitude");
        longitude = extras.getDouble("longitude");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);

        LatLng myPlace = new LatLng(latitude,  longitude);
        MarkerOptions centralPoint = new MarkerOptions()
                .position(myPlace)
                .title("Localização atual");

        mMap.addMarker(centralPoint);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 8));

        new Thread(new Runnable() {
            @Override
            public void run() {
                //Referencing the service's interface
                APIServices APISine = ServerConnection.getInstance().getService();
                //Calling the request method with two parameters
                Call<List<Sine>> call = APISine.getSinesGPS(latitude,longitude,"100");
                //Executing de request GET
                call.enqueue(new Callback<List<Sine>>() {
                    @Override
                    public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {
                        sines = response.body();
                        for (Sine sine : sines) {
                            double lati = Double.parseDouble(sine.getLat());
                            double longLat = Double.parseDouble(sine.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(
                                    new LatLng(lati,longLat))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker))
                                    .title(sine.getNome()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Sine>> call, Throwable t) {
                        Log.i("onFailure", t.getMessage().toString());
                    }
                });
            }
        }).start();

    }
}
