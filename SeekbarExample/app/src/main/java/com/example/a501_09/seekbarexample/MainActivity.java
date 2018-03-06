package com.example.a501_09.seekbarexample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar_Green,seekBar_Red,seekBar_Blue;
    TextView txt_Seek;
    int red =255,green=255,blue=255;
    Button button_Red,button_Green,button_blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar_Red = (SeekBar)findViewById(R.id.seekBar_Red);
        seekBar_Green = (SeekBar)findViewById(R.id.seekBar_Green);
        seekBar_Blue = (SeekBar)findViewById(R.id.seekBar_Blue);
        txt_Seek = (TextView)findViewById(R.id.txt_seek);

        SeekBarlisteneer seekBarlisteneer = new SeekBarlisteneer();
        seekBar_Red.setOnSeekBarChangeListener(seekBarlisteneer);
        seekBar_Green.setOnSeekBarChangeListener(seekBarlisteneer);
        seekBar_Blue.setOnSeekBarChangeListener(seekBarlisteneer);

        button_Red = (Button)findViewById(R.id.button_Red);
        button_Green = (Button)findViewById(R.id.button_Green);
        button_blue = (Button)findViewById(R.id.button_Blue);

        ColorButtonListener colorButtonListener = new ColorButtonListener();
        button_Red.setOnClickListener(colorButtonListener);
        button_Green.setOnClickListener(colorButtonListener);
        button_blue.setOnClickListener(colorButtonListener);
    }

    class ColorButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button_Red:
                    seekBar_Red.setProgress(255);
                    seekBar_Green.setProgress(0);
                    seekBar_Blue.setProgress(0);
                    break;
                case R.id.button_Green:
                    seekBar_Red.setProgress(0);
                    seekBar_Green.setProgress(255);
                    seekBar_Blue.setProgress(0);
                    break;
                case R.id.button_Blue:
                    seekBar_Red.setProgress(0);
                    seekBar_Green.setProgress(0);
                    seekBar_Blue.setProgress(255);
                    break;
            }
        }
    }
    class SeekBarlisteneer implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {//seekBar 의 값
            switch (seekBar.getId()){
                case R.id.seekBar_Red:
                    red = i;
                    break;
                case R.id.seekBar_Green:
                    green = i;
                    break;
                case R.id.seekBar_Blue:
                    blue = i;
                    break;
            }
            txt_Seek.setBackgroundColor(Color.rgb(red,green,blue));//배경 색깔 변화
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {//seek에 손 대는 순간
            Toast.makeText(MainActivity.this,"start",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {//seek에 손을 뗄 때때
//            Toast.makeText(MainActivity.this,"end",Toast.LENGTH_SHORT).show();

        }
    }
}
