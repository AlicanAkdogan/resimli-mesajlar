package com.alican.resimlimesajlar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MesajServis extends FirebaseMessagingService {

    private NotificationCompat.Builder builder;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);


        String baslik = message.getNotification().getTitle();
        String icerik = message.getNotification().getBody();

        Log.e("Başlık",baslik);
        Log.e("İçerik",icerik);

        durumBagli(baslik,icerik);

    }

    public void durumBagli(String baslik, String icerik){

        NotificationManager bildirimYonteicisi =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this,MainActivity.class);

        PendingIntent gidilecekIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            String kanalId="kanalId";
            String kanalAd="kanalAd";
            String kanalTanım = "kanalTanım";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYonteicisi.getNotificationChannel(kanalId);

            if(kanal == null){

                kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimYonteicisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(this,kanalId);

            builder.setContentTitle(baslik);
            builder.setContentText(icerik);
            builder.setSmallIcon(R.drawable.ic_baseline_mosque_24);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
        }else{

            builder = new NotificationCompat.Builder(this);

            builder.setContentTitle(baslik);
            builder.setContentText(icerik);
            builder.setSmallIcon(R.drawable.ic_baseline_mosque_24);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        bildirimYonteicisi.notify(1,builder.build());

    }
}
