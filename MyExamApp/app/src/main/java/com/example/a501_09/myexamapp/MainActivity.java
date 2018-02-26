package com.example.a501_09.myexamapp;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {//앱을 켜자마자 실행되는 함수
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //컴포넌트의 객체 생성
        Button btn_test = (Button)findViewById(R.id.button1);//레이아웃의 버튼이 기능할 수 있도록 코드의 버튼 생성
        Button btn_test02 = (Button)findViewById(R.id.button2);
        ImageView image01 = (ImageView)findViewById(R.id.image1);
        TextView txt01 = (TextView)findViewById(R.id.result);
        //리스너의 객체 생성
        MyListener01 myListener01 = new MyListener01();
        MyListener02 myListener02 = new MyListener02();
        MyListener03 myListener03 = new MyListener03();
        MyListener04 myListener04 = new MyListener04();
        //컴포넌트 객체에 리스너 등록
        btn_test.setOnClickListener(myListener01);
        btn_test02.setOnClickListener(myListener02);
        image01.setOnClickListener(myListener03);
        txt01.setOnClickListener(myListener04);
    }

    //버튼이 기능할 리스너 만듦
    class MyListener01 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            Toast.makeText(MainActivity.this,"버튼을 클릭하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    class MyListener02 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            Toast.makeText(MainActivity.this,"버튼2를 클릭하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    class MyListener03 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            Toast.makeText(MainActivity.this,"이미지뷰를 클릭하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    class MyListener04 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            Toast.makeText(MainActivity.this,"텍스트뷰를 클릭하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
