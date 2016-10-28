package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.appcivico.R;
import br.edu.ifpb.appcivico.entities.Sine;
import br.edu.ifpb.appcivico.network.ServerConnection;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarSineComRaioActivity extends AppCompatActivity {

        @BindView(R.id.lvSines) ListView lvSines;
        ArrayAdapter<Sine> adapter;
        List<Sine> sines;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_listar_sine_com_raio);


            ButterKnife.bind(this);

            sines = new ArrayList<Sine>();
            adapter = new ArrayAdapter<Sine>(this, android.R.layout.simple_list_item_1, sines);
            lvSines.setAdapter(adapter);

            listar();
        }


        public void listar(){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    Call<List<Sine>> call = ServerConnection.getInstance().getService().listarWithRaio();

                    Log.i(this.getClass().getName(), "realizando chamada ao serviço de listagem");

                    call.enqueue(new Callback<List<Sine>>() {
                        @Override
                        public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {

                            try{

                                if(response.isSuccessful()){
                                    List<Sine> sinesResponse = response.body();

                                    sines.addAll(sinesResponse);
                                    adapter.notifyDataSetChanged();
                                }
                                else{
                                    Log.e(this.getClass().toString(), "Erro na chamada");
                                }


                            }
                            catch (Exception e){
                                Log.e(this.getClass().toString(), "Erro na chamada");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Sine>> call, Throwable t) {
                            Log.e("onFailure", "Erro");
                        }
                    });
                }
            }).start();
        }

}
