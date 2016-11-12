package br.edu.ifpb.appcivico.network;

import java.util.List;

import br.edu.ifpb.appcivico.entities.Sine;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rebeca on 27/10/2016.
 */

public interface APIServices {

    @GET("latitude/-7.242662/longitude/-35.9716057/raio/100")
    Call<List<Sine>> getSinesComRaio();

    @GET(" ")
    Call<List<Sine>> getSinesBR();

    @GET("cod/{cod}")
    Call<List<Sine>> getSinePorCod(@Path("cod") String cod);
}
