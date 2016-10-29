package br.edu.ifpb.appcivico.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appcivico.activities.ListarSinesComRaioActivity;
import br.edu.ifpb.appcivico.activities.MainActivity;

/**
 * Created by rebeca on 27/10/2016.
 */

public class OnClickListarSinesComRaio implements View.OnClickListener{

    MainActivity mainActivity;

    public OnClickListarSinesComRaio(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(this.mainActivity, ListarSinesComRaioActivity.class);
        this.mainActivity.startActivity(intent);

    }

}
