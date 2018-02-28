package com.firstman.project;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by USER on 01-07-2017.
 */

public class mentorloginapicall extends IntentService {

    OkHttpClient client = new OkHttpClient();
    public String webservice="http://192.168.43.62:82/project/login.php";
    public mentorloginapicall(){
        super("mentorlogin");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
    String strusername=intent.getStringExtra("username").trim();
        String strpassword=intent.getStringExtra("password").trim();
        String strmentorid=intent.getStringExtra("mentorid").trim();
      String response = SendDataByOkHttp(strusername,strpassword,strmentorid);
      // String response = SendDataToApi(strusername,strpassword,strmentorid);
        //try{JSONObject jobj =new JSONObject(response);}catch (Exception json){json.printStackTrace();}
        Log.e("response",response );
        sendResponse(response);

    }

    private String SendDataByOkHttp(String strusername, String strpassword, String strmentorid) {


        try {


                Request request = new Request.Builder()
                        .url(webservice + "?username="+strusername+"&password="+strpassword+"&mentor_id="+strmentorid)
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();



        }catch (IOException io){io.printStackTrace();}return null;}

    private String SendDataToApi(String strusername, String strpassword, String strmentorid) {
        try {
            URL url=new URL(webservice +"?username="+strusername+"&password="+strpassword+"&mentor_id="+strmentorid);
            HttpURLConnection httpconnection =(HttpURLConnection)url.openConnection();
            InputStreamReader inputstreamReader=new InputStreamReader(httpconnection.getInputStream());
            BufferedReader bufferedReader= new BufferedReader(inputstreamReader);
            StringBuilder stringBuilder=new StringBuilder();

            for( String line=bufferedReader.readLine();bufferedReader.readLine()!=null;stringBuilder.append(line))


            httpconnection.disconnect();
            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
private void sendResponse(String response){
    Intent intent =new Intent();
    intent.putExtra("response",response);
    intent.setAction("project.login");
    sendBroadcast(intent);
}

}



