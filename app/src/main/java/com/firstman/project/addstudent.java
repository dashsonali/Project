package com.firstman.project;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by USER on 08-07-2017.
 */

public class addstudent extends IntentService{
    OkHttpClient client = new OkHttpClient();
    public String webservice="http://192.168.43.62:82/project/addstudent.php";
    public addstudent(){
        super("addstudent");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        String studentname=intent.getStringExtra("studentname").trim();
        String regnno=intent.getStringExtra("regnno").trim();
        String strmentorid=intent.getStringExtra("mentorid").trim();
        String cgpa=intent.getStringExtra("cgpa").trim();
        String complaints=intent.getStringExtra("complaints").trim();
        String response3 = SendDataByOkHttp(studentname,regnno,strmentorid,cgpa,complaints);
        Log.e("response3",response3 );
        sendResponse(response3);

    }

    private String SendDataByOkHttp(String studentname,String regnno,String strmentorid,String cgpa,String complaints) {


        try {


            Request request = new Request.Builder()
                    .url(webservice + "?name="+studentname+"&regnno="+regnno+"&mentorid="+strmentorid+"&cgpa="+cgpa+"&complaints="+complaints)
                    .build();

            Response response3 = client.newCall(request).execute();
            return response3.body().string();



        }catch (IOException io){io.printStackTrace();}return null;}
    private void sendResponse(String response3){
        Intent intent =new Intent();
        intent.putExtra("response3",response3);
        intent.setAction("project.addstudent");
        sendBroadcast(intent);
    }
}

