package com.example.a501_09.newlistexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item_dataFormet> arrayList;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터를 넣을 공간 만들기
        arrayList = new ArrayList<Item_dataFormet>();
        arrayList.add(new Item_dataFormet(R.drawable.image,"갤럭시","삼성"));
        arrayList.add(new Item_dataFormet(R.drawable.image,"아이폰","애플"));
        arrayList.add(new Item_dataFormet(R.drawable.image,"V30","LG"));

        listView = (ListView)findViewById(R.id.listview_main);
        listView.setAdapter(new MyItemAdapter(arrayList,this,R.layout.listview_item));
    }

    //항목에 표현할 자료들을 저장하는 객체의 클래스
    class Item_dataFormet{
        int img;
        String title;
        String sub_title;

        public Item_dataFormet(int img, String title, String sub_title) {
            this.img = img;
            this.title = title;
            this.sub_title = sub_title;
        }
    }
}
