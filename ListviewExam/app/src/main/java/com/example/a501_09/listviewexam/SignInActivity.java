package com.example.a501_09.listviewexam;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 501-09 on 2018-03-13.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SignInActivity extends AppCompatActivity {
    EditText edit_id,edit_pw,edit_repw,edit_name,edit_year,edit_cell;
    CheckBox checkBox_male,checkBox_female;
    Spinner spinner_month,spinner_day;
    //ArrayAdapter adapter_month,adapter_day;
    String[] string_month,string_day;
    boolean isInitSpinner = false, check_id = false;
    ArrayAdapter<String> adapter_month,adapter_day;
    TextView tv_info;
    Button submit_btn,file_check,pref_btn;

    int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        file_check = (Button)findViewById(R.id.check_file);
        pref_btn = (Button)findViewById(R.id.pref_btn);
        file_check.setOnClickListener(new FileCheckListener());
        pref_btn.setOnClickListener(new FileCheckListener());

        //int i,j;
        tv_info = (TextView)findViewById(R.id.tv_Info);

        edit_cell = (EditText)findViewById(R.id.edttxt_cell);
        edit_id = (EditText)findViewById(R.id.edittxt_id);
        edit_name = (EditText)findViewById(R.id.edittxt_name);
        edit_pw = (EditText) findViewById(R.id.edittxt_pw);
        edit_repw = (EditText)findViewById(R.id.edittxt_repw);
        edit_year = (EditText)findViewById(R.id.edittxt_year);

        checkBox_female = (CheckBox)findViewById(R.id.checkBox_female);
        checkBox_male = (CheckBox)findViewById(R.id.checkBox_male);

        spinner_day = (Spinner)findViewById(R.id.spinner_day);
        spinner_month = (Spinner)findViewById(R.id.spinner_month);
        string_day = new String[31];
        string_month = new String[12];

        submit_btn = (Button)findViewById(R.id.submitBtn);
        submit_btn.setOnClickListener(new SubmitButtonListener());

        IdCheckedListener idCheckedListener = new IdCheckedListener();
        edit_id.setOnFocusChangeListener(idCheckedListener);
        PwCheckedListener pwCheckedListener = new PwCheckedListener();
        edit_pw.setOnFocusChangeListener(pwCheckedListener);
        edit_repw.setOnFocusChangeListener(pwCheckedListener);

        for(i=0;i<=11;i++){
            string_month[i] = Integer.toString(i+1);
        }
        for (j=0;j<=30;j++){
            string_day[j] = Integer.toString(j+1);
        }

        adapter_month = new ArrayAdapter<String>(
                SignInActivity.this,
                android.R.layout.simple_spinner_item,
                string_month);//적용할 리스트
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//스피너의 틀
        spinner_month.setAdapter(adapter_month);

        adapter_day = new ArrayAdapter<String>(
                SignInActivity.this, //배치할 엑티비티
                android.R.layout.simple_spinner_item,//적용할 스타일
                string_day);//적용할 리스트
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//스피너의 틀
        spinner_day.setAdapter(adapter_day);

        SpinnerBirthListener spinnerBirthListener = new SpinnerBirthListener();
        spinner_month.setOnItemSelectedListener(spinnerBirthListener);
        spinner_day.setOnItemSelectedListener(spinnerBirthListener);

        GenderCheckBoxListener genderCheckBoxListener = new GenderCheckBoxListener();
        checkBox_male.setOnCheckedChangeListener(genderCheckBoxListener);
        checkBox_female.setOnCheckedChangeListener(genderCheckBoxListener);

    }
    class FileCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.check_file:
                    //input stream 파일을 자료로 보내기
                    //Inputstream 생성
                    FileInputStream fis = null;
                    //자료를 저장할 공간 만들기
                    byte[] data = null;//자료를 저장하기 위해서 byte형태로 만들어야 함
                    try {
                        fis = openFileInput("user_id.bin");//자료로 변환할 파일을 받아옴
                        //fis의 파일크기와 같은 크기의 byte배열을 생성
                        data = new byte[fis.available()];//available 파일의 바이트 수를 알려주는 함수
                        //data배열에 파일을 입력(byte형태)
                        while (fis.read(data) != -1) {
                            ;
                        }//read 함수로 파일을 읽어와 data배열에 넣음
                        //inputstream은 꼭 닫아주어야한다.
                        //닫지 않으면 프로그램 종료될때까지 계속 실행되기때문에
                        fis.close();
                        //byte 형태를 문자열(String)로 변환
                        String temp = new String(data);
                        Toast.makeText(SignInActivity.this, temp, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("file stream", "input stream error");
                    }
                    break;
                case R.id.pref_btn:
                    SharedPreferences pref_load = getSharedPreferences("user_Info",0);

                    String user_name = pref_load.getString("user_name_01","default string");
                    String user_cell = pref_load.getString("user_cell_01","default string");

                    Toast.makeText(SignInActivity.this, user_name+"/"+user_cell, Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }
    class SubmitButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            FileOutputStream fos = null;
            String user_input_id = edit_id.getText().toString();
            if(check_id) {
                try{
                    //자료를 넣을 파일 생성
                    fos = openFileOutput("user_id.bin", Context.MODE_PRIVATE);//저장할 이름과 접근 권한 설정
                    //자료를 빈 파일에 저장
                    //파일에 자료를 저장 (Byte형태로 변환 후)
                    fos.write(user_input_id.getBytes());//파일에 저장할때 write사용
                    //저장후 반드시 닫기
                    fos.close();
                }catch(Exception e){
                    Log.d("file stream","output stream error");}
            }
            else{
                Toast.makeText(SignInActivity.this, "양식을 확인해 주세요", Toast.LENGTH_SHORT).show();
            }
            String user_input_name = edit_name.getText().toString();
            String user_input_cell = edit_cell.getText().toString();
            if(user_input_name.equals("")||user_input_cell.equals("")) {
                Toast.makeText(SignInActivity.this, "이름과 전화번호를 모두 입력해 주세요.", Toast.LENGTH_SHORT).show();
            }else{
                SharedPreferences pref_save = getSharedPreferences("user_Info",0);
                SharedPreferences.Editor editor = pref_save.edit();
                editor.putString("user_name_01",user_input_name);
                editor.putString("user_cell_01",user_input_cell);
                editor.commit();
            }
        }
    }
    class IdCheckedListener implements View.OnFocusChangeListener{//다음 칸으로 넘어가는 리스너
        @Override
        public void onFocusChange(View view, boolean b) {
            String user_input_id = edit_id.getText().toString();
            int num_Of_Id= user_input_id.length();
            if(num_Of_Id < 5 || num_Of_Id > 12){
                tv_info.setText("비정상적인 아이디 입니다.");
                check_id = false;
            }else{
                tv_info.setText("정상아이디 입니다.");
                check_id = true;
            }
        }
    }
    class PwCheckedListener implements View.OnFocusChangeListener{//다음 칸으로 넘어가는 리스너
        @Override
        public void onFocusChange(View view, boolean b) {
            String user_input_pw = edit_pw.getText().toString();
            String user_input_re_pw = edit_repw.getText().toString();
            int num_of_pw;
            num_of_pw = user_input_pw.length();
            if(num_of_pw < 6 || num_of_pw > 16) {
                tv_info.setText("비밀번호의 길이가 적절하지 않습니다.");
            }else{
                if (user_input_pw.equals(user_input_re_pw)) {
                    tv_info.setText("비밀번호가 일치 합니다.");
                } else {
                    tv_info.setText("비밀번호가 일치하지 않습니다.");
                }
            }
        }
    }
    class SpinnerBirthListener implements AdapterView.OnItemSelectedListener{
        //항목이 선택된 경우
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch(view.getId()) {
                case R.id.spinner_day:
                    Toast.makeText(SignInActivity.this, string_day[i], Toast.LENGTH_SHORT).show();
                    break;
                case R.id.spinner_month:
                    Toast.makeText(SignInActivity.this, string_month[i], Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        //항목이 선택되지 않은 경우
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    class GenderCheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch(compoundButton.getId()) {
                case R.id.checkBox_female:
                    Toast.makeText(SignInActivity.this, checkBox_female.getText(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.checkBox_male:
                    Toast.makeText(SignInActivity.this, checkBox_male.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}

