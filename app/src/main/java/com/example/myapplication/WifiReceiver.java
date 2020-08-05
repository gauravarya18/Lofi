package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class WifiReceiver extends BroadcastReceiver {

    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;
    Context con;
    int c;
    ArrayList<String> list=new ArrayList<String>();

    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList,Context context,ArrayList<String> list1 , int x) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
        this.con=context;
        this.list=list1;
        this.c=x;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            //sb = new StringBuilder();
            List<ScanResult> wifiList = wifiManager.getScanResults();
            Log.d("hey","started");

            ArrayList<Integer> Level = new ArrayList<>();
            for (ScanResult scanResult : wifiList) {
                // sb.append("\n").append(scanResult.SSID).append("   -   ").append(scanResult.BSSID).append("  -   ").append("   -   ").append(scanResult.level).append(scanResult.capabilities);
                Log.d("hey-ids",scanResult.BSSID);
//                if(scanResult.level>-80)
//                    if(!list.contains(scanResult.BSSID))
                        list.add(scanResult.BSSID);
                        Level.add((scanResult.level));
            }
            Map<String,ArrayList<String>> WifiResults = new HashMap<>();

            for(int i=0;i<list.size();i++)
            {
                String key=list.get(i);
                Log.d("hey_key-i",key);
                int key_value=(Level.get(i));
                Log.d("hey-strength_i",Integer.toString(key_value));
                ArrayList<String> BSSID = new ArrayList<>();
                for(int j=0;j<list.size();j++)
                {
                    if(i!=j)
                    {
                        int value=(Level.get(j));
                        Log.d("hey-strength_j",Integer.toString(value));
                        if(value<=key_value)
                        {
                            BSSID.add(list.get(j));
                            Log.d("hey_key-j",list.get(j));
                        }
                    }
                }

//                if(BSSID.size()>0)
//                {
                    WifiResults.put(key,BSSID);
//                }

            }
            //Ref#1 https://stackoverflow.com/questions/21888385/how-to-call-the-start-activity-from-one-java-class/21888725
            Intent i=new Intent(context,Main4Activity.class);
            Bundle args1 = new Bundle();
            args1.putSerializable("ARRAYLIST1",(Serializable)list);
            i.putExtra("BUNDLE1",args1);

            Bundle args2 = new Bundle();
            args2.putSerializable("ARRAYLIST2",(Serializable)Level);
            i.putExtra("BUNDLE2",args2);

            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)WifiResults);
            i.putExtra("BUNDLE",args);

            Bundle args3 = new Bundle();
            args3.putSerializable("ARRAYLIST3",(Serializable)c);
            i.putExtra("BUNDLE3",args3);

            Log.d("hey","finished");
            context.startActivity(i);
//            MainActivity.getInstance().finish();




            //Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
            //MainActivity.getInstance().myMethod();

            //To show as list in first activity
//            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());
//
//            wifiDeviceList.setAdapter(arrayAdapter);
        }
    }




}