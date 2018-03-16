package com.example.a501_09.listviewexam;

import android.content.Intent;
import android.content.res.TypedArray;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-02-28.
 */

public class ExplainActivity extends AppCompatActivity {
    LinearLayout image_scroll;
    String[] movie_title;
    String[] movie_type;
    String[] movie_director;
    String[] movie_actor;

    TypedArray movie_img;
    TypedArray movie_mon_scroll;
    TypedArray movie_gh_scroll;
    TypedArray movie_bp_scroll;
    TypedArray movie_rs_scroll;

    TextView mv_title;
    TextView mv_type;
    TextView mv_director;
    TextView mv_actor;

    ImageButton back_btn;
    RatingBar ratingBar;
    ImageView mv_image;
    Button reserve_btn;

    int movie_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        image_scroll = (LinearLayout) findViewById(R.id.image_scroll);

      //  back_btn = (ImageButton) findViewById(R.id.back_btn);
        BackButtonListener backButtonListener = new BackButtonListener();
        //back_btn.setOnClickListener(backButtonListener);

        reserve_btn = (Button) findViewById(R.id.reservation_btn);
        ReserveButtonListener reserveButtonListener = new ReserveButtonListener();
        reserve_btn.setOnClickListener(reserveButtonListener);

        movie_title = getResources().getStringArray(R.array.movie_title);
        movie_type = getResources().getStringArray(R.array.movie_type);
        movie_director = getResources().getStringArray(R.array.movie_director);
        movie_actor = getResources().getStringArray(R.array.movie_actor);
        movie_img = getResources().obtainTypedArray(R.array.movie_img);//이미지 파일을 불러오기 위해 typed array사용
        movie_mon_scroll = getResources().obtainTypedArray(R.array.movie_mon_scroll);
        movie_gh_scroll = getResources().obtainTypedArray(R.array.movie_gh_scroll);
        movie_bp_scroll = getResources().obtainTypedArray(R.array.movie_bp_scroll);
        movie_rs_scroll = getResources().obtainTypedArray(R.array.movie_rs_scroll);

        mv_title = (TextView) findViewById(R.id.movie_title);
        mv_type = (TextView) findViewById(R.id.movie_type);
        mv_director = (TextView) findViewById(R.id.movie_director);
        mv_actor = (TextView) findViewById(R.id.movie_actor);
        mv_image = (ImageView) findViewById(R.id.movie_image);

        ratingBar = (RatingBar) findViewById(R.id.movieRating);
        RatingBarListener ratingBarListener = new RatingBarListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarListener);

        //3. 화면 전환 후 이전 엑티비티에서 보낸 인텐트를 수신
        Intent intent = getIntent();
        //4. 인텐트에서 데이터를 읽음
        movie_index = intent.getIntExtra("Movie_Index", -1);
        //데이터 수신 여부 확인
        if (movie_index != -1) {
            Toast.makeText(ExplainActivity.this, Integer.toString(movie_index), Toast.LENGTH_LONG).show();
            //Integer.toString(temp)//숫자 출력할때

            mv_title.setText(movie_title[movie_index]);
            mv_type.setText(movie_type[movie_index]);
            mv_director.setText(movie_director[movie_index]);
            mv_actor.setText(movie_actor[movie_index]);

            switch (movie_index) {
                case 0:
                    for(int i=0; i<movie_mon_scroll.length();i++){
                        ImageView movie_scroll_view = new ImageView(ExplainActivity.this);
                        movie_scroll_view.setImageResource(movie_mon_scroll.getResourceId(i, -1));
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
                case 1:
                    for(int i=0; i<movie_gh_scroll.length();i++){
                        ImageView movie_scroll_view = new ImageView(ExplainActivity.this);
                        movie_scroll_view.setImageResource(movie_gh_scroll.getResourceId(i, -1));
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
                case 2:
                    for(int i=0; i<movie_bp_scroll.length();i++){
                        ImageView movie_scroll_view = new ImageView(ExplainActivity.this);
                        movie_scroll_view.setImageResource(movie_bp_scroll.getResourceId(i, -1));
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
                case 3:
                    for(int i=0; i<movie_rs_scroll.length();i++){
                        ImageView movie_scroll_view = new ImageView(ExplainActivity.this);
                        movie_scroll_view.setImageResource(movie_rs_scroll.getResourceId(i, -1));
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
            }
            mv_image.setImageResource(movie_img.getResourceId(movie_index, -1)); // 이미지 뷰에 이미지를 설정
        }



    }

    class DataFormat {
        int img_Id;
        public DataFormat(int img_Id) {
            this.img_Id = img_Id;
        }
    }

    class ReserveButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(ExplainActivity.this, ReservationActivity.class);
            intent.putExtra("Movie_Index", movie_index);
            //인텐트를 넘겨주는 함수
            startActivity(intent);//requestCode는 순서를 확인하기 위한 장치
        }
    }

    class BackButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    class RatingBarListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            Toast.makeText(ExplainActivity.this, Double.toString(v), Toast.LENGTH_LONG).show();
        }
    }

}
