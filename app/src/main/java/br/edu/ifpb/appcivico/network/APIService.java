package br.edu.ifpb.appcivico.network;

import java.util.List;

import br.edu.ifpb.appcivico.entities.Sine;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rebeca on 27/10/2016.
 */

public interface APIService {

    @GET("latitude/-7.242662/longitude/-35.9716057/raio/50")
    Call<List<Sine>> listarWithRaio();
}
