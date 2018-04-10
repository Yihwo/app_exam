package com.example.a501_09.myportfolio_chungnam;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 501-09 on 2018-04-10.
 */

public class PermissionChecker {
    private Context context;
    private String[] not_granted_permissions;

    public PermissionChecker(Context context, String... permissions) {
        this.context = context;
        ArrayList<String> temp = new ArrayList<String>();

        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) { // 권한이 설정되지 않은 경우

                temp.add(permissions[i]);
            }
        }
        if (temp.size() > 0) {
            not_granted_permissions = new String[temp.size()];
            not_granted_permissions = temp.toArray(not_granted_permissions);
        }

    }

    public String[] checkNoGrantedPermission() {
        if (not_granted_permissions != null && not_granted_permissions.length != 0) {
            return not_granted_permissions;
        } else {
            return null;
        }
    }

    public void getPermission(String[] not_granted_permissions) {
        if (not_granted_permissions != null) {
            for (int i = 0; i < not_granted_permissions.length; i++) {
                /**
                 * shouldShowRequestPermissionRationale()의 return 값에 따라
                 * true : 사용자가 권한을 요청을 한번 거절한 경우
                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        not_granted_permissions[i])) {
                    Toast.makeText(context, "앱이 정상적으로 동작하기 위해 권한이 필요합니다.",
                            Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(
                            (Activity) context,
                            not_granted_permissions,
                            i
                    );
                }
            }
        }
    }


}