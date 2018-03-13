package com.example.a501_09.newlistexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-03-13.
 */

public class MyItemAdapter extends BaseAdapter {
    /////////////////필수 요소///////////////////////////
    //main엑티비티에서 저장한 값을 가져오기위한 객체 생성,리스트 뷰에 표현할 정보
    ArrayList<MainActivity.Item_dataFormet> arrayList;
    //항목을 표현하는 객체의 변수
    LayoutInflater inflater;
    //메인 엑티비티의 정보를 저장하는 변수
    Context context;
    //항목의 레이아웃 아이디
    int item_layout;

    //generate로 생성자 만들기
    //메인 엑티비티의 정보를 가지고 레이아웃을 만드는 역할
    public MyItemAdapter(ArrayList<MainActivity.Item_dataFormet> arrayList, Context context, int item_layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.item_layout = item_layout;
        //인플레터는 직접 작성
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //항목의 갯수를 알려주는 함수수
   @Override
    public int getCount() {
        return arrayList.size();
    }
    //항목의 정보 객체를 알려주는 함수
    @Override
    public Object getItem(int i) {
        //i 번째의 리스트객체에서 정보를 꺼내옴
        return arrayList.get(i);
    }
    //항목의 정보 객체의 인덱스를 알려주는 함수
    @Override
    public long getItemId(int i) {
        return i;
    }
    //항목의 정보를 항목 레이아웃에 표현하는 함수
    //자료가 생길때 마다 호출된다.
    //첫 실행에서는 항목의 요소들을 구성하기 위해 호출되기 때문에 첫 호출을 구별해줄 필요가 있다.
    @Override
    //i는 항목/arraylist의 번호, view는 항목, viewGroup는 항목들이 포함된 리스트
    //item_layout 항목 레이아웃의 아이디
    public View getView(int i, View view, ViewGroup viewGroup) {
        //처음으로 항목을 만들 경우
        if(view==null){
            view = inflater.inflate(item_layout,viewGroup,false);
        }else{//첫번째 호출 이후 데이터를 표현

        }
        ImageView img = (ImageView)view.findViewById(R.id.item_image);
        TextView title_txt = (TextView)view.findViewById(R.id.title_txt);
        TextView subtitle_txt = (TextView)view.findViewById(R.id.subtitle_txt);

        //arrayList의 데이터를 항목을 구성하는 컴포넌트에 저장
        //arrayList의 요소는 메인에서 정의한 요소들로 이루어짐
        img.setImageResource(arrayList.get(i).img);
        title_txt.setText(arrayList.get(i).title);
        subtitle_txt.setText(arrayList.get(i).sub_title);
        //return view; 필요
        return view;
    }
}
