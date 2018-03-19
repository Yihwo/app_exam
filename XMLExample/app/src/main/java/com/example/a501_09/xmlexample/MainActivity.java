package com.example.a501_09.xmlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.annotation.Documented;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt_main = (TextView)findViewById(R.id.textview_main);
        String xml_str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<root>\n" +
                "\t<item Maker=\"청주\">사과</item>\n" +
                "\t<item Maker=\"대전\">포도</item>\n" +
                "\t<item Maker=\"제주\">수박</item>\n" +
                "</root>";

        try{
            //xml파일 읽어오기
            //도큐먼트 팩토리 안에 객체 입력
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //내용을 읽어 올 빌더 생성
            DocumentBuilder builder = factory.newDocumentBuilder();

            //입력 받은 데이터를 byte화
            InputStream input = new ByteArrayInputStream(xml_str.getBytes("utf-8"));

            Document document = builder.parse(input);
            //가장 큰 태그를 가져옴
            Element root = document.getDocumentElement();
            //항목들을 nodelist에 저장
            NodeList items = root.getElementsByTagName("item");
            String buf="";
            for (int i = 0;i<items.getLength();i++){
                //nodelist의 항목들을 각각 떼어내 node에 저장
                Node item = items.item(i);
                String value = item.getFirstChild().getNodeValue();
                buf += value+" ";
                txt_main.setText(buf);
            }
        }catch(Exception e){}
    }
}
