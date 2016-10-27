package br.edu.ifpb.appcivico.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import br.edu.ifpb.appcivico.R;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesBR;
import br.edu.ifpb.appcivico.listeners.OnClickListarSinesRaio;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OpcoesListarSinesActivity extends AppCompatActivity {

    MainActivity mainActivity;
    @BindView(R.id.btnListarSinesBR) Button btnListarSinesBR;
    @BindView(R.id.btnListarSineRaio) Button btnListarSineRaio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_listar_sines);
        ButterKnife.bind(this);

        btnListarSinesBR.setOnClickListener(new OnClickListarSinesBR(mainActivity));
        btnListarSineRaio.setOnClickListener(new OnClickListarSinesRaio(mainActivity));

    }

}
