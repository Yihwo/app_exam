package com.example.a501_09.pushmessageexample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

/**
 * Created by 501-09 on 2018-03-21.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //메세지를 받아옴
        showNotification(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
    }
    //메세지가 표현되고 기능하기 위한 함수
    private void showNotification(String title, String message){
        //메세지를 클릭했을때 실행하는 액티비티를 지정
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //앱 비활성화 상태에서도 메세지를 받을 수 있도록 하는 PendingIntent생성
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this,0, intent, PendingIntent.FLAG_ONE_SHOT);

        //메세지 수신시 소리 알람을 표시하기 위한 dsfaultSounduri 객체 생성
        Uri defaultSounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //메세지를 표현하기 위한 설정
        NotificationCompat.Builder  builder= new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)//아이콘 입력
                .setContentTitle(title)//메세지 제목 입력
                .setContentText(message)//푸시 메세지에 나타날 내용
                .setSound(defaultSounduri)//사운드
                .setAutoCancel(true)//푸시 메세지를 터치하면 사라짐
                .setContentIntent(pendingIntent);//pendingIntent 설정

        //수신한 메세지를 스마트 폰에서 표현
        NotificationManager manager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);//매니저 객체 만들기
        //매니저를 통해서 푸시 메세지 띄우기
        manager.notify(0,builder.build());//di == request code
    }
}
