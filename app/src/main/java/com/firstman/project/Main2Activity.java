package com.firstman.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentorlogin);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(loginreceiver,new IntentFilter("project.login"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(loginreceiver);
    }

    public void onClick(View v) {
        EditText user =(EditText)findViewById(R.id.idm);
        String us1=user.getText().toString();
        EditText password =(EditText)findViewById(R.id.pass);
        String passwrd=password.getText().toString();
        EditText menid =(EditText)findViewById(R.id.mid);
        String mentorid=menid.getText().toString();
        switch (v.getId())
        {


            case R.id.checkmentor:
                Intent it2=new Intent(Main2Activity.this,mentorloginapicall.class);
                it2.putExtra("username",us1);
                it2.putExtra("password",passwrd);
                it2.putExtra("mentorid",mentorid);

                startService( it2);
            default:
                break;
        }}

public void onBackPressed(){finish();};


    BroadcastReceiver loginreceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String response=intent.getStringExtra("response");

                    try{JSONObject jsonObject= new JSONObject(response);
                        Integer status = jsonObject.getInt("status");
                        String message = jsonObject.getString("message");
                        if(status==1){
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            EditText user =(EditText)findViewById(R.id.idm);
                            String us1=user.getText().toString();
                            EditText password =(EditText)findViewById(R.id.pass);
                            String passwrd=password.getText().toString();
                            EditText menid =(EditText)findViewById(R.id.mid);
                            String mentorid=menid.getText().toString();
                            Intent intents=new Intent(Main2Activity.this,Main4Activity.class);
                            intents.putExtra("username",us1);
                            intents.putExtra("password",passwrd);
                            intents.putExtra("mentorid",mentorid);
                            startActivity(intents);



                        }else{ Toast.makeText(context, message, Toast.LENGTH_SHORT).show();}
        }catch (JSONException JEXP){JEXP.printStackTrace();}}
    };
}