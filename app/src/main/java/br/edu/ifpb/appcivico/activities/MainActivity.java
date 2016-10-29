package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifpb.appcivico.listeners.OnClickBuscarSinePorCod;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesBR;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesComRaio;
import butterknife.*;


import br.edu.ifpb.appcivico.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnListarSinesCG)
    Button btnListarSinesCG;
    @BindView(R.id.btnListarSinesBR)
    Button btnListarSinesBR;
    @BindView(R.id.btnBuscarSinePorCod)
    Button btnBuscarSinePorCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnListarSinesCG.setOnClickListener(new OnClickListarSinesComRaio(this));
        btnListarSinesBR.setOnClickListener(new OnClickListarSinesBR(this));
        btnBuscarSinePorCod.setOnClickListener(new OnClickBuscarSinePorCod(this));


    }

}