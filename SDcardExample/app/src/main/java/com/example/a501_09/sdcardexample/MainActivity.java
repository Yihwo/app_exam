package com.example.a501_09.sdcardexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txt_fileName;
    Button btn_find_music, btn_find_image;
    ImageView img_file_view;
    ArrayList<String> img_array, music_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_fileName = (TextView) findViewById(R.id.txt_fileName);
        btn_find_image = (Button) findViewById(R.id.btn_find_image);
        btn_find_music = (Button) findViewById(R.id.btn_find_music);
        img_file_view = (ImageView) findViewById(R.id.img_file);

        ButtonListener buttonListener = new ButtonListener();

        btn_find_music.setOnClickListener(buttonListener);
        btn_find_image.setOnClickListener(buttonListener);

//        //sd card가 제대로 있는지 확인
//        String state = Environment.getExternalStorageState();
//        if(state.equals(Environment.MEDIA_MOUNTED)){
//            //sd card의 경로
//            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//            //원하는 파일만 추출
//            FilenameFilter img_filter = new FilenameFilter() {
//                @Override
//                public boolean accept(File file, String s) {
//                    //.jpg 로 끝나는 파일 찾기
//                    return s.endsWith(".jpg");
//                }
//            };
//            FilenameFilter music_filter = new FilenameFilter() {
//                @Override
//                public boolean accept(File file, String s) {
//                    //.jpg 로 끝나는 파일 찾기
//                    return s.endsWith(".mp3");
//                }
//            };
//            File sdRoot = new File(sdPath);
//            //sdRoot 안에서 필터가 적용된 파일만 입력됨
//            String[] jpg_list = sdRoot.list(img_filter);
//            String[] mp3_list = sdRoot.list(music_filter);
//            String temp_img = "";
//            String temp_music = "";
//
//            for(int i=0;i<jpg_list.length;i++){
//                temp_img += jpg_list[i]+"\n";
//                txt_fileName.setText(temp_img);
//            }
//            for(int j=0;j<mp3_list.length;j++){
//                temp_music += mp3_list[j]+"\n";
//                txt_fileName.setText(temp_music);
//            }
//        }else{
//            Toast.makeText(this, "sd card의 인식이 불가합니다.", Toast.LENGTH_SHORT).show();
//        }
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_find_image:
                    img_array = FindFileNameExtend("jpg");
                    txt_fileName.setText("");
                                        for (int i = 0; i < img_array.size(); i++) {
                        String temp = txt_fileName.getText().toString();
                        txt_fileName.setText(temp + img_array.get(i) + "\n");
                    }
                    //이미지의 크기 조정
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize=2;//이미지를 1/n 크기로 줄여줌

                    File img_file = new File(img_array.get(0));
                    //파일을 그림 형식으로 변환하기 위해 bitmap 객체로 생성
                    Bitmap bitmap = BitmapFactory.decodeFile(img_file.getAbsolutePath(),options);
                    //비트맵 객체를 원하는 크기로 조정
                    Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap,40,20,true);
                    //이미지 뷰에서 bitmap에 저장된 파일을 출력
                    img_file_view.setImageBitmap(bitmap_resize);
                    break;
                case R.id.btn_find_music:
                    music_array = FindFileNameExtend("mp3");
                    txt_fileName.setText("");
                    for (int i = 0; i < music_array.size(); i++) {
                        String temp = txt_fileName.getText().toString();
                        txt_fileName.setText(temp + music_array.get(i) + "\n");
                    }
                    break;
            }
        }
    }
    private ArrayList<String> FindFileNameExtend(String ext) {
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
                txt_fileName.setText(temp_file);

                //파일의 경로 와 파일명
                temp.add(sdPath+"/"+file_list[i]);
            }
            return temp;
        } else {
            Toast.makeText(this, "sd card의 인식이 불가합니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
