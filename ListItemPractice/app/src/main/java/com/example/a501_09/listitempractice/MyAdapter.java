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
    LayoutInflater inflater;
    ArrayList<ItemFormat> arrayList;
    int item_idx;
    public MyAdapter(Context context, ArrayList<ItemFormat> arrayList, int item_idx) {
        this.context = context;
        this.arrayList = arrayList;
        this.item_idx = item_idx;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(item_idx,viewGroup,false);
        }
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
