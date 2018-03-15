package com.example.a501_09.progressbarexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Switch aSwitch;
    ProgressBar progressBar_Horizen;
    Button leftToRight,rightToLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar_Horizen = (ProgressBar)findViewById(R.id.progressBarHorizen);
        aSwitch = (Switch)findViewById(R.id.switch1);
        leftToRight = (Button)findViewById(R.id.ltor_btn);
        rightToLeft = (Button)findViewById(R.id.rtol_btn);

        ProgressButtonListener progressButtonListener = new ProgressButtonListener();
        leftToRight.setOnClickListener(progressButtonListener);
        rightToLeft.setOnClickListener(progressButtonListener);

        //최대값 설정
        progressBar_Horizen.setMax(200);
        progressBar_Horizen.setProgress(100);
        progressBar_Horizen.setSecondaryProgress(150);

        SwitchClickedListener switchClickedListener = new SwitchClickedListener();

        aSwitch.setOnCheckedChangeListener(switchClickedListener);
    }
    class SwitchClickedListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
    class ProgressButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ltor_btn:
                    progressBar_Horizen.incrementProgressBy(10);
                    progressBar_Horizen.incrementSecondaryProgressBy(10);
                    break;
                case R.id.rtol_btn:
                    progressBar_Horizen.incrementProgressBy(-10);
                    progressBar_Horizen.incrementSecondaryProgressBy(-10);
                    break;
            }
        }
    }
}
