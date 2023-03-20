package com.example.l317_broadcasts_part3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);

//        String Action = "android.intent.action.HEADSET_PLUG";

//        IntentFilter filter = new IntentFilter(Action);



        Boot_Receiver receiver= new Boot_Receiver();

        IntentFilter filter = new IntentFilter();


        String action_HeadSet = "android.intent.action.HEADSET_PLUG";
        String action_restart = "android.intent.action.BOOT_COMPLETED";

        filter.addAction(action_HeadSet);
        filter.addAction(action_restart);

        registerReceiver(receiver, filter);


    } // on create Method


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        unregisterReceiver(receiver);

    } // on Destroy Method
} // MainActivity Class