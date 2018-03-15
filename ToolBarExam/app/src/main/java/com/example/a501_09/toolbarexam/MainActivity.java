package com.example.a501_09.toolbarexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar_main = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);
        toolbar_main.setTitle("");//툴바의 타이틀은 변경 가능
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);//menu.xml을 불러옴
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.toolbar_item_select1:
                Toast.makeText(MainActivity.this, "select first", Toast.LENGTH_SHORT).show();
                return true;
                //onOptionsItemSelected 함수 특성상 처리하면 true를 리턴해야 한다.
            case R.id.toolbar_item_select2:
                Toast.makeText(MainActivity.this, "select second", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
