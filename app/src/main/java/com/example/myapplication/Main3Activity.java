package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ImageView bgapp;
    LinearLayout textsplash, texthome;
    Animation frombottom;
    ConstraintLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
//
//        textsplash = (LinearLayout) findViewById(R.id.textsplash);



//        bgapp.animate().translationY(+1000).setDuration(1000).setStartDelay(500);
//        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);

        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                ArrayList<String> BSSID = new ArrayList<>();
                Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)BSSID);
                i.putExtra("BUNDLE",args);
                startActivity(i);
                finish();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();




    }
}
