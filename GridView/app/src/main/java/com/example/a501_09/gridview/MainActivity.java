package com.example.a501_09.gridview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridview_main);
        gridView.setAdapter(new ImageAdapter(MainActivity.this));
        gridView.setOnItemClickListener( new GridListener());
    }
    class GridListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this, (i+1)+" 번째 그림", Toast.LENGTH_SHORT).show();
        }
    }
    class ImageAdapter extends BaseAdapter {
        int[] picture = {R.drawable.icon,R.drawable.icon1,R.drawable.icon2};
        Context context;
        public ImageAdapter(Context context){
            this.context = context;
        }

        //표현할 자료의 개수
        @Override
        public int getCount() {
            return 100;
        }

        //그리드 안에 넣을 객체
        @Override
        public Object getItem(int i) {
            return picture[i%3];
        }

        //그리드의 순서 번호
        @Override
        public long getItemId(int i) {
            return i;
        }

        //그리드 뷰 안에 들어간 정보를 추가하는 함수
        //자료의 수만큼 임의로 반복하지 않아도 자료 수 만큼 알아서 반복됨
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView img_view;
            if(view == null){
                // 동적으로 뷰를 생성하므로 findviewbyid와는 다르게 생성
                img_view = new ImageView(context);
                img_view.setLayoutParams(new GridView.LayoutParams(300,300));
                img_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                img_view = (ImageView)view;
            }
            img_view.setImageResource(picture[i%3]);
            return img_view;
        }
    }
}
