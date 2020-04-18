package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main4Activity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    private static final int PERMISSION_REQUEST_STORAGE=1000;
    private static final int READ_REQUEST_CODE=42;
    private ListView wifiList;
    private WifiManager wifiManager;
    private final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
    WifiReceiver receiverWifi;
    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;
    Animation frombottom;
    ConstraintLayout login;
    String FileData;
    String wifiData;
    Map<String,ArrayList<String>> dataFile=new HashMap<>();
    EditText resultData;


    String xx="";
    private String TAG ="main4activty";
    public String  actualfilepath="";
    private int request_code =1, FILE_SELECT_CODE =101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        login =(ConstraintLayout) findViewById(R.id.login_page);
        login.setVisibility(View.INVISIBLE);
        getWindow().getSharedElementEnterTransition().setDuration(800);
       // getWindow().getSharedElementReturnTransition().setDuration(2000);
        new CountDownTimer(500, 1000) {
            public void onFinish() {
                frombottom = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.frombottom);
                login.setVisibility(View.VISIBLE);
                login.startAnimation(frombottom);
            }

            public void onTick(long millisUntilFinished) {

            }
        }.start();



        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(getApplicationContext(), "Turning WiFi ON  ...", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        textmsg=(EditText)findViewById(R.id.filedata);
        resultData=findViewById(R.id.Result);

//        try {
//            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
//            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//
//            String temp="Hi Chutiye, tu gandu h.";
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

        EditText tv=findViewById(R.id.textView);
        tv.setText("");
        textmsg.setText("");
        for(int i=0;i<list.size();i++)
        {
            tv.setText(tv.getText()+list.get(i)+"\n");
        }
        wifiData=tv.getText().toString();

       // performfileSearch();
      FileData=readText(("Download/data.txt"));
        textmsg.setText(FileData);

        String x=compareData((list));
        resultData.setText(x);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        receiverWifi = new WifiReceiver(wifiManager, wifiList,this,list);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(receiverWifi, intentFilter);
        getWifi();
    }

    private void getWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Toast.makeText(Main4Activity.this, "version>=marshmallow", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(Main4Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(Main4Activity.this, "location turned off", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(Main4Activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            } else {
                //Toast.makeText(Main4Activity.this, "location turned on", Toast.LENGTH_SHORT).show();
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
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
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
    private void performfileSearch()
    {
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        Toast.makeText(Main4Activity.this, "permissio", Toast.LENGTH_LONG).show();
        startActivityForResult(intent,READ_REQUEST_CODE);
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



    private String readText(String input){
//        Log.d("heyyy",input);
//        String[] A=input.split("/");
//        A[2]="data.txt";
//        input="/"+A[1]+"/"+A[2];
        Log.d("heyyy",input);
        Toast.makeText(Main4Activity.this, input, Toast.LENGTH_SHORT).show();
        File file =new File(Environment.getExternalStorageDirectory(),input);
        StringBuilder text=new StringBuilder();
        int n=list.size();
        int matches=0;
        String name="";
        try{

            BufferedReader bf=new BufferedReader(new FileReader(file));
            String line;
            Log.d("map","start");
            while((line = bf.readLine())!=null)
            {
                String Key="";
                ArrayList<String>tempList=new ArrayList<>();
                text.append(line);
                text.append("\n");
                if(line=="*")
                {
                    line=bf.readLine();
                    Key=line;
                    while((line = bf.readLine())!="*")
                    {
                        tempList.add(line);
                        text.append(line);
                        text.append("\n");
                    }
                }
                Log.d("map",Key);
                for(int i=0;i<tempList.size();i++)
                    Log.d("map",tempList.get(i));

                dataFile.put(Key,tempList);
            }
            Log.d("hey1",name);
            bf.close();

        }

        catch (IOException e)
        {
            Toast.makeText(this,"Error in Fetching",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
//        float x=matches/n;
        Log.d("map","end");
//        //return name+" "+String.valueOf(x);
        return text.toString();

    }

//    Adaptor to compare the file data and live data
    private String compareData(ArrayList<String>wifiData)
    {
        for (Map.Entry<String,ArrayList<String>> entry : dataFile.entrySet())
        {
            if(calScore(entry.getValue(),wifiData)>=0.5)
                return entry.getKey();

        }

        return "No location Found";
    }

//    Specifying a special Algorithm to compare the strings
    private float calScore(ArrayList<String>mapArray,ArrayList<String>liveData)
    {
//        if(mapArray==liveData)
//            return 1;
//        else
//            return 0;
        int match=0;
          for(int i=0;i<mapArray.size();i++)
          {
              Log.d("heyyy",mapArray.get(i));
              if(liveData.contains(mapArray.get(i)))
                  match+=1;
          }

          return match;
    }
}

