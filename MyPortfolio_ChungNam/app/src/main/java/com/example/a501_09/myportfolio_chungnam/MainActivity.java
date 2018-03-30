package com.example.a501_09.myportfolio_chungnam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainWebView();
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
                if(url.startsWith("app://")){
                    Intent intent = new Intent(MainActivity.this,TestActivity.class);
                    startActivity(intent);
                }else{
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView.loadUrl("http://118.46.60.101/index.php");
    }
}
