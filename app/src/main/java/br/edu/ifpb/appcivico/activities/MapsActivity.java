package br.edu.ifpb.appcivico.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.edu.ifpb.appcivico.R;
import br.edu.ifpb.appcivico.entities.Sine;
import br.edu.ifpb.appcivico.network.ServerConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
      /* mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        mMap = googleMap;

        Call<List<Sine>> call = ServerConnection.getInstance().getService().getSinesComRaio();

        Log.i(this.getClass().getName(), "Calling Sines");

        call.enqueue(new Callback<List<Sine>>() {
            @Override
            public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {

                try {
                    if (response.isSuccessful()) {
                        List<Sine> sines = response.body();

                        for (int i = 1; i <= sines.size(); i++) {
                            double lati=Double.parseDouble(sines.get(i).getLat());
                            double longLat=Double.parseDouble(sines.get(i).getLongitude());

                            mMap.addMarker(new MarkerOptions().position(new LatLng(lati,longLat)).title(sines.get(i).getNome()));

                        }

                    } else {
                        Log.e(this.getClass().toString(), "Error on calling " + response.code() );
                    }
                } catch (Exception e) {
                    Log.e(this.getClass().toString(), e.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Sine>> call, Throwable t) {
                Log.e("onFailure", "Error" + t.getMessage());
            }

        });

        //Controles do zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);


        //Listener da ação realizada ao clicar em um ponto do mapa - TODO - ampliar sine clicado
        //mMap.setOnMapClickListener(new OnMapClickListener(this));

    }
}