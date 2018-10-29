package com.example.auzan.cataloguemovieuiux.utils;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.activity.MainActivity;
import com.example.auzan.cataloguemovieuiux.model.MovieItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.auzan.cataloguemovieuiux.BuildConfig.API_KEY;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.EN_LANG_URL;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.MOVIE_URL;
import static com.example.auzan.cataloguemovieuiux.BuildConfig.UP_COMING_URL;

/**
 * Created by auzan on 10/28/2018.
 * Github: @auzanassdq
 */
public class UpcomingReminderReceive extends BroadcastReceiver {

    private static final String API_URL = MOVIE_URL + UP_COMING_URL + API_KEY + EN_LANG_URL;
    private static int NOTIFICATION_ID = 2046;
    private ArrayList<MovieItem> movieLists = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        getReleasedToday(context);
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

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification.build());
        }
    }

    public void setReleaseAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, UpcomingReminderReceive.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE, 1);

        int requestCode = NOTIFICATION_ID;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    private void getReleasedToday(final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("results");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject movie = array.getJSONObject(i);
                        MovieItem movies = new MovieItem(movie);
                        movies.setReleaseDate(movie.getString("release_date"));
                        movieLists.add(movies);
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date date = new Date();
                    final String dateNow = dateFormat.format(date);

                    for (int i = 0; i<movieLists.size(); i++){
                        MovieItem movieItem = movieLists.get(i);

                        if (movieItem.getReleaseDate().equals(dateNow)){
                            String title = "Today Release";
                            String message = movieItem.getTitle();
                            sendNotification(context, title, message, NOTIFICATION_ID);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                getReleasedToday(context);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, UpcomingReminderReceive.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

}
