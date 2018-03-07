package com.example.a501_09.webviewexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    WebView wv_test;
    ImageButton goWeb_btn;
    EditText address_txt;
    String webAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address_txt = (EditText)findViewById(R.id.address_txt);
        goWeb_btn = (ImageButton)findViewById(R.id.goWeb_btn);

        GoToWebPageBtnListener goToWebPageBtnListener = new GoToWebPageBtnListener();
        goWeb_btn.setOnClickListener(goToWebPageBtnListener);

        //웹뷰 객체 생성
        wv_test = (WebView)findViewById(R.id.webView01);
        //websetting 객체를 통해 웹뷰 설정
        WebSettings webSettings = wv_test.getSettings();
        //자바 스크립트 허용
        webSettings.setJavaScriptEnabled(true);
        //새 창에 페이지가 나타나는 것을 방지
        wv_test.setWebViewClient(new WebViewClient() {
            public boolean shouldoverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        //웹페이지 로드하기
        wv_test.loadUrl("http://www.naver.com");
    }
    class GoToWebPageBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            webAddress = address_txt.getText().toString();
            wv_test.loadUrl(webAddress);
        }
    }
}
