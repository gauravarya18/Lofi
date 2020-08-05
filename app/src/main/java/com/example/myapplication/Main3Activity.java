package com.example.myapplication;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    ImageView bgapp;
    LinearLayout textsplash, texthome;
    Animation frombottom;
    ConstraintLayout login;
    TextView heading,subheading;
    private static final int PERMISSION_REQUEST_STORAGE=1000;
    private static final int READ_REQUEST_CODE=42;
    private ListView wifiList;
    ArrayList<String> BSSID = new ArrayList<>();
    private WifiManager wifiManager;
    private final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
    WifiReceiver receiverWifi;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        Bundle args3 = intent.getBundleExtra("BUNDLE3");
        x = (int) args3.getSerializable("ARRAYLIST3");

        //getWindow().setSharedElementEnterTransition(enterTransition());
        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);


        bgapp = (ImageView) findViewById(R.id.bgapp);
        heading =(TextView) findViewById(R.id.textView6);
//
        subheading =(TextView) findViewById(R.id.textView5);
        frombottom = AnimationUtils.loadAnimation(Main3Activity.this, R.anim.frombottom);
//        textsplash = (LinearLayout) findViewById(R.id.textsplash);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "Turning WiFi ON  ...", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }
        subheading.setVisibility(View.INVISIBLE);
        heading.startAnimation(frombottom);
//        new CountDownTimer(2000, 1000) {
//            public void onFinish() {
//                subheading.setVisibility(View.VISIBLE);
//            }
//
//            public void onTick(long millisUntilFinished) {
//
//            }
//        }.start();

      //  bgapp.animate().translationY(1500).setDuration(1000).setStartDelay(500);
//        textsplash.animate().translationY(140).alpha(0).setDuration(800).setStartDelay(800);

//        new CountDownTimer(4000, 1000) {
//            public void onFinish() {
//                // When timer is finished
//                // Execute your code here
//
//                Intent i=new Intent(Main3Activity.this,Main4Activity.class);
//                Pair[] pairs=new Pair[1];
//                //pairs[0]=new Pair<View,String>(bgapp,"backgroungimg");
//                pairs[0]=new Pair<View,String>(heading,"title");
//               // pairs[2]=new Pair<View,String>(subheading,"subtitle");
//
//                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Main3Activity.this,pairs);
//
//
////                Bundle args = new Bundle();
////                args.putSerializable("ARRAYLIST",(Serializable)BSSID);
////                i.putExtra("BUNDLE",args);
////                startActivity(i);
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                finish();
//            }

//            public void onTick(long millisUntilFinished) {
//
//
//                //login.setVisibility(View.VISIBLE);
//
//
//
//            }
//        }.start();




    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        receiverWifi = new WifiReceiver(wifiManager, wifiList,this,BSSID,x);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(receiverWifi, intentFilter);
        getWifi();
    }

    private void getWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Toast.makeText(Main4Activity.this, "version>=marshmallow", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Main3Activity.this, "location turned off", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(Main3Activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            } else {
                //Toast.makeText(Main4Activity.this, "location turned on", Toast.LENGTH_SHORT).show();
                wifiManager.startScan();
            }
        } else {
            Toast.makeText(Main3Activity.this, "scanning", Toast.LENGTH_SHORT).show();
            wifiManager.startScan();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Main3Activity.this, "permission granted", Toast.LENGTH_SHORT).show();
                    wifiManager.startScan();
                } else {

                    Toast.makeText(Main3Activity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }


}
