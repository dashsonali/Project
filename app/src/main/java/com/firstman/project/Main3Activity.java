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

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentlogin);
    }
    public void onBackPressed(){finish();};
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(loginstudent,new IntentFilter("project.addstudent"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(loginstudent);
    }

    public void onClick(View v) {
        EditText name =(EditText)findViewById(R.id.name);
        String sname=name.getText().toString();
        EditText reg =(EditText)findViewById(R.id.regnno);
        String regnno=reg.getText().toString();
        EditText menid =(EditText)findViewById(R.id.mentorid);
        String mentorid=menid.getText().toString();
        EditText cg =(EditText)findViewById(R.id.cgpa);
        String cgpa=cg.getText().toString();
        EditText com =(EditText)findViewById(R.id.complaints);
        String complaints=com.getText().toString();
        switch (v.getId())
        {


            case R.id.savestudent :

                Intent it2=new Intent(Main3Activity.this,addstudent.class);
                it2.putExtra("studentname",sname);
                it2.putExtra("regnno",regnno);
                it2.putExtra("mentorid",mentorid);
                it2.putExtra("cgpa",cgpa);
                it2.putExtra("complaints",complaints);

                startService( it2);
            default:
                break;
        }}




    BroadcastReceiver loginstudent = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String response3=intent.getStringExtra("response3");

            try{JSONObject jsonObject1= new JSONObject(response3);
                Integer status = jsonObject1.getInt("status");
                String message = jsonObject1.getString("message");
                if(status==1){
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                }else{ Toast.makeText(context, message, Toast.LENGTH_SHORT).show();}
            }catch (JSONException JEXP){JEXP.printStackTrace();}}
    };

}
