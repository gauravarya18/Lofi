package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
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
import java.net.MalformedURLException;
import java.net.URL;
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
    ArrayList<Integer> Level = new ArrayList<>();
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
    Map<String,ArrayList<String>> Wifiresults=new HashMap<>();
    EditText resultData;
    int flag_dobule=1;
    Map<String,Map<String,ArrayList<String>>> Data = new HashMap<>();

    String xx="";
    String finalresults="";
    private String TAG ="main4activty";
    public String  actualfilepath="";
    private int request_code =1, FILE_SELECT_CODE =101;

    Map<String,ArrayList<String>> t1=new HashMap<>();
    Map<String,ArrayList<String>> t2=new HashMap<>();
    Map<String,ArrayList<String>> t3=new HashMap<>();

    String TextFileURL = "https://raw.githubusercontent.com/gauravarya18/Lofi/master/data.txt" ;
    TextView textView ;
    Button button ;
    URL url ;
    String TextHolder = "" , TextHolder2 = "";
    BufferedReader bufferReader ;
    int flag=0;
    int x=0;
    EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        new GetNotePadFileFromServer().execute();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        Wifiresults = (Map<String,ArrayList<String>>) args.getSerializable("ARRAYLIST");

        Bundle args1 = intent.getBundleExtra("BUNDLE1");
        list = (ArrayList<String>) args1.getSerializable("ARRAYLIST1");

        Bundle args2 = intent.getBundleExtra("BUNDLE2");
        Level = (ArrayList<Integer>) args2.getSerializable("ARRAYLIST2");

        Bundle args3 = intent.getBundleExtra("BUNDLE3");
        x = (int) args3.getSerializable("ARRAYLIST3");


        textmsg=(EditText)findViewById(R.id.filedata);
        resultData=findViewById(R.id.Result);
         tv=findViewById(R.id.textView);


    }


    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        if(flag_dobule==0)
        {

            Intent i=new Intent(this,Main3Activity.class);
            Bundle args3 = new Bundle();
            args3.putSerializable("ARRAYLIST3",(Serializable)x);
            i.putExtra("BUNDLE3",args3);
            this.startActivity(i);
            finish();
        }

        flag_dobule=0;





        tv.setText("");
        //textmsg.setText("");
        String s="All list "+ Wifiresults.size()+"\n";
        for(int i=0;i<list.size();i++)
        {
            s=s+list.get(i)+" "+"\n";
        }
        s+="\n";
        for(int i=0;i<Level.size();i++)
        {
            s=s+Level.get(i)+" "+"\n";
        }
        s+="\n";
        Log.d("hey-size",Long.toString(Wifiresults.size()));
        String new1="";
        for (int i=0;i<list.size();i++)
        {
            new1=new1+list.get(i) + " -- ";
            //tv.setText(tv.getText()+ entry.getKey() + " -- ");
            for(int j=0;j<Wifiresults.get(list.get(i)).size();j++)
            {
                new1=new1 + Wifiresults.get(list.get(i)).get(j)+"  ";
                //tv.setText(tv.getText()+entry.getValue().get(i));
            }
           // tv.setText("\n");

            new1=new1 + "\n";
        }

        tv.setText(s+new1);
        wifiData=tv.getText().toString();

        // performfileSearch();
//      FileData=readText(("Download/data.txt"));
//        textmsg.setText(FileData);
        //textmsg.setText("     ");



                resultData.setText("");
                String x=compareData();
                resultData.setText(x+"\n"+finalresults);
                if(Wifiresults.size()==0)
                    tv.setText("Please on Wifi or there is no wifi signal around you");



    }




    public class GetNotePadFileFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(TextFileURL);

                bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String d="!";
                while ((TextHolder2 = bufferReader.readLine()) != null) {

                    String Key_location="";
                    ArrayList<String>tempList=new ArrayList<>();
//                    TextHolder+=TextHolder2;
//                    TextHolder+="\n";
                    if(TextHolder2.matches(d))
                    {
                        TextHolder2=bufferReader.readLine();
                        Key_location=TextHolder2;
                        TextHolder+=TextHolder2;
                        TextHolder+="\n";
                        Map<String,ArrayList<String>> x=new HashMap<>();

                        while ((TextHolder2 = bufferReader.readLine()) != null)
                        {
                            if(TextHolder2.matches(d))
                                break;

                            ArrayList<String> values=new ArrayList<>();
                            String temp[]=TextHolder2.split(" ");
                            for(int i=1;i<temp.length;i++)
                                values.add(temp[i]);

                            String key= temp[0];
                            x.put(key,values);
                            //Toast.makeText(this,"Error in Fetching",Toast.LENGTH_LONG).show();
                            TextHolder+=TextHolder2;
                            TextHolder+="\n";
                        }

                        Log.d("map","iiiii");
                        Log.d("map",Key_location);
                        for(int i=0;i<tempList.size();i++)
                            Log.d("map",tempList.get(i));
                        Data.put(Key_location,x);
                    }




                }
                bufferReader.close();

            } catch (MalformedURLException malformedURLException) {

                // TODO Auto-generated catch block
                malformedURLException.printStackTrace();
                TextHolder = malformedURLException.toString();

            } catch (IOException iOException) {

                // TODO Auto-generated catch block
                iOException.printStackTrace();

                TextHolder = iOException.toString();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void finalTextHolder) {

            textmsg.setText("     ");
            textmsg.setText(TextHolder);

            super.onPostExecute(finalTextHolder);
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
            String d="!";
            while((line = bf.readLine())!=null)
            {
                String Key="";
                ArrayList<String>tempList=new ArrayList<>();
                text.append(line);
                text.append("\n");
                Log.d("map",line);
                if(line.matches(d))
                {
                    line=bf.readLine();
                    Key=line;
                    text.append(line);
                    while((line = bf.readLine())!=null)
                    {
                        if(line.matches(d))
                            break;
                        tempList.add(line);
                        //Toast.makeText(this,"Error in Fetching",Toast.LENGTH_LONG).show();
                        text.append(line);

                        text.append("\n");
                    }
                }
                Log.d("map","iiiii");
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
    private String compareData()
    {
        int maxScore =0;
        String finalLocation="No location Found";
        finalresults="";

        for (Map.Entry<String,Map<String,ArrayList<String>>> entry : Data.entrySet())
        {
            String currLocation = entry.getKey();
            Map<String,ArrayList<String>> Key_value=new HashMap<>();
            Key_value=entry.getValue();
            int currScore =0;
            for (Map.Entry<String,ArrayList<String>> e : Wifiresults.entrySet())
            {
                if(Key_value.containsKey(e.getKey())) {
                    ArrayList<String> arr1 = Key_value.get(e.getKey());
                    ArrayList<String> arr2 = e.getValue();
                    currScore+=cal(arr1,arr2);

                }
            }

            int maxPossibleScore = getMaxPossibleScore(Key_value);
            float percent=((float)currScore/(float)maxPossibleScore);
                percent*=100;

            finalresults=finalresults+currLocation+"   "+Float.toString(percent)+"%";
            finalresults=finalresults+"\n";

            if(currScore>maxScore)
            {
                finalLocation=currLocation;
                maxScore=currScore;
            }

        }

        return finalLocation;
    }

    //    Specifying a special Algorithm to compare the strings
    int cal(ArrayList<String> arr1,ArrayList<String> arr2)
    {
        int score=0;
        for(int i=0;i<arr1.size();i++)
            for(int j=0;j<arr2.size();j++)
            {
                if(arr1.get(i).matches(arr2.get(j)))
                    score+=1;
            }

        return score;
    }

    int getMaxPossibleScore(Map<String,ArrayList<String>> m)
    {
        int score=0;
        for (Map.Entry<String,ArrayList<String>> e : m.entrySet())
        {
            score+=e.getValue().size();
        }
        return score;
    }
}