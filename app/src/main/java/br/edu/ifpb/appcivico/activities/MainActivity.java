package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifpb.appcivico.listeners.OnClickBuscarSinePorCod;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesBR;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesComRaio;
import br.edu.ifpb.appcivico.listeners.OnClickMapaSinesComRaio;
import br.edu.ifpb.appcivico.listeners.OnClickSinesPorGPS;
import butterknife.*;


import br.edu.ifpb.appcivico.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnListarSinesCG)
    Button btnListarSinesCG;
    @BindView(R.id.btnListarSinesBR)
    Button btnListarSinesBR;
    @BindView(R.id.btnBuscarSinePorCod)
    Button btnBuscarSinePorCod;
    @BindView(R.id.btnMap) Button btnMap;
    @BindView(R.id.btnMapGPS) Button btnMapGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnListarSinesCG.setOnClickListener(new OnClickListarSinesComRaio(this));
        btnListarSinesBR.setOnClickListener(new OnClickListarSinesBR(this));
        btnBuscarSinePorCod.setOnClickListener(new OnClickBuscarSinePorCod(this));
        btnMap.setOnClickListener(new OnClickMapaSinesComRaio(this));
        btnMapGPS.setOnClickListener(new OnClickSinesPorGPS(this));


    }

}