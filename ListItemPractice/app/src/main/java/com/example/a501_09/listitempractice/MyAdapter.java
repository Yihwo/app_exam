package com.example.a501_09.listitempractice;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-03-14.
 */

public class MyAdapter extends BaseAdapter {
    Context context;//메인엑티비티 정보를 담는 변수
    LayoutInflater inflater;//항목을 표현하기 위해 활용하는 객체
    ArrayList<ItemFormat> arrayList;//자료를 저장하고 있는 arraylist
    int item_idx;//항목 Layout 파일의 아이디
    public MyAdapter(Context context, ArrayList<ItemFormat> arrayList, int item_idx) {
        this.context = context;
        this.arrayList = arrayList;
        this.item_idx = item_idx;
        //항목을 표시할 개게 layoutinflater를 생성
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    //arraylist의 저장된 항목의 개수를 알려주는 함수
    @Override
    public int getCount() {
        return arrayList.size();
    }
    //arraylist에 저장된 항목객체를 알려주는 함수
    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }
    //arraylist의 인덱스를 알려주는 함수
    @Override
    public long getItemId(int i) {
        return i;
    }
    //
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //항목 레이아웃을 리스트 뷰에 추가해주는 부분
        if(view == null){
            view = inflater.inflate(item_idx,viewGroup,false);
        }
        //컴포넌트 객체 생성
        ImageView item_img_left = (ImageView)view.findViewById(R.id.img_Item01);
        ImageView item_img_right = (ImageView)view.findViewById(R.id.img_Item02);
        TextView txt_tit_left = (TextView)view.findViewById(R.id.tit_Item01);
        TextView txt_tit_right = (TextView)view.findViewById(R.id.tit_Item02);
        TextView txt_price_left = (TextView)view.findViewById(R.id.txt_price01);
        TextView txt_price_right = (TextView)view.findViewById(R.id.txt_price02);
        TextView txt_score_left = (TextView)view.findViewById(R.id.txt_score01);
        TextView txt_score_right = (TextView)view.findViewById(R.id.txt_score02);
        RatingBar ratingBar_left = (RatingBar)view.findViewById(R.id.rating_item_01);
        RatingBar ratingBar_right = (RatingBar)view.findViewById(R.id.rating_item_02);
        ConstraintLayout constraintLayout_left = (ConstraintLayout)view.findViewById(R.id.view_Item01);
        ConstraintLayout constraintLayout_right = (ConstraintLayout)view.findViewById(R.id.view_Item02);

        //객체에 데이터 추가가
        item_img_left.setImageResource(arrayList.get(i).item_image_left);
        item_img_right.setImageResource(arrayList.get(i).item_image_right);
        txt_tit_left.setText(arrayList.get(i).item_name_left);
        txt_tit_right.setText(arrayList.get(i).item_name_right);
        txt_price_left.setText(arrayList.get(i).item_price_left);
        txt_price_right.setText(arrayList.get(i).item_price_right);
        txt_score_left.setText(Double.toString(arrayList.get(i).score_left));
        txt_score_right.setText(Double.toString(arrayList.get(i).score_right));
        ratingBar_left.setRating(Float.parseFloat(Double.toString(arrayList.get(i).score_left)));
        ratingBar_right.setRating(Float.parseFloat(Double.toString(arrayList.get(i).score_right)));

        //컴포넌트에 리스너 등록
        constraintLayout_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
            }
        });
        constraintLayout_right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
