package com.example.a501_09.togglebuttonswitchexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggleBtn;
    Switch switch01, switch02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleBtn = (ToggleButton)findViewById(R.id.toggleBtn);
        ToggleButtonListener toggleButtonListener = new ToggleButtonListener();

        //리스너의 객체를 만들고 버튼 에 등록 하는 것을 한번에 동작시킬 수 있다.
        toggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    toggleBtn.setTextOn("Turn on");
                    Toast.makeText(MainActivity.this, toggleBtn.getTextOn(), Toast.LENGTH_SHORT).show();
                }else{
                    toggleBtn.setTextOff("Turn off");
                    Toast.makeText(MainActivity.this, toggleBtn.getTextOff(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        /////////////////////////////////스위치/////////////////////////////////////
        switch01 = (Switch)findViewById(R.id.switch1);
        switch02 = (Switch)findViewById(R.id.switch2);

//        switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//            }
//        });
        switch01.setOnCheckedChangeListener(new SwitchListener());
        //4,5번 과정 함축형
        switch02.setOnCheckedChangeListener(new SwitchListener());

        switch01.setChecked(true);//초기값을 정해줌
        //switch01.setTextOn("사과");
        //switch01.setTextOff("바나나");
        //스위치의 외부 옵션 변경
        switch01.setThumbResource(R.drawable.ic_launcher_background);
    }
    class ToggleButtonListener implements CompoundButton.OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b) {
                Toast.makeText(MainActivity.this, "눌림", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(MainActivity.this, "안눌림", Toast.LENGTH_SHORT).show();
        }
    }
    class SwitchListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.switch1:
                    if (b) {
                        Toast.makeText(MainActivity.this, "1활성화", Toast.LENGTH_SHORT).show();
                        switch01.setTextOn("사과");
                    } else {
                        Toast.makeText(MainActivity.this, "1비활성화", Toast.LENGTH_SHORT).show();
                        switch01.setTextOff("바나나");
                    }
                    break;
                case R.id.switch2:
                    if (b) {
                        Toast.makeText(MainActivity.this, "2활성화", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "2비활성화", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }
}
