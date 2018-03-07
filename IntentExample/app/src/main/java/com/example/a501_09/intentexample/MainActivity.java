package com.example.a501_09.intentexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_web,button_map,button_add,button_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_web = (Button)findViewById(R.id.button1);
        button_map = (Button)findViewById(R.id.button2);
        button_call = (Button)findViewById(R.id.button3);
        button_add = (Button)findViewById(R.id.button4);

        ButtonClickListener buttonClickListener = new ButtonClickListener();

        button_map.setOnClickListener(buttonClickListener);
        button_web.setOnClickListener(buttonClickListener);
        button_call.setOnClickListener(buttonClickListener);
        button_add.setOnClickListener(buttonClickListener);


    }
    class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button1:
                    //웹 페이지를 불러옴
                    Intent intent_web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                    startActivity(intent_web);
                    break;
                case R.id.button2:
                    //지도를 불러옴
                    Intent intent_map = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:36.6420470,127.4888222"));
                    startActivity(intent_map);
                    break;
                case R.id.button3:
                    //전화앱을 불러옴
                    //
                    Intent intent_call = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:01012345678"));
                    startActivity(intent_call);
                    break;
                case R.id.button4:
                    Intent intent_add = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/"));
                    startActivity(intent_add);
                    break;
            }
        }
    }
}
