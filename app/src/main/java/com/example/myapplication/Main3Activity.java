package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ImageView bgapp;
    LinearLayout textsplash, texthome;
    Animation frombottom;
    ConstraintLayout login;
    TextView heading,subheading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //getWindow().setSharedElementEnterTransition(enterTransition());
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        heading =(TextView) findViewById(R.id.textView6);
//
        subheading =(TextView) findViewById(R.id.textView5);
//        textsplash = (LinearLayout) findViewById(R.id.textsplash);



      //  bgapp.animate().translationY(1500).setDuration(1000).setStartDelay(500);
//        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);

        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                // When timer is finished
                // Execute your code here
                ArrayList<String> BSSID = new ArrayList<>();
                Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                Pair[] pairs=new Pair[1];
                //pairs[0]=new Pair<View,String>(bgapp,"backgroungimg");
                pairs[0]=new Pair<View,String>(heading,"title");
               // pairs[2]=new Pair<View,String>(subheading,"subtitle");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Main3Activity.this,pairs);


                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)BSSID);
                i.putExtra("BUNDLE",args);
                startActivity(i,options.toBundle());
                finish();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();




    }



}
