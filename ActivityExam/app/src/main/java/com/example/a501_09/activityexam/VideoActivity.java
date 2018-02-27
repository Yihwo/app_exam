package com.example.a501_09.activityexam;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.VideoView;

/**
 * Created by 501-09 on 2018-02-27.
 */

public class VideoActivity extends AppCompatActivity {
    VideoView bp_Video;
    RatingBar ratingBar;
    ImageView bp_Image;
    ImageButton back_btn;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        back_btn = (ImageButton)findViewById(R.id.imageBtn_Back);
        BackBtnListener backBtnListener = new BackBtnListener();
        back_btn.setOnClickListener(backBtnListener);

        bp_Image = (ImageView)findViewById(R.id.image_Bp);
        ImageListener imageListener = new ImageListener();
        bp_Image.setOnClickListener(imageListener);

        bp_Video = (VideoView)findViewById(R.id.bp_video);
        String urlId = "android.resource://"+getPackageName()+"/"+R.raw.soapbubble;
        Uri uri = Uri.parse(urlId);
        bp_Video.setVideoURI(uri);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar_bp);
        RatingBarListener ratingBarListener = new RatingBarListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarListener);


        final MediaController mediaController = new MediaController(this);

        bp_Video.setMediaController(mediaController);

    }
    class RatingBarListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            Toast.makeText(VideoActivity.this,Double.toString(v),Toast.LENGTH_LONG).show();
        }
    }
    class ImageListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(VideoActivity.this,"Black Panther",Toast.LENGTH_SHORT).show();
        }
    }
    class BackBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            finish();
        }
    }

}
