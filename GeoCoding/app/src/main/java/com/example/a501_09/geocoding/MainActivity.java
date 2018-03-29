package com.example.a501_09.geocoding;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button find_position,find_address;
    EditText lat_txt,lng_txt,address_txt;
    TextView result_txt;

    Geocoder geocoder;
    List<Address> addresses = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        find_address = (Button)findViewById(R.id.find_address);
        find_position=(Button) findViewById(R.id.find_position);
        lat_txt=(EditText)findViewById(R.id.lat_txt);
        lng_txt=(EditText)findViewById(R.id.lng_txt);
        address_txt=(EditText)findViewById(R.id.address_txt);
        result_txt=(TextView)findViewById(R.id.result_txt);
        //좌표 주소 전화번호등의 변환을 위한 geocoder 객체 생성
        geocoder = new Geocoder(MainActivity.this);

        find_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lat = lat_txt.getText().toString();
                String lng = lng_txt.getText().toString();
                try{
                    //위도 경도를 통해서 상세주소를 가져오는 함수 getFromLocation
                    //결과를 List에 저장
                    addresses = geocoder.getFromLocation(Double.parseDouble(lat),Double.parseDouble(lng),5);
                }catch (Exception e){
                    Log.d("Geo",e.getMessage());
                }
                if(addresses.size() == 0 || addresses == null){
                    result_txt.setText("검색결과가 없습니다.");
                }else{
                    //getCountryName : 국가
                    //getPhone : 전화번호
                    //getPostalCode : 우편번호
                    //getFeatureName : 명칭
                    //getLatitude : 위도
                    //getLongitude : 경도
                    result_txt.setText(addresses.get(0).toString());
                }
            }
        });
        find_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = address_txt.getText().toString();
                try{
                    //간단한 주소를 통해 상세주소를 찾는 함수 getFromLocationName 함수
                    //결과를 List에 저장
                    addresses = geocoder.getFromLocationName(addr,1);
                }catch (Exception e){
                    Log.d("Geo",e.getMessage());
                }
                if(addresses.size() == 0 || addresses == null){
                    result_txt.setText("검색결과가 없습니다.");
                }else{
                    //검색 결과에서 특정한 값만 받아올 수 있는 함수
                    //getCountryCode : 국가 번호
                    //getCountryName : 국가
                    //getPhone : 전화번호
                    //getPostalCode : 우편번호
                    //getFeatureName : 명칭
                    //getLatitude : 위도
                    //getLongitude : 경도
                    result_txt.setText(addresses.get(0).toString());
                }
            }
        });
    }
}
