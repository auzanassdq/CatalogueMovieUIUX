package com.example.auzan.cataloguemovieuiux;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.auzan.cataloguemovieuiux.activity.MainActivity;

import java.util.Calendar;

public class DailyReminderAlarm extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 2407;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = "Catalogue Movie";
        String message = "Let's see our movie today!";
        sendNotification(context, title, message, NOTIFICATION_ID);
    }

    public void sendNotification(Context context, String title, String message, int notifId) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_movie_white)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setSound(alarmSound)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true);

        notificationManagerCompat.notify(notifId, notification.build());

    }

    public void setRepeatingAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyReminderAlarm.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        int requestCode = NOTIFICATION_ID;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminderAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);

        alarmManager.cancel(pendingIntent);
    }

//    private static PendingIntent getPendingIntent(Context context) {
//        /* get the application context */
//        Intent alarmIntent = new Intent(context, DailyReminderAlarm.class);
//
//        boolean isAlarmOn = (PendingIntent.getBroadcast(context, NOTIFICATION_ID, alarmIntent,
//                PendingIntent.FLAG_NO_CREATE) != null);
//
//        Log.e("isAlarmOn : ", String.valueOf(isAlarmOn));
//
//        return PendingIntent.getBroadcast(context, NOTIFICATION_ID, alarmIntent,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//    }

}
