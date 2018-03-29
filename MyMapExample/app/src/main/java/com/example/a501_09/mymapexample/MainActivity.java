package com.example.a501_09.mymapexample;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

//지도에 마커를 추가하기 위해 onMapReadyCallback 클래스 추가
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    //AIzaSyDZx-5PxLLpR6aeXGx6GHgCtqQDiyeGJs4
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fragment 객체 로드
        FragmentManager fragmentManager = getFragmentManager();
        //지도를 표시해줄 fragment 객체 생성
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.mapview_main);
        //지도 정보를 읽어옴
        mapFragment.getMapAsync(this);
    }


    //지도 정보를 다 읽어온 후에 실행하는 onMapReady함수수
    @Override
    public void onMapReady(GoogleMap googleMap) {

        ArrayList<LatLng> arrayList = new ArrayList<>();
        arrayList.add(new LatLng(34.51,126.97));
        arrayList.add(new LatLng(36.6283032,127.4561313));
        arrayList.add(new LatLng(37.56, 129.03));
         for(int i=0; i < arrayList.size() ; i++){
             MarkerOptions markerOptions_02 = new MarkerOptions();
             markerOptions_02.position(arrayList.get(i));

             googleMap.addMarker(markerOptions_02);
         }

        //위도 경도를 저장할 객체 생성
        LatLng seoul = new LatLng(37.56, 126.97);

        //마커에 위치 지정
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(seoul);
        //마커 설명
        markerOptions.title("서울");
        //마케 부연설명
        markerOptions.snippet("수도 입니다.");
        //마커를 지도에 표시
        googleMap.addMarker(markerOptions);
        //지도가 실행될때 마커가 가운데 오도록 화면 조정
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        //지도의 배율을 결정하는 zoomTo 함수 숫자가 클 수록 상세히 표현
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(6));

    }
}
