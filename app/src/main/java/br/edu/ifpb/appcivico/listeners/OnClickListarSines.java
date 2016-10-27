package br.edu.ifpb.appcivico.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appcivico.activities.MainActivity;
import br.edu.ifpb.appcivico.activities.OpcoesListarSinesActivity;

/**
 * Created by rebeca on 27/10/2016.
 */

public class OnClickListarSines implements View.OnClickListener{

    MainActivity mainActivity;

    public OnClickListarSines(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(this.mainActivity, OpcoesListarSinesActivity.class);
        this.mainActivity.startActivity(intent);

    }

}
