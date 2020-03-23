package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
      ArrayList<String> list = new ArrayList<>();
        ImageView bgapp, clover;
        LinearLayout textsplash, texthome;
        Animation frombottom;
        ConstraintLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);




        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        texthome = (LinearLayout) findViewById(R.id.texthome);
        login =(ConstraintLayout) findViewById(R.id.login_page);












        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        list = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        if (list.size()==0)
        {
            texthome.startAnimation(frombottom);
            login.startAnimation(frombottom);
            bgapp.animate().translationY(-1900).setDuration(800).setStartDelay(500);
            clover.animate().alpha(0).setDuration(800).setStartDelay(800);
            textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);
        }
        else
        {
            bgapp.animate().translationY(-1900);
            clover.animate().alpha(0);
        }

        Button buttonScan = findViewById(R.id.scanBtn);
        Button buttonScan1 = findViewById(R.id.scanBtn1);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main4Activity.this,MainActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)list);
                i.putExtra("BUNDLE",args);
                startActivity(i);
                finish();
            }
        });


     buttonScan1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Main4Activity.this,Main2Activity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)list);
            i.putExtra("BUNDLE",args);
            startActivity(i);
            finish();
        }

    });

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;


        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
