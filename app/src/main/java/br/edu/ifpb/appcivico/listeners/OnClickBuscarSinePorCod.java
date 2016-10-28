package br.edu.ifpb.appcivico.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appcivico.activities.BuscarSinePorCodActivity;
import br.edu.ifpb.appcivico.activities.ListarSineComRaioActivity;
import br.edu.ifpb.appcivico.activities.MainActivity;

/**
 * Created by rebeca on 27/10/2016.
 */

public class OnClickBuscarSinePorCod implements View.OnClickListener {

    MainActivity mainActivity;

    public OnClickBuscarSinePorCod(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.mainActivity, BuscarSinePorCodActivity.class);
        this.mainActivity.startActivity(intent);
    }
}
