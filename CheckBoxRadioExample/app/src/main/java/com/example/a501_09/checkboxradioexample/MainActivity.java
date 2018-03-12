package com.example.a501_09.checkboxradioexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1,checkBox2,checkBox3;
    RadioButton radioButton1,radioButton2,radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);

        radioButton1 = (RadioButton)findViewById(R.id.radioButton2);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton3);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton4);

        CheckBoxListener checkBoxListener = new CheckBoxListener();

        checkBox1.setOnCheckedChangeListener(checkBoxListener);
        checkBox2.setOnCheckedChangeListener(checkBoxListener);
        checkBox3.setOnCheckedChangeListener(checkBoxListener);

        radioButton1.setOnCheckedChangeListener(new RadioButtonListener());
        radioButton2.setOnCheckedChangeListener(new RadioButtonListener());
        radioButton3.setOnCheckedChangeListener(new RadioButtonListener());
    }
    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //if(b) {
                switch (compoundButton.getId()) {
                    case R.id.checkBox:
                        Toast.makeText(MainActivity.this, checkBox1.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.checkBox2:
                        Toast.makeText(MainActivity.this, checkBox2.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.checkBox3:
                        Toast.makeText(MainActivity.this, checkBox3.getText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            //}
        }
    }
    class RadioButtonListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.radioButton2:
                    Toast.makeText(MainActivity.this, radioButton1.getText(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButton3:
                    Toast.makeText(MainActivity.this, radioButton2.getText(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioButton4:
                    Toast.makeText(MainActivity.this, radioButton3.getText(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
