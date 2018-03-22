package com.example.a501_09.listviewexam;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-02-28.
 */

public class ExplainActivity extends AppCompatActivity {

    ArrayList<String> movie_sdcard_img;

    LinearLayout image_scroll;
    String[] movie_title;
    String[] movie_type;
    String[] movie_director;
    String[] movie_actor;
    String[] movie_photo_pack;

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
        //180319
        movie_photo_pack = getResources().getStringArray(R.array.photo_pack);

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

            ImageView movie_scroll_view;
            String img_pack_str;
            String[] split_img_pack;
            switch (movie_index) {
                case 0:
                    movie_scroll_view = new ImageView(ExplainActivity.this);
                    //이미지 이름 불러오기
//                    img_pack_str = movie_photo_pack[movie_index];
//                    split_img_pack = img_pack_str.split("/");

//                    for (int j = 0; j < split_img_pack.length; j++) {
//                        //이미지 이름으로 이미지 아이디 찾기
//                        String file_name = "@drawable/" + split_img_pack[j];
//                        int img_id = getResources().getIdentifier(file_name, "drawable", this.getPackageName());

                    movie_sdcard_img = FindFileNameExtend("bp_");
//                    txt_fileName.setText("");
                    BitmapFactory.Options options0 = new BitmapFactory.Options();
                    options0.inSampleSize = 4;//이미지를 1/n 크기로 줄여줌
                    Bitmap bitmap_0;
                    for (int i = 0; i < movie_sdcard_img.size(); i++) {
                        File img_file = new File(movie_sdcard_img.get(i));
                        //파일을 그림 형식으로 변환하기 위해 bitmap 객체로 생성
                        bitmap_0 = BitmapFactory.decodeFile(img_file.getAbsolutePath(), options0);
                        //비트맵 객체를 원하는 크기로 조정
                        Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap_0, 40, 20, true);
                        //이미지 적용하기
                        movie_scroll_view.setImageBitmap(bitmap_resize);
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(300 ,200));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }

                    //이미지 적용하기
//                        movie_scroll_view.setImageResource(img_id);
//                        //img.setImageResource(arrayList.get(i).img_id);
//                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
//                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
//                        image_scroll.addView(movie_scroll_view);

                    break;
                case 1:
                    movie_scroll_view = new ImageView(ExplainActivity.this);
                    //이미지 이름 불러오기
//                    img_pack_str = movie_photo_pack[movie_index];
//                    split_img_pack = img_pack_str.split("/");

//                    for (int j = 0; j < split_img_pack.length; j++) {
//                        //이미지 이름으로 이미지 아이디 찾기
//                        String file_name = "@drawable/" + split_img_pack[j];
//                        int img_id = getResources().getIdentifier(file_name, "drawable", this.getPackageName());

                    movie_sdcard_img = FindFileNameExtend("bp_");
//                    txt_fileName.setText("");
                    BitmapFactory.Options options1 = new BitmapFactory.Options();
                    options1.inSampleSize = 4;//이미지를 1/n 크기로 줄여줌
                    Bitmap bitmap_1;

                    for (int i = 0; i < movie_sdcard_img.size(); i++) {
                        File img_file = new File(movie_sdcard_img.get(i));
                        //파일을 그림 형식으로 변환하기 위해 bitmap 객체로 생성
                        bitmap_1 = BitmapFactory.decodeFile(img_file.getAbsolutePath(), options1);
                        //비트맵 객체를 원하는 크기로 조정
                        Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap_1, 40, 20, true);
                        //이미지 적용하기
                        movie_scroll_view.setImageBitmap(bitmap_resize);
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
                case 2:
                    movie_scroll_view = new ImageView(ExplainActivity.this);
                    //이미지 이름 불러오기
//                    img_pack_str = movie_photo_pack[movie_index];
//                    split_img_pack = img_pack_str.split("/");

//                    for (int j = 0; j < split_img_pack.length; j++) {
//                        //이미지 이름으로 이미지 아이디 찾기
//                        String file_name = "@drawable/" + split_img_pack[j];
//                        int img_id = getResources().getIdentifier(file_name, "drawable", this.getPackageName());

                    movie_sdcard_img = FindFileNameExtend("bp_");
//                    txt_fileName.setText("");
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inSampleSize = 4;//이미지를 1/n 크기로 줄여줌
                    Bitmap bitmap_2;
                    for (int i = 0; i < movie_sdcard_img.size(); i++) {
                        File img_file = new File(movie_sdcard_img.get(i));
                        //파일을 그림 형식으로 변환하기 위해 bitmap 객체로 생성
                        bitmap_2 = BitmapFactory.decodeFile(img_file.getAbsolutePath(), options2);
                        //비트맵 객체를 원하는 크기로 조정
                        Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap_2, 40, 20, true);
                        //이미지 적용하기
                        movie_scroll_view.setImageBitmap(bitmap_resize);
                        //img.setImageResource(arrayList.get(i).img_id);
                        movie_scroll_view.setLayoutParams(new LinearLayout.LayoutParams(200, 300));//레이아웃 각각의 크기 설정
                        movie_scroll_view.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                        image_scroll.addView(movie_scroll_view);
                    }
                    break;
                case 3:
                    movie_scroll_view = new ImageView(ExplainActivity.this);
                    //이미지 이름 불러오기
//                    img_pack_str = movie_photo_pack[movie_index];
//                    split_img_pack = img_pack_str.split("/");

//                    for (int j = 0; j < split_img_pack.length; j++) {
//                        //이미지 이름으로 이미지 아이디 찾기
//                        String file_name = "@drawable/" + split_img_pack[j];
//                        int img_id = getResources().getIdentifier(file_name, "drawable", this.getPackageName());

                    movie_sdcard_img = FindFileNameExtend("bp_");
//                    txt_fileName.setText("");
                    BitmapFactory.Options options3 = new BitmapFactory.Options();
                    options3.inSampleSize = 4;//이미지를 1/n 크기로 줄여줌
                    Bitmap bitmap_3;
                    for (int i = 0; i < movie_sdcard_img.size(); i++) {
                        File img_file = new File(movie_sdcard_img.get(i));
                        //파일을 그림 형식으로 변환하기 위해 bitmap 객체로 생성
                        bitmap_3 = BitmapFactory.decodeFile(img_file.getAbsolutePath(), options3);
                        //비트맵 객체를 원하는 크기로 조정
                        Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap_3, 40, 20, true);
                        //이미지 적용하기
                        movie_scroll_view.setImageBitmap(bitmap_resize);
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

        private ArrayList<String> FindFileNameExtend (String ext){
            ArrayList<String> temp = new ArrayList<String>();
            final String file_ext = ext;
            //sd card가 제대로 있는지 확인
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                //sd card의 경로
                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                //원하는 파일만 추출
                FilenameFilter file_filter = new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        //.jpg 로 끝나는 파일 찾기
                        return s.endsWith(file_ext);
                    }
                };
                File sdRoot = new File(sdPath);
                //sdRoot 안에서 필터가 적용된 파일만 입력됨
                String[] file_list = sdRoot.list(file_filter);
                String temp_file = "";

                for (int i = 0; i < file_list.length; i++) {
                    temp_file += file_list[i] + "\n";

                    //파일의 경로 와 파일명
                    temp.add(sdPath + "/" + file_list[i]);
                }
                return temp;
            } else {
                Toast.makeText(this, "sd card의 인식이 불가합니다.", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }
