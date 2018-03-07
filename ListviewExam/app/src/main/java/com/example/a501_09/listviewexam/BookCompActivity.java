package com.example.a501_09.listviewexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 501-09 on 2018-03-07.
 */

public class BookCompActivity extends AppCompatActivity {

    Button btn_web,btn_map,btn_call,btn_complete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcomp);
        btn_call = (Button)findViewById(R.id.btn_call);
        btn_map = (Button)findViewById(R.id.btn_map);
        btn_web = (Button)findViewById(R.id.btn_web);
        btn_complete = (Button)findViewById(R.id.btn_complete);

        BookCompleteButtonListener bookCompleteButtonListener = new BookCompleteButtonListener();
        btn_complete.setOnClickListener(bookCompleteButtonListener);
        btn_call.setOnClickListener(bookCompleteButtonListener);
        btn_web.setOnClickListener(bookCompleteButtonListener);
        btn_map.setOnClickListener(bookCompleteButtonListener);

    }
    class BookCompleteButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_call:
                    Intent intent_call = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:0123456789"));
                    startActivity(intent_call);
                    break;
                case R.id.btn_map:
                    Intent intent_map = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.6420470,127.4888222"));
                    startActivity(intent_map);
                    break;
                case R.id.btn_web:
                    Intent intent_web = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cgv.co.kr"));
                    startActivity(intent_web);
                    break;
                case R.id.btn_complete:
                    Intent intent_comp = new Intent(BookCompActivity.this,MainActivity.class);
                    startActivity(intent_comp);
                    break;
            }

        }
    }
}
