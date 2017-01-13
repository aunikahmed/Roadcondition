package com.example.aunik.roadcondition2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.aunik.roadcondition2.apiUtils.RESTClient;

public class MainActivity extends ActionBarActivity {

    private TextView currentX, currentY, currentZ;
    private Button viewConditonButton, contributeButton, commentBtn;
    public static  int t =0;

    RESTClient restClient;
    Uri imageUri;
    SharedPreferences preferences;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setContentView(R.layout.home);

        restClient = new RESTClient(getApplicationContext());

        viewConditonButton = (Button) findViewById(R.id.viewButton);
        contributeButton = (Button)findViewById(R.id.contributeButton);
        commentBtn = (Button) findViewById(R.id.uploadBtn);

        boolean serviceRunning  = preferences.getBoolean("service_running", false);
        if(!serviceRunning){
            contributeButton.setText("contribute");
        }else{
            contributeButton.setText("stop Contribution");
        }

        viewConditonButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent viewIntent = new Intent(getApplicationContext(), MapViewActivity.class);

                startActivity(viewIntent);
//                Intent serviceIntent=new Intent(getApplicationContext(),TimerService.class);
//                serviceIntent.setAction("start");
//                startService(serviceIntent);


            }

        });




        contributeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //toggle.toggle();

                boolean serviceRunning  = preferences.getBoolean("service_running", false);

                if ( !serviceRunning) {
                    showDialog();
                } else if (serviceRunning) {
                    stopService();
                    t = 0;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("service_running",false);
                    editor.apply();
                    contributeButton.setText("contribute");

                }
            }


        });



        commentBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivity(homeIntent);
            }
        });



    }


    void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("To improve the app we need your help." +
                " If you agree to contribute then sensor data will be collected using your device and" +
                " after finishing data collection it will be send to server. ").setCancelable(false)
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startService();
                        t = 1;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("service_running",true);
                        editor.apply();

                        contributeButton.setText("stop Contribution");
                    }
                })
                .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    void startService(){
        Intent serviceIntent=new Intent(getApplicationContext(),TimerService.class);
        serviceIntent.setAction("start");
        startService(serviceIntent);

    }

    void stopService(){
        Intent serviceIntent=new Intent(getApplicationContext(),TimerService.class);
        stopService(serviceIntent);

    }






}
