package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifpb.appcivico.listeners.OnClickBuscarSinePorCod;
import br.edu.ifpb.appcivico.listeners.OnClickListarSines;
import butterknife.*;


import br.edu.ifpb.appcivico.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnListarSines) Button btnListarSines;
    @BindView(R.id.btnBuscarSinePorCod) Button btnBuscarSinePorCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnListarSines.setOnClickListener(new OnClickListarSines(this));
        btnBuscarSinePorCod.setOnClickListener(new OnClickBuscarSinePorCod(this));
    }
}