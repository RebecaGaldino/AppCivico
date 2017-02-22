package br.edu.ifpb.appcivico.listeners;

import android.Manifest;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import br.edu.ifpb.appcivico.activities.MainActivity;
import br.edu.ifpb.appcivico.activities.SinesGPSMapsActivity;

/**
 * Created by rebeca on 21/02/2017.
 */

public class OnClickSinesPorGPS implements View.OnClickListener,LocationListener {
    MainActivity main;

    // Location
    private LocationManager locationManager;
    private Location location;
    private double latitude;
    private double longitude;

    // Configuração
    private final int REQUEST_LOCATION = 200;
    private static final String TAG = "SineMaps";

    Intent intent;
    Bundle bundle;




    public OnClickSinesPorGPS(MainActivity mainActivity) {
        this.main = mainActivity;
    }

    @Override
    public void onClick(View v) {
        // Inicializar comunicação com o módulo de GPS do dispositivo móvel.
        locationManager = (LocationManager) main.getSystemService(Service.LOCATION_SERVICE);
        /*
         * Verificar permissões de acesso. Desde a API 23 o usuário também precisa conscentir expressamente
         * que permite o acesso ao GPS. A permissão do manifest também precisa ser adicionada.
         */
        if (ActivityCompat.checkSelfPermission(main,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(main,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Requisitar permissão de acesso ao usuário.
            ActivityCompat.requestPermissions(main,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);

        } else {

            // Caso a permissão já conscentida atualizar a localização.
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2,this);

            if (locationManager != null) {
                // Localização atualizada.
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            if (location != null) {

                // Posições do GPS
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                startSinesGPSMapsActivity();
            }else{
                showGPSDisabledAlertToUser();
            }
        }
    }

    /**
     * Caixa de diálogo para habilitar o GPS nas configurações do dispositivo móvel.
     */
    private void showGPSDisabledAlertToUser() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(main);
        alertDialogBuilder.setMessage("O GPS está desabilitado no seu dispositivo. Deseja habilitar?")
                .setCancelable(false)
                .setPositiveButton("Direcione para as configurações para habilitar o GPS.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        main.startActivity(callGPSSettingIntent);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        startSinesGPSMapsActivity();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.i(TAG, "Status Changed");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i(TAG, "enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i(TAG, "Disabled");
        if (s.equals(LocationManager.GPS_PROVIDER)) {
            showGPSDisabledAlertToUser();
        }
    }

    private void startSinesGPSMapsActivity(){
        //Envia os dados de latitude e longitude para a Activity em um bundle (pacote) anexo na intent
        intent = new Intent(main, SinesGPSMapsActivity.class);
        bundle = new Bundle();

        bundle.putDouble("latitude", latitude);
        bundle.putDouble("longitude", longitude);

        intent.putExtras(bundle);
        main.startActivity(intent);
        main.finish();
    }

}


