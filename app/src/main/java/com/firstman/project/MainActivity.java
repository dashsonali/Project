package com.firstman.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);



    }
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.mentor:
                Intent it=new Intent(MainActivity.this,Main2Activity.class);
                startActivity( it);
                break;
            case R.id.student:
                Intent it1=new Intent(MainActivity.this,Main3Activity.class);
                startActivity( it1);
                break;

            default:
                break;
        }

    }}


