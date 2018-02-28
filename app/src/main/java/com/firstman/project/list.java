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

public class list extends IntentService {
    OkHttpClient client = new OkHttpClient();
    public String webservice="http://192.168.43.62:82/project/studentlist.php";
    public list(){
        super("list");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        String strusername=intent.getStringExtra("username").trim();
        String strpassword=intent.getStringExtra("password").trim();
        String strmentorid=intent.getStringExtra("mentorid").trim();
        String response2 = SendDataByOkHttp(strusername,strpassword,strmentorid);

        Log.e("response",response2 );
        sendResponse2(response2);

    }
    private String SendDataByOkHttp(String strusername, String strpassword, String strmentorid) {


        try {


            Request request = new Request.Builder()
                    .url(webservice + "?username="+strusername+"&password="+strpassword+"&mentor_id="+strmentorid)
                    .build();

            Response response2 = client.newCall(request).execute();
            return response2.body().string();



        }catch (IOException io){io.printStackTrace();}return null;}
    private void sendResponse2(String response2){
        Intent intent =new Intent();
        intent.putExtra("response2",response2);
        intent.setAction("project.list");
        sendBroadcast(intent);
    }
}
