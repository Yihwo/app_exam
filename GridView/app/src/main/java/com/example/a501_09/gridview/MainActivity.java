package com.example.a501_09.gridview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView)findViewById(R.id.gridview_main);
        gridView.setAdapter(new ImageAdapter(MainActivity.this));
    }
    class ImageAdapter extends BaseAdapter {
        int[] picture = {R.drawable.icon,R.drawable.icon1,R.drawable.icon2};
        Context context;
        public ImageAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int i) {
            return picture[i%3];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView img_view;
            if(view == null){
                // 동적으로 뷰를 생성하므로 findbyid와는 다르게 생성
                img_view = new ImageView(context);
                img_view.setLayoutParams(new GridView.LayoutParams(50,50));
                img_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }else{
                img_view = (ImageView)view;
            }
            img_view.setImageResource(picture[i%3]);
            return img_view;
        }
    }
}
