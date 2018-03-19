package com.example.a501_09.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by 501-09 on 2018-03-19.
 */

public class SubActivity extends AppCompatActivity {
    Button btn_sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn_sub = (Button)findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("ActivityCycle","SubActivity 에서 onCreate() 호출");
    }
    protected void  onStart(){
        super.onStart();
        Log.d("ActivityCycle","SubActivity 에서 onStart() 호출");
    }
    protected void onResume(){
        super.onResume();
        Log.d("ActivityCycle","SubActivity 에서 onResume() 호출");
    }
    protected  void onPause(){
        super.onPause();
        Log.d("ActivityCycle","SubActivity 에서 onPause() 호출");
    }
    protected  void onStop(){
        super.onStop();
        Log.d("ActivityCycle","SubActivity 에서 onStop() 호출");
    }
    protected  void onDestroy(){
        super.onDestroy();
        Log.d("ActivityCycle","SubActivity 에서 onDestroy() 호출");
    }
    protected  void onRestart(){
        super.onRestart();
        Log.d("ActivityCycle","SubActivity 에서 onRestart() 호출");
    }
}
