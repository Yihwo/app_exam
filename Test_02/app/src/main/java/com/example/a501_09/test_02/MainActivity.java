package com.example.a501_09.test_02;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_pre,btn_play,btn_stop,btn_nxt;
    TextView tit_mp3;
    ListView list_mp3;
    ArrayList<String> mp3_array;
    ArrayList<String> mp3_title= new ArrayList<String>();
    int mp3_index=0;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_mp3 = (ListView)findViewById(R.id.list_mp3);

        mp3_array = FindFileNameExtend("mp3");

        btn_pre = (Button)findViewById(R.id.btn_pre);
        btn_play = (Button)findViewById(R.id.btn_play);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_nxt = (Button)findViewById(R.id.btn_nxt);

        tit_mp3 = (TextView)findViewById(R.id.tit_mp3);

        player = new MediaPlayer();

        try {
            player.setDataSource(mp3_array.get(mp3_index));
            player.prepare();
        } catch (Exception e) {Log.d("play mp3", "mp3 file error");}

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                mp3_title);

        list_mp3.setAdapter(adapter);
        list_mp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tit_mp3.setText(mp3_title.get(i));
                mp3_index = i;
                try {
                    if (player.isPlaying()) {
                        player.stop();
                        player.prepare();
                    }else{btn_play.setText("일시정지");}
                    player.reset();
                    player.setDataSource(mp3_array.get(i));
                    player.prepare();
                    player.start();
                } catch (Exception e) {Log.d("play mp3", "mp3 file error");}
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.pause();
                    btn_play.setText("재생");
                } else {
                    player.start();
                    btn_play.setText("일지중지");
                }
                tit_mp3.setText(mp3_title.get(mp3_index));
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player.isPlaying()) {
                    player.stop();
                    btn_play.setText("재생");
                }
                try {
                    player.prepare();
                } catch (Exception e) {Log.d("play mp3", "mp3 file error");}
            }
        });
        btn_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp3_index < mp3_array.size()-1) {
                    mp3_index++;
                }else{
                    mp3_index=0;
                }
                tit_mp3.setText(mp3_title.get(mp3_index));
                try {
                    if (player.isPlaying()) {
                        player.stop();
                        player.prepare();
                    }else{btn_play.setText("일시정지");}
                    player.reset();
                    player.setDataSource(mp3_array.get(mp3_index));
                    player.prepare();
                    player.start();
                } catch (Exception e) {Log.d("play mp3", "mp3 file error");}
            }
        });
        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp3_index > 0) {
                    mp3_index--;
                }else{
                    mp3_index = mp3_array.size()-1;
                }
                tit_mp3.setText(mp3_title.get(mp3_index));
                try {
                    if (player.isPlaying()) {
                        player.stop();
                        player.prepare();
                    }else{btn_play.setText("일시정지");}
                    player.reset();
                    player.setDataSource(mp3_array.get(mp3_index));
                    player.prepare();
                    player.start();
                } catch (Exception e) {Log.d("play mp3", "mp3 file error");}
            }
        });
    }

    private ArrayList<String> FindFileNameExtend (String ext){
        ArrayList<String> temp = new ArrayList<String>();
        final String file_ext = ext;
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            FilenameFilter file_filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith(file_ext);
                }
            };
            File sdRoot = new File(sdPath);
            String[] file_list = sdRoot.list(file_filter);
            for (int i = 0; i < file_list.length; i++) {
                temp.add(sdPath + "/" + file_list[i]);
                mp3_title.add(file_list[i]);
            }
            return temp;
        } else {
            Toast.makeText(this, "sd card의 인식이 불가합니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    protected void onDestroy(){
        super.onDestroy();
        if(player!=null) {
            player.release();
            player = null;
        }
    }
}
