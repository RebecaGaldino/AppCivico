package br.edu.ifpb.appcivico.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rebeca on 27/10/2016.
 */

public class ServerConnection {
    private static final String URL_BASE =
            "http://mobile-aceite.tcu.gov.br/mapa-da-saude/rest/emprego/";


    private static APIServices service;
    private static ServerConnection selfInstance = new ServerConnection();

    public static ServerConnection getInstance() {
        return selfInstance;
    }

    public APIServices getService() {
        return service;
    }

    private ServerConnection() {
        updateServiceAdress();
    }

    public void updateServiceAdress() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIServices.class);
    }
}
