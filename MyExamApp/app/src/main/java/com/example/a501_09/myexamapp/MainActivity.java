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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText num01;
    EditText num02;
    TextView txt_result;
    Button btn_add;
    Button btn_min;
    Button btn_mul;
    Button btn_div;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//앱을 켜자마자 실행되는 함수
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //컴포넌트의 객체 생성
        num01 = (EditText)findViewById(R.id.num1);
        num02 = (EditText)findViewById(R.id.num2);
        txt_result = (TextView)findViewById(R.id.result);
        btn_add = (Button)findViewById(R.id.add);//레이아웃의 버튼이 기능할 수 있도록 코드의 버튼 생성
        btn_min = (Button)findViewById(R.id.min);
        btn_mul = (Button)findViewById(R.id.mul);
        btn_div = (Button)findViewById(R.id.div);
        ImageView image01 = (ImageView)findViewById(R.id.image1);
        //리스너의 객체 생성
        MyListener01 myListener01 = new MyListener01();
        MyListener02 myListener02 = new MyListener02();
        MyListener03 myListener03 = new MyListener03();
        MyListener04 myListener04 = new MyListener04();
        //컴포넌트 객체에 리스너 등록
        btn_add.setOnClickListener(myListener01);
        btn_min.setOnClickListener(myListener02);
        btn_mul.setOnClickListener(myListener03);
        btn_div.setOnClickListener(myListener04);

        //리스너 생성, 리스너 객체 생성, 컴포넌트 객체와 리스너 연결을 한번에
        image01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"이미지뷰를 클릭하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //버튼이 기능할 리스너 만듦
    class MyListener01 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            //Toast.makeText(MainActivity.this,"버튼을 클릭하였습니다.",Toast.LENGTH_SHORT).show();
            String input01 = num01.getText().toString();//input01 에 num01 값을 넣는 동작
            String input02 = num02.getText().toString();
            //Integer.parseInt(input01) input01의 값을 int로 바꿔줌
            int result = Integer.parseInt(input01)+Integer.parseInt(input02);
            txt_result.setText(Integer.toString(result));//setText는 텍스트를 출력하는 함수이므로 int를 출력할때 String로 바꿔줘야함
        }
    }
    class MyListener02 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            String input01 = num01.getText().toString();
            String input02 = num02.getText().toString();

            int result = Integer.parseInt(input01)-Integer.parseInt(input02);
            txt_result.setText(Integer.toString(result));
        }
    }
    class MyListener03 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            String input01 = num01.getText().toString();
            String input02 = num02.getText().toString();

            int result = Integer.parseInt(input01)*Integer.parseInt(input02);
            txt_result.setText(Integer.toString(result));
        }
    }
    class MyListener04 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            String input01 = num01.getText().toString();
            String input02 = num02.getText().toString();
            double result = Double.parseDouble(input01)/Double.parseDouble(input02);
            txt_result.setText(Double.toString(result));
        }
    }

    //비슷한 류의 리스너 들을 하나로 출력
    class MyListener05 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //텍스트 출력
            String input01 = num01.getText().toString();
            String input02 = num02.getText().toString();

            switch(view.getId()){
                case R.id.add:
                    int result = Integer.parseInt(input01)+Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.min:
                    result = Integer.parseInt(input01)-Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.mul:
                    result = Integer.parseInt(input01)*Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.div:
                    double result1 = Double.parseDouble(input01)/Double.parseDouble(input02);
                    txt_result.setText(Double.toString(result1));
                    break;
            }
            double result = Double.parseDouble(input01)/Double.parseDouble(input02);
            txt_result.setText(Double.toString(result));
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
