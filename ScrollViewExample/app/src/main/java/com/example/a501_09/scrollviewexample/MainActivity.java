package com.example.a501_09.scrollviewexample;

import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 컴포넌트 객체 생성
        linearLayout_main = (LinearLayout)findViewById(R.id.linearLayout_main);
        //라이너 레이아웃에 추가할 자료들 준비하기
        Integer[] img_id = {R.drawable.aaa, R.drawable.bbb, R.drawable.hhh, R.drawable.ggg, R.drawable.eee, R.drawable.fff};

        //화면의 크기 구하기
        //이미지의 크기를 디스플레이에 맞추어 출력할때
        //화면에 꽉 차게 출력
        Display display = getWindowManager().getDefaultDisplay();//디바이스 화면정보를 가지고 있는 디스플레이 객체를 가져옴
        Point point = new Point();//화면 끝의 좌표를 가져오기 위한 포인트 변수 생성
        display.getSize(point);//화면의 좌표 정보를 가져와 포인트 변수에 입력

        int width = point.x;//포인트 객체에 저장된 값을 가져옴
        int height = point.y;

        //동적으로 뷰 생성
        for(int num_img=0; num_img < img_id.length; num_img++){// 배열의 갯수만큼 이미지 뷰 생성
            ImageView temp = new ImageView(MainActivity.this);
            temp.setImageResource(img_id[num_img]);
            temp.setLayoutParams(new LinearLayout.LayoutParams(width,height));//레이아웃 각각의 크기 설정
            temp.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌

            linearLayout_main.addView(temp);//라이너 레이아웃에 이미지 뷰 생성
        }
    }
}
