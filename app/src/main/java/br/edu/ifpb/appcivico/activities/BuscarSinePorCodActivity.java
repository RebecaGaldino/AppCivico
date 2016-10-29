package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class BuscarSinePorCodActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtCodSine) EditText cod;
    @BindView(R.id.btnBuscar) Button btnBuscar;
    @BindView(R.id.tvCod) TextView tvCod;



    @BindView(R.id.tvNome) TextView tvNome;
    @BindView(R.id.tvEC) TextView tvEC;
    @BindView(R.id.tvEndereco) TextView tvEndereco;
    @BindView(R.id.tvBairro) TextView tvBairro;
    @BindView(R.id.tvCep) TextView tvCep;
    @BindView(R.id.tvTel) TextView tvTel;
    @BindView(R.id.tvMunicipio) TextView tvMunicipio;
    @BindView(R.id.tvUf) TextView tvUf;



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
            @Override
            public void run() {

                ServerConnection.getInstance().getService().getSinePorCod(cod.getText().toString());

                Call<List<Sine>> call = ServerConnection.getInstance().getService().getSinePorCod(cod.getText().toString());

                Log.i(this.getClass().getName(), "Calling Sine");

                call.enqueue(new Callback<List<Sine>>() {
                    @Override
                    public void onResponse(Call<List<Sine>> call, Response<List<Sine>> response) {

                        try{

                            if(response.isSuccessful()){
                                List<Sine> sines = response.body();

                                Sine sine = sines.get(0);

                               getTvNome().setText(sine.getNome());
                               getTvBairro().setText(sine.getBairro());
                               getTvCep().setText(sine.getCep());
                               getTvCod().setText(sine.getCodPosto());
                               getTvEC().setText(sine.getEntidadeConveniada());
                               getTvTel().setText(sine.getTelefone());
                               getTvMunicipio().setText(sine.getMunicipio());
                               getTvUf().setText(sine.getUf());
                               getTvEndereco().setText(sine.getEndereco());

                            }
                            else{
                                Log.e(this.getClass().toString(), "Error on calling"+ response.code());
                            }
                        }
                        catch (Exception e){
                            Log.e(this.getClass().toString(), e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Sine>> call, Throwable t) {
                        Log.e("onFailure", "Error"+ t.getMessage());
                    }
                });
            }
        }).start();
    }





    public TextView getTvBairro() {
        return tvBairro;
    }

    public void setTvBairro(TextView tvBairro) {
        this.tvBairro = tvBairro;
    }

    public TextView getTvCep() {
        return tvCep;
    }

    public void setTvCep(TextView tvCep) {
        this.tvCep = tvCep;
    }

    public TextView getTvCod() {
        return tvCod;
    }

    public void setTvCod(TextView tvCod) {
        this.tvCod = tvCod;
    }

    public TextView getTvEC() {
        return tvEC;
    }

    public void setTvEC(TextView tvEC) {
        this.tvEC = tvEC;
    }

    public TextView getTvEndereco() {
        return tvEndereco;
    }

    public void setTvEndereco(TextView tvEndereco) {
        this.tvEndereco = tvEndereco;
    }

    public TextView getTvMunicipio() {
        return tvMunicipio;
    }

    public void setTvMunicipio(TextView tvMunicipio) {
        this.tvMunicipio = tvMunicipio;
    }

    public TextView getTvNome() {
        return tvNome;
    }

    public void setTvNome(TextView tvNome) {
        this.tvNome = tvNome;
    }

    public TextView getTvTel() {
        return tvTel;
    }

    public void setTvTel(TextView tvTel) {
        this.tvTel = tvTel;
    }

    public TextView getTvUf() {
        return tvUf;
    }

    public void setTvUf(TextView tvUf) {
        this.tvUf = tvUf;
    }
}