package com.example.aunik.roadcondition2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.aunik.roadcondition2.apiUtils.RESTClient;
import com.example.aunik.roadcondition2.utils.ImageUploadUtils;

import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private TextView currentX, currentY, currentZ;
    private Button viewConditonButton, contributeButton, uploadImageBtn;
    public static  int t =0;
    ImageUploadUtils imageUploadUtils;
    RESTClient restClient;
    Uri imageUri;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.home);
        imageUploadUtils = new ImageUploadUtils(this);
        restClient = new RESTClient(getApplicationContext());

        viewConditonButton = (Button) findViewById(R.id.viewButton);
        contributeButton = (Button)findViewById(R.id.contributeButton);
        uploadImageBtn = (Button) findViewById(R.id.uploadBtn);

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
                if ( t == 0) {
                    showDialog();
                } else if (t ==1) {
                    stopService();
                    t = 0;
                    contributeButton.setText("contribute");

                }
            }
        });



        uploadImageBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageUri = imageUploadUtils.openCamera();
            }
        });



    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if(imageUri != null){
                //upload image
               // restClient.uploadImage(imageUri);
            }
            //capturedImage.setImageBitmap(imageBitmap);
        }
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
