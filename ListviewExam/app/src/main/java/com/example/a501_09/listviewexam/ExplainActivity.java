package com.example.a501_09.listviewexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 501-09 on 2018-02-28.
 */

public class ExplainActivity extends AppCompatActivity {
    String[] movie_title;
    String[] movie_type;
    String[] movie_directer;
    String[] movie_actor;

    TextView mv_title;
    TextView mv_type;
    TextView mv_directer;
    TextView mv_actor;

    ImageButton back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        BackButtonListener backButtonListener = new BackButtonListener();
        back_btn.setOnClickListener(backButtonListener);

        movie_title = getResources().getStringArray(R.array.movie_title);
        movie_type = getResources().getStringArray(R.array.movie_type);
        movie_directer = getResources().getStringArray(R.array.movie_directer);
        movie_actor = getResources().getStringArray(R.array.movie_actor);

        mv_title = (TextView)findViewById(R.id.movie_title);
        mv_type = (TextView)findViewById(R.id.movie_type);
        mv_directer = (TextView)findViewById(R.id.movie_directer);
        mv_actor = (TextView)findViewById(R.id.movie_actor);


        //3. 화면 전환 후 이전 엑티비티에서 보낸 인텐트를 수신
        Intent intent = getIntent();
        //4. 인텐트에서 데이터를 읽음
        int movie_index = intent.getIntExtra("Movie_Index",-1);
        //데이터 수신 여부 확인
        if(movie_index != -1){
            Toast.makeText(ExplainActivity.this, Integer.toString(movie_index), Toast.LENGTH_LONG).show();
            //Integer.toString(temp)//숫자 출력할때
        }

        mv_title.setText(movie_title[movie_index]);
        mv_type.setText(movie_type[movie_index]);
        mv_directer.setText(movie_directer[movie_index]);
        mv_actor.setText(movie_actor[movie_index]);

    }
    class BackButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            finish();
        }
    }


}
