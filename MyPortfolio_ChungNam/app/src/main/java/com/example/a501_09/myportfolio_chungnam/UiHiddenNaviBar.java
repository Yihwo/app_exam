package com.example.a501_09.myportfolio_chungnam;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by 501-09 on 2018-03-30.
 */
//화면상에서 작업바를 지우는 클래스
public class UiHiddenNaviBar {
   public UiHiddenNaviBar(Context context){
       View decorView = ((Activity)context).getWindow().getDecorView();
       int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
       decorView.setSystemUiVisibility(uiOptions);
   }
}
