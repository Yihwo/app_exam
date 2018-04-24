package com.example.a501_09.myportfolio_chungnam;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.a501_09.myportfolio_chungnam.util.Util;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainWebView();

        UiHiddenNaviBar uiHiddenNaviBar = new UiHiddenNaviBar(MainActivity.this);

//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
    }
    private void setMainWebView(){
        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();

        //자바 스크립트가 정상적으로 동작하도록 해줌
        webSettings.setJavaScriptEnabled(true);
        //앱에서 웹뷰를 출력하도록 도와줌
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                //연결된 홈페이지 내부에서 특정한 url을 선택 했을때 activity를 이동함
                if(url.startsWith("app://place1")){
                    Util.setPlaceIndex(MainActivity.this,0);
                    Util.setPlaceTitle(MainActivity.this,"악휘봉");
                    Intent intent = new Intent(MainActivity.this,ListTripActivity.class);
                    startActivity(intent);
                }else if(url.startsWith("app://place2")){
                    Util.setPlaceIndex(MainActivity.this,1);
                    Util.setPlaceTitle(MainActivity.this,"희양산");
                    Intent intent = new Intent(MainActivity.this,ListTripActivity.class);
                    startActivity(intent);
                }else if(url.startsWith("app://place3")){
                    Util.setPlaceIndex(MainActivity.this,2);
                    Util.setPlaceTitle(MainActivity.this,"조항산");
                    Intent intent = new Intent(MainActivity.this,ListTripActivity.class);
                    startActivity(intent);
                }else if(url.startsWith("app://place4")){
                    Util.setPlaceIndex(MainActivity.this,3);
                    Util.setPlaceTitle(MainActivity.this,"쌍곡 계곡");
                    Intent intent = new Intent(MainActivity.this,ListTripActivity.class);
                    startActivity(intent);
                }else if(url.startsWith("app://place5")){
                    Util.setPlaceIndex(MainActivity.this,4);
                    Util.setPlaceTitle(MainActivity.this,"선유구곡");
                    Intent intent = new Intent(MainActivity.this,ListTripActivity.class);
                    startActivity(intent);
                }else if(url.startsWith("tel:")){
                    Intent dial = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    //현재의 activity 에 대하여 startActivity 호출
                    startActivity(dial);
                    return true;
                }
                else{
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl(DefaultOptionURL.DEFAULT_MAIN_PAGE_URL);
    }
}
