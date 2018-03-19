package com.example.a501_09.activitylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main = (Button)findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                startActivity(intent);
            }
        });
        Log.d("ActivityCycle","MainActivity 에서 onCreate() 호출");
    }
    protected void  onStart(){
        super.onStart();
        Log.d("ActivityCycle","MainActivity 에서 onStart() 호출");
    }
    protected void onResume(){
        super.onResume();
        Log.d("ActivityCycle","MainActivity 에서 onResume() 호출");
    }
    protected  void onPause(){
        super.onPause();
        Log.d("ActivityCycle","MainActivity 에서 onPause() 호출");
    }
    protected  void onStop(){
        super.onStop();
        Log.d("ActivityCycle","MainActivity 에서 onStop() 호출");
    }
    protected  void onDestroy(){
        super.onDestroy();
        Log.d("ActivityCycle","MainActivity 에서 onDestroy() 호출");
    }
    protected  void onRestart(){
        super.onRestart();
        Log.d("ActivityCycle","MainActivity 에서 onRestart() 호출");
    }
}
