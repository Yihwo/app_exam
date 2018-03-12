package com.example.a501_09.fileexam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Button btn_input,btn_output,btn_delete;
    EditText edittxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_input = (Button)findViewById(R.id.btn_input);
        btn_output = (Button)findViewById(R.id.btn_output);

        edittxt = (EditText)findViewById(R.id.editText);
        btn_input.setOnClickListener(new ButtonClickedListener());
        btn_output.setOnClickListener(new ButtonClickedListener());
    }
    class ButtonClickedListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_output:
                    //output stream 자료를 파일로 보내기
                    //outputstream생성
                    FileOutputStream fos = null;
                    try{
                        //자료를 넣을 파일 생성
                        fos = openFileOutput("test.txt", Context.MODE_PRIVATE);//저장할 이름과 접근 권한 설정
                        //자료를 빈 파일에 저장
                        String str_temp = "파일을 저장하는 예제";
                        //파일에 자료를 저장 (Byte형태로 변환 후)
                        fos.write(str_temp.getBytes());//파일에 저장할때 write사용
                        //저장후 반드시 닫기
                        fos.close();
                    }
                    catch(Exception e){
                        Log.d("file stream","output stream error");
                    }
                    break;
                case R.id.btn_input:
                    //input stream 파일을 자료로 보내기
                    //Inputstream 생성
                    FileInputStream fis = null;
                    //자료를 저장할 공간 만들기
                    byte[] data = null;//자료를 저장하기 위해서 byte형태로 만들어야 함
                    try{
                        fis = openFileInput("test.txt");//자료로 변환할 파일을 받아옴
                        //fis의 파일크기와 같은 크기의 byte배열을 생성
                        data = new byte[fis.available()];//available 파일의 바이트 수를 알려주는 함수
                        //data배열에 파일을 입력(byte형태)
                        while(fis.read(data) != -1 ) {;}//read 함수로 파일을 읽어와 data배열에 넣음
                        //inputstream은 꼭 닫아주어야한다.
                        //닫지 않으면 프로그램 종료될때까지 계속 실행되기때문에
                        fis.close();
                        //byte 형태를 문자열(String)로 변환
                        String temp = new String(data);
                        edittxt.setText(temp);
                    }
                    catch (Exception e){
                        Log.d("file stream","input stream error");
                    }
                    break;
            }
        }
    }
}
