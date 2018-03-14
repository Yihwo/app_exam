package com.example.a501_09.listitempractice;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ItemFormat> arItem;
    ListView item_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //항목 객체를 모아놓은 ArrayList생성
        arItem = new ArrayList<ItemFormat>();

        //ArrayList에 자료 객체를 만들어어 입력
        arItem.add(new ItemFormat(R.drawable.aaa,R.drawable.bbb,
                "candy","hamburger",
                "12000원","14000원",
                3.5,4.0));
        arItem.add(new ItemFormat(R.drawable.aaa,R.drawable.bbb,
                "camera","fan",
                "860000원","32000원",
                4.5,3.0));
        arItem.add(new ItemFormat(R.drawable.aaa,R.drawable.bbb,
                "Ipad","ice cream",
                "789000원","11000원",
                5.0,4.5));

        item_list = (ListView)findViewById(R.id.item_list);
        item_list.setAdapter(new MyAdapter(MainActivity.this,arItem,R.layout.listview_item));
    }
}
