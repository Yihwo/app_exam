package com.example.a501_09.listviewexam;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView_01;
    ArrayList<MovieItemFormet> movieItem;
    Button sign_btn;
    Toolbar movie_main_toolbar;

    //리스트 뷰의 항목에 대한 설명
    //String[] list_ex = {"조선시대의 장군","고구려 시대의 왕","조선시대 화가이자 문인","조선시대의 왕"};

//    String[] file_explain;
//    String[] file_great;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.login_select_item:
                Toast.makeText(MainActivity.this, "log in page", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.signin_select_item:
                Intent intent;
                intent= new Intent(MainActivity.this,SignInActivity.class);
                //인텐트를 넘겨주는 함수
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    String[] movie_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie_main_toolbar = (Toolbar)findViewById(R.id.movie_main_toolbar);
        setSupportActionBar(movie_main_toolbar);

        String[] movie_title = getResources().getStringArray(R.array.movie_title);
        String[] movie_type = getResources().getStringArray(R.array.movie_type);
        TypedArray movie_img = getResources().obtainTypedArray(R.array.movie_img);

//        sign_btn = (Button)findViewById(R.id.signIn_btn);
//        sign_btn.setOnClickListener(new SignInButtonListener());

        movieItem = new ArrayList<MovieItemFormet>();
        for(int i = 0;i<movie_title.length;i++) {
            movieItem.add(new MovieItemFormet(movie_img.getResourceId(i,-1), movie_title[i], movie_type[i]));
        }

        listView_01 = (ListView)findViewById(R.id.listView_01);
        listView_01.setAdapter(new MovieItemAdapter(MainActivity.this,movieItem,R.layout.movielist_item));

        listView_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                intent= new Intent(MainActivity.this,ExplainActivity.class);
                intent.putExtra("Movie_Index",i);

                //인텐트를 넘겨주는 함수
                startActivityForResult(intent,1);//requestCode는 순서를 확인하기 위한 장치
            }
        });
//        movie_title = getResources().getStringArray(R.array.movie_title);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(
//                MainActivity.this,
//                R.array.movie_title,
//                android.R.layout.simple_list_item_1);
//        listView_01 = (ListView)findViewById(R.id.listView_01);
//        listView_01.setAdapter(adapter);
//        ListViewListener listViewListener = new ListViewListener();
//        listView_01.setOnItemClickListener(listViewListener);


        /////////////////////////////////리스트뷰 실습//////////////////////////////////////////
//        //데이터 생성
//        file_explain = getResources().getStringArray(R.array.greatman_explain);
//        file_great = getResources().getStringArray(R.array.great_man);
//
//        //데이터 생성하기
//        //String[] list = {"이순신","광개토대왕","신사임당","세종대왕"};
//
//        //Adapter 만들기 현재 페이지에서 배열로 데이터 생성 했을때
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
////                        MainActivity.this, //배치할 엑티비티
////                        android.R.layout.simple_list_item_1,//적용할 스타일
////                        list);//적용할 리스트
//
//        //데이터를 불러 올 때 Adapter 만들기
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(
//                MainActivity.this,              //목록이 출력되는 페이지.
//                R.array.great_man,                      //항목 안에 들어갈 데이터
//                android.R.layout.simple_list_item_1     //적용할 스타일
//        );//리소스로부터 만들어짐
//        listView_01 = (ListView)findViewById(R.id.listView_01);
//
//        //리스트 뷰에 어댑터 설정하기
//        listView_01.setAdapter(adapter);
//        //리스너 생성
//        ListViewListener listViewListener = new ListViewListener();
//        //리스트 뷰에 리스너 등록
//        listView_01.setOnItemClickListener(listViewListener);
//
//        //리스너 생성, 리스트 뷰에 리스너 등록 축약하기
//        listView_01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,//출력할 페이지
//                        file_explain[i], //출력할 메시지
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_main_menu,menu);
        return true;
    }

    //리스트 뷰 리스너 만들기
    class ListViewListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//int는 눌려진 리스트의 index 번호

            Intent intent;
            intent= new Intent(MainActivity.this,ExplainActivity.class);
            intent.putExtra("Movie_Index",i);

            //인텐트를 넘겨주는 함수
            startActivityForResult(intent,1);//requestCode는 순서를 확인하기 위한 장치

//            Toast.makeText(MainActivity.this,//출력할 페이지
//                    movie_title[i], //출력할 메시지
//                    Toast.LENGTH_SHORT).show();
        }
    }
//    class  SignInButtonListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            Intent intent;
//            intent= new Intent(MainActivity.this,SignInActivity.class);
//            //인텐트를 넘겨주는 함수
//            startActivity(intent);
//        }
//    }
}
