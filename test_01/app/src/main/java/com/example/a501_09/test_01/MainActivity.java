package com.example.a501_09.test_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText num01;
    EditText num02;
    TextView txt_result;
    Button btn_add;
    Button btn_min;
    Button btn_mul;
    Button btn_div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num01 = (EditText) findViewById(R.id.num1);
        num02 = (EditText) findViewById(R.id.num2);
        txt_result = (TextView) findViewById(R.id.result);
        btn_add = (Button) findViewById(R.id.add);
        btn_min = (Button) findViewById(R.id.min);
        btn_mul = (Button) findViewById(R.id.mul);
        btn_div = (Button) findViewById(R.id.div);

        MyListener myListener = new MyListener();

        btn_add.setOnClickListener(myListener);
        btn_min.setOnClickListener(myListener);
        btn_mul.setOnClickListener(myListener);
        btn_div.setOnClickListener(myListener);

    }
    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String input01 = num01.getText().toString();
            String input02 = num02.getText().toString();
            int result;

            switch (view.getId()) {
                case R.id.add:
                    result = Integer.parseInt(input01)+Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.min:
                    result = Integer.parseInt(input01) - Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.mul:
                    result = Integer.parseInt(input01) * Integer.parseInt(input02);
                    txt_result.setText(Integer.toString(result));
                    break;
                case R.id.div:
                    double result1 = Double.parseDouble(input01) / Double.parseDouble(input02);
                    txt_result.setText(Double.toString(result1));
                    break;
            }
        }
    }
}
