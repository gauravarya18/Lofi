package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        //Ref#2 https://stackoverflow.com/questions/13601883/how-to-pass-arraylist-of-objects-from-one-to-another-activity-using-intent-in-an

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        list = (ArrayList<String>) args.getSerializable("ARRAYLIST");

        //Ref#3 https://stackoverflow.com/questions/14355731/killing-one-activity-from-another/14356774
       // MainActivity.getInstance().finish();

        EditText tv=findViewById(R.id.textView);
        for(int i=0;i<list.size();i++)
        {
            tv.setText(tv.getText()+"\n"+list.get(i)+"\n");
        }


    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Main2Activity.this,Main4Activity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)list);
        i.putExtra("BUNDLE",args);
        startActivity(i);
        finish();
    }
}
