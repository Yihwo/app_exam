package com.example.a501_09.spinnerexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] fruits;
    Spinner spin_fruits;
    ArrayAdapter adapter;
    boolean isInitSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_fruits = (Spinner)findViewById(R.id.spinner_Fruits);
        fruits = getResources().getStringArray(R.array.fruits);

        //어뎁터 생성
        adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.fruits,
                android.R.layout.simple_spinner_item);//스피너의 항목득
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//스피너의 틀
        spin_fruits.setAdapter(adapter);//어댑터 연결

        SpinnerFruitsListener spinnerFruitsListener = new SpinnerFruitsListener();
        spin_fruits.setOnItemSelectedListener(spinnerFruitsListener);

        isInitSpinner = false;
    }

    //스피너의 리스너
    class SpinnerFruitsListener implements AdapterView.OnItemSelectedListener{
        //항목이 선택된 경우
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(!isInitSpinner){ //boolean값이 거짓인경우 !false이므로 참이되고 boolean값을 참으로 바꾸어줌
                                //참인 경우는 !true이므로 거짓이 되어 else로 넘어감
                isInitSpinner = true;
            }else{
                Toast.makeText(MainActivity.this,fruits[i],Toast.LENGTH_SHORT).show();
            }
        }
        //항목이 선택되지 않은 경우
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
