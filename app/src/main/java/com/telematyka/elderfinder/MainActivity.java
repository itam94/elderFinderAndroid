package com.telematyka.elderfinder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    private Intent gpsintent;
    private boolean currentlyTracking;
    private AlarmManager alarmManager;
    private Intent gpsTrackerIntent;
    private PendingIntent pendingIntent;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // gpsintent = new Intent(getApplicationContext(),GPSTrackingService.class);
       // startService = false;
        button = (Button) findViewById(R.id.startService);


    }

    public void addMember(View view) {
    }

    public void checkLastPositions(View view) {
    }

    public void startService(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!checkIfGooglePlayEnabled()) {
            return;
        }

        if (currentlyTracking) {
            cancelAlarmManager();

            currentlyTracking = false;
            editor.putBoolean("currentlyTracking", false);
        } else {
            startAlarmManager();

            currentlyTracking = true;
            editor.putBoolean("currentlyTracking", true);
            editor.putFloat("totalDistanceInMeters", 0f);
            editor.putBoolean("firstTimeGettingPosition", true);

        }

        editor.apply();
        setTrackingButtonState();
    }

    private void setTrackingButtonState() {
        if(currentlyTracking) {
            button.setText(R.string.slave);
            button.setBackgroundColor(Color.GREEN);
        }else{

            button.setText(R.string.master);
            button.setBackgroundColor(Color.BLUE);
        }
    }

    private boolean checkIfGooglePlayEnabled() {
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            return true;
        } else {
            Log.e("google", "unable to connect to google play services.");
            Toast.makeText(getApplicationContext(), "Service unavailable", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void cancelAlarmManager() {
        Log.d("State", "cancelAlarmManager");

        Context context = getBaseContext();
        Intent gpsTrackerIntent = new Intent(context, GpsTrackerAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, gpsTrackerIntent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
    private void startAlarmManager() {
        Log.d("State", "startAlarmManager");

        Context context = getBaseContext();
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        gpsTrackerIntent = new Intent(context, GpsTrackerAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, gpsTrackerIntent, 0);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.websmithing.gpstracker.prefs", Context.MODE_PRIVATE);


        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                15 * 60000, // 60000 = 1 minute
                pendingIntent);
    }
}
