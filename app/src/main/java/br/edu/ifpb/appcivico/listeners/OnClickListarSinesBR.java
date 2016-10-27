package br.edu.ifpb.appcivico.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appcivico.activities.ListarSinesBRActivity;
import br.edu.ifpb.appcivico.activities.MainActivity;

/**
 * Created by rebeca on 27/10/2016.
 */

public class OnClickListarSinesBR implements View.OnClickListener {

    MainActivity mainActivity;

    public OnClickListarSinesBR(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.mainActivity, ListarSinesBRActivity.class);
        this.mainActivity.startActivity(intent);
    }
}
