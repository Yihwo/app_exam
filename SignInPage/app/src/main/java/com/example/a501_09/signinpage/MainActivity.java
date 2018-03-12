package com.example.a501_09.signinpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit_id,edit_pw,edit_repw,edit_name,edit_year,edit_cell;
    CheckBox checkBox_male,checkBox_female;
    Spinner spinner_month,spinner_day;
    //ArrayAdapter adapter_month,adapter_day;
    String[] string_month,string_day;
    boolean isInitSpinner = false;
    ArrayAdapter<String> adapter_month,adapter_day;
    TextView tv_info;

    int i,j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                string_month);//적용할 리스트
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//스피너의 틀
        spinner_month.setAdapter(adapter_month);

        adapter_day = new ArrayAdapter<String>(
                MainActivity.this, //배치할 엑티비티
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
    class IdCheckedListener implements View.OnFocusChangeListener{//다음 칸으로 넘어가는 리스너
        @Override
        public void onFocusChange(View view, boolean b) {
            String user_input_id = edit_id.getText().toString();
            int num_Of_Id= user_input_id.length();
            if(num_Of_Id < 5 || num_Of_Id > 12){
                tv_info.setText("비정상적인 아이디 입니다.");
            }else{
                tv_info.setText("정상아이디 입니다.");
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
                    Toast.makeText(MainActivity.this, string_day[i], Toast.LENGTH_SHORT).show();
                    break;
                case R.id.spinner_month:
                    Toast.makeText(MainActivity.this, string_month[i], Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, checkBox_female.getText(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.checkBox_male:
                    Toast.makeText(MainActivity.this, checkBox_male.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
