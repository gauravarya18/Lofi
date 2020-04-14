package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    private static final int PERMISSION_REQUEST_STORAGE=1000;
    private static final int READ_REQUEST_CODE=42;
    private ListView wifiList;
    private WifiManager wifiManager;
    private final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
    WifiReceiver receiverWifi;
    TextView textmsg;
    static final int READ_BLOCK_SIZE = 100;
    String xx="";

    private String TAG ="main4activty";
    public String  actualfilepath="";
    private int request_code =1, FILE_SELECT_CODE =101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        //Request Permission //5
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

//1
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "Turning WiFi ON  ...", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        textmsg=(TextView)findViewById(R.id.filedata);

//        try {
//            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
//            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//
//            String temp="Hi Chutiye hey baby, tu gandu h.";
//
//            outputWriter.write(temp);
//            outputWriter.close();
//
//            //display file saved message
//            Toast.makeText(getBaseContext(), "File saved successfully!",
//                    Toast.LENGTH_SHORT).show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }


    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
//2
        EditText tv=findViewById(R.id.textView);
        for(int i=0;i<list.size();i++)
        {
            tv.setText(tv.getText()+"\n"+list.get(i)+"\n");
        }

       //6
       // performfileSearch();
        textmsg.setText(readText("Download/data.txt"));

//        try {
//            FileInputStream fileIn=openFileInput("mytextfile.txt");
//            InputStreamReader InputRead= new InputStreamReader(fileIn);
//
//            char[] inputBuffer= new char[READ_BLOCK_SIZE];
//            String s="";
//            int charRead;
//
//            while ((charRead=InputRead.read(inputBuffer))>0) {
//                // char to string conversion
//                String readstring=String.copyValueOf(inputBuffer,0,charRead);
//                s +=readstring;
//            }
//            InputRead.close();
//            textmsg.setText(s);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

//3
    @Override
    protected void onPostResume() {
        super.onPostResume();
        receiverWifi = new WifiReceiver(wifiManager, wifiList,this,list);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(receiverWifi, intentFilter);
        getWifi();
//hh
    }

    //7
    private void performfileSearch()
    {
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        Toast.makeText(Main4Activity.this, "permissio", Toast.LENGTH_LONG).show();
        startActivityForResult(intent,READ_REQUEST_CODE);
    }


//4
    private void getWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(Main4Activity.this, "version>=marshmallow", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(Main4Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Main4Activity.this, "location turned off", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            } else {
                Toast.makeText(Main4Activity.this, "location turned on", Toast.LENGTH_SHORT).show();
                wifiManager.startScan();
            }
        } else {
            Toast.makeText(Main4Activity.this, "scanning", Toast.LENGTH_SHORT).show();
            wifiManager.startScan();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    //8
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Main4Activity.this, "permission granted", Toast.LENGTH_SHORT).show();
                    wifiManager.startScan();
                } else {

                    Toast.makeText(Main4Activity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case PERMISSION_REQUEST_STORAGE:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(Main4Activity.this, "permission granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Main4Activity.this, "permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(Main4Activity.this, "88888888", Toast.LENGTH_LONG).show();
       if(requestCode==READ_REQUEST_CODE&&resultCode== Activity.RESULT_OK){
           if(data!=null){
               Uri uri=data.getData();
               String path=uri.getPath();
               path=path.substring(path.indexOf(":")+1);
               if(path.contains("emulated")){
                   path=path.substring(path.indexOf("0")+1);
               }
               Toast.makeText(Main4Activity.this, ""+path, Toast.LENGTH_SHORT).show();
               xx=path;
               textmsg.setText(readText(path));
           }
       }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;


        Toast.makeText(this, "Please   click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    //9
    private String readText(String input){


        Toast.makeText(Main4Activity.this, input, Toast.LENGTH_SHORT).show();
        File file =new File(Environment.getExternalStorageDirectory(),input);
        StringBuilder text=new StringBuilder();

        try{

            BufferedReader bf=new BufferedReader(new FileReader(file));
            String line;
            while((line = bf.readLine())!=null)
            {
                text.append(line);
                text.append("\n");
            }
            bf.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return text.toString();

    }




}