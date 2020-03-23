package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
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

class WifiReceiver extends BroadcastReceiver {

    WifiManager wifiManager;
    StringBuilder sb;
    ListView wifiDeviceList;
    Context con;
    ArrayList<String> list=new ArrayList<String>();

    public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList,Context context,ArrayList<String> list1 ) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
        this.con=context;
        this.list=list1;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            //sb = new StringBuilder();
            List<ScanResult> wifiList = wifiManager.getScanResults();

            ArrayList<String> BSSID = new ArrayList<>();
            ArrayList<String> Level = new ArrayList<>();
            for (ScanResult scanResult : wifiList) {
               // sb.append("\n").append(scanResult.SSID).append("   -   ").append(scanResult.BSSID).append("  -   ").append("   -   ").append(scanResult.level).append(scanResult.capabilities);
                if(scanResult.level>-80)
                    if(!list.contains(scanResult.BSSID+"\n"+scanResult.SSID+"\n-----\n"))
                list.add(scanResult.BSSID+"\n"+scanResult.SSID+"\n-----\n");
                //Level.add(scanResult.level+"");
            }

            //Ref#1 https://stackoverflow.com/questions/21888385/how-to-call-the-start-activity-from-one-java-class/21888725
            Intent i=new Intent(context,Main4Activity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)list);
            i.putExtra("BUNDLE",args);
            context.startActivity(i);
            MainActivity.getInstance().finish();




            //Toast.makeText(context, sb, Toast.LENGTH_SHORT).show();
            //MainActivity.getInstance().myMethod();

            //To show as list in first activity
//            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceList.toArray());
//
//            wifiDeviceList.setAdapter(arrayAdapter);
        }
    }




}
