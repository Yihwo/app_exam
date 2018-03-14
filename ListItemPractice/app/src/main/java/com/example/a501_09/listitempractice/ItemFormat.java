package com.example.a501_09.listitempractice;

/**
 * Created by 501-09 on 2018-03-14.
 */
//리스트 항목에 들어갈 정보
public class ItemFormat {
    int item_image_left, item_image_right;
    String item_name_left, item_name_right;
    String item_price_left, item_price_right;
    double score_left, score_right;

    public ItemFormat(int item_image_left, int item_image_right,
                      String item_name_left, String item_name_right,
                      String item_price_left, String item_price_right,
                      double score_left, double score_right) {
        this.item_image_left = item_image_left;
        this.item_image_right = item_image_right;
        this.item_name_left = item_name_left;
        this.item_name_right = item_name_right;
        this.item_price_left = item_price_left;
        this.item_price_right = item_price_right;
        this.score_left = score_left;
        this.score_right = score_right;
    }
}
