package com.example.a501_09.myportfolio_chungnam.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by 501-09 on 2018-04-10.
 */

public class Util {
    public static Date getNowDateTime(){
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
