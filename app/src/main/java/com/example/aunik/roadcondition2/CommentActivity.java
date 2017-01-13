package com.example.aunik.roadcondition2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.aunik.roadcondition2.apiUtils.RESTClient;
import com.example.aunik.roadcondition2.model.RoadSegment;
import com.example.aunik.roadcondition2.utils.ImageUploadUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends ActionBarActivity {

    RESTClient restClient;
    ImageUploadUtils imageUploadUtils;

    Button postBtn ;
    ImageButton imageButton;
    RatingBar ratingBar;
    EditText commentTxt;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        restClient = new RESTClient(getApplicationContext());
        imageUploadUtils = new ImageUploadUtils(this);


        ratingBar = (RatingBar) findViewById(R.id.conditionRatingBar);
        commentTxt = (EditText) findViewById(R.id.commentTxt);
        addListenerOnPostButton();
        addListernerOnImageBtn();

    }


    public void addListenerOnPostButton() {

        postBtn = (Button) findViewById(R.id.postBtn);

        //if click on me, then display the current rating value.
        postBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), Float.toString(ratingBar.getRating() ) + commentTxt.getText(),Toast.LENGTH_LONG ).show();
                List comments = new ArrayList<String>();
                if(commentTxt.getText() != null){
                    comments.add(commentTxt.getText().toString());
                }

                RoadSegment roadSegment = new RoadSegment("123", (int)ratingBar.getRating(), null, comments);
                restClient.sendSegmentData(roadSegment);
            }

        });

    }

    private  void addListernerOnImageBtn(){
        imageButton = (ImageButton) findViewById(R.id.imageBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");



        }
    }





    //imageUri = imageUploadUtils.openCamera();
}
