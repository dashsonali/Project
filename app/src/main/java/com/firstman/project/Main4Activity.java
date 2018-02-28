package com.firstman.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentlist);
        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        String mentorid=intent.getStringExtra("mentorid");
        TextView textView=(TextView)findViewById(R.id.tex);
        textView.setText("welcome "+username);
        Intent service2=new Intent(Main4Activity.this,list.class);
        service2.putExtra("username",username);
        service2.putExtra("password",password);
        service2.putExtra("mentorid",mentorid);
        startService( service2);
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(listreceiver,new IntentFilter("project.list"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(listreceiver);
    }
    public void onBackPressed(){finish();};


    BroadcastReceiver listreceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String response2=intent.getStringExtra("response2");
            TextView listView=(TextView) findViewById(R.id.list);
            listView.setText(response2.toString().replaceAll("<br>","\n "));


        }
    };}
