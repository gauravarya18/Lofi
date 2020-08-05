package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=new Intent(Main2Activity.this,Main3Activity.class);
        Bundle args3 = new Bundle();
        args3.putSerializable("ARRAYLIST3",(Serializable)0);
        i.putExtra("BUNDLE3",args3);

        Log.d("hey","finished");
        this.startActivity(i);



    }




}
