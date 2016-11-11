package br.edu.ifpb.appcivico.listeners;

import android.content.Intent;
import android.view.View;

import br.edu.ifpb.appcivico.activities.MainActivity;
import br.edu.ifpb.appcivico.activities.MapsActivity;

/**
 * Created by rebeca on 10/11/2016.
 */

public class OnClickMapaSinesComRaio implements View.OnClickListener {
    MainActivity mainActivity;

    public OnClickMapaSinesComRaio(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.mainActivity, MapsActivity.class);
        this.mainActivity.startActivity(intent);
    }
}
