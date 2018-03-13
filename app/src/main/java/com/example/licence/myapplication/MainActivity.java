package com.example.licence.myapplication;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button) findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendSMSMessage();
            }
        });



    }

    protected void sendSMSMessage(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }else{

            sendSms();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_SEND_SMS:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sendSms();
                }else{
                    Toast.makeText(getApplicationContext(), "sms pas envoyé", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    public void sendSms(){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("0763137101", null, "coucou", null,null);
        Toast.makeText(getApplicationContext(), "sms envoyé", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStart() {
        super.onStart();

        Log.i("LPM", "on start");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("LPM", "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LPM", "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LPM", "on restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LPM", "on resume");
    }
}
