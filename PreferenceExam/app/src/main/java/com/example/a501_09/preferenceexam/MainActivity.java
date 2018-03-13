package com.example.a501_09.preferenceexam;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button save_btn,load_btn;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save_btn = (Button)findViewById(R.id.save_btn);
        load_btn = (Button)findViewById(R.id.load_btn);
        editText = (EditText)findViewById(R.id.editText);
        save_btn.setOnClickListener(new SaveLoadButtonListener());
        load_btn.setOnClickListener(new SaveLoadButtonListener());
    }
    class SaveLoadButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.save_btn:
                    //프레퍼런스 객체 생성
                    SharedPreferences pref = getSharedPreferences("file_name",0);
                    //파일을 쓰는데 도움을 주는 함수 Editer
                    SharedPreferences.Editor editor = pref.edit();
                    //저장할 데이터 준비
                    editor.putString("test_string","저장할 값");//키값-저장하는 값의 ID,value 저장할 값
                    editor.putString("test_string01","저장할 값2");
                    editor.putString("test_string02",editText.getText().toString());
                    editor.putInt("test_int", 100);
                    editor.commit();//입력한 값들을 최종적으로 저장시켜주는 함수
                    editText.setText("");
                    break;
                case R.id.load_btn:
                    //프레퍼런스 객체 생성
                    SharedPreferences pref_load = getSharedPreferences("file_name",0);
                    String temp1 = pref_load.getString("test_string02","default_string");
                    String temp2 = pref_load.getString("test_string01","default_string");
                    int temp3 = pref_load.getInt("test_int",-1);
                    editText.setText(temp1);

                    break;
            }
        }
    }
}
