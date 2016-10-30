package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.edu.ifpb.appcivico.R;
import br.edu.ifpb.appcivico.entities.Sine;
import br.edu.ifpb.appcivico.network.ServerConnection;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarSinePorCodActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtCodSine)
    EditText edtCodSine;
    @BindView(R.id.btnBuscar)
    Button btnBuscar;
    @BindView(R.id.tvSine) TextView tvSine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_sine_por_cod);
        ButterKnife.bind(this);

        btnBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {

            String cod = edtCodSine.getText().toString();

            @Override
            public void run() {

                ServerConnection.getInstance().getService().getSinePorCod(cod);

                Call<List<Sine>> call = ServerConnection.getInstance().getService().getSinePorCod(cod);

                Log.i(this.getClass().getName(), "Calling Sine");

                call.enqueue(new Callback<List<Sine>>() {
                    @Override
                    public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {

                        try {

                            if (response.isSuccessful()) {
                                List<Sine> sines = response.body();

                                Sine sine = sines.get(0);
                                tvSine.setText(sine.toString());

                            } else {
                                Log.e(this.getClass().toString(), "Error on calling " + response.code() + cod);
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
            }
        }).start();

    }
}