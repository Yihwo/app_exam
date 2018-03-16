
package com.example.a501_09.hscrollviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout_main;
    ArrayList<DataFormat> arrayList;
    final int DATA_TYPE_IS_STRING = 0;
    final int DATA_TYPE_IS_IMG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //뷰에 표시될 자료 준비
        linearLayout_main = (LinearLayout)findViewById(R.id.linearLayout_main);
        arrayList = new ArrayList<DataFormat>();
        arrayList.add(new DataFormat("myString1"));
        arrayList.add(new DataFormat(R.drawable.bbb));
        arrayList.add(new DataFormat(R.drawable.ccc,"myString2"));
        arrayList.add(new DataFormat(R.drawable.image,"myString3"));
        arrayList.add(new DataFormat(R.drawable.ddd));
        arrayList.add(new DataFormat("myString4"));

        //동적으로 컴포넌트 생성하여 라이너레이아웃에 추가하기기
       for(int i=0;i<arrayList.size();i++){
           DataFormat temp = arrayList.get(i);
            //switch(arrayList.get(i).type){
           switch(temp.type){
               case DATA_TYPE_IS_STRING:
               //case 0:
                    TextView text = new TextView(MainActivity.this);
                    text.setText(temp.text);
                    //text.setText(arrayList.get(i).text);
                    linearLayout_main.addView(text);
                    break;
               case DATA_TYPE_IS_IMG:
               //case 1:
                    ImageView img = new ImageView(MainActivity.this);
                    img.setImageResource(temp.img_id);
                    //img.setImageResource(arrayList.get(i).img_id);
                    img.setLayoutParams(new LinearLayout.LayoutParams(200,300));//레이아웃 각각의 크기 설정
                    img.setScaleType(ImageView.ScaleType.FIT_XY);//이미지 사이즈를 이미지 뷰에 맞게 맞추어줌
                    linearLayout_main.addView(img);
                    break;
                case 2:
                    break;
            }
        }

        linearLayout_main = (LinearLayout)findViewById(R.id.linearLayout_main);

    }
    //자료를 저장할 객체의 클래스
    class DataFormat{
        int type;
        int img_id;
        String text;
        public  DataFormat(String text){
            type=0;
            this.text = text;
            this.img_id = -1;
        }
        public DataFormat(int img_id){
            type=1;
            this.img_id = img_id;
            this.text=null;
        }
        public DataFormat(int img_id,String text){
            type=2;
            this.img_id = img_id;
            this.text = text;
        }
    }
}
