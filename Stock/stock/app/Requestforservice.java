package com.example.stocks;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;


public class Requestforservice extends Service {


    private static final int REQUEST_INTERVAL = 60000; // Request interval in milliseconds (60 seconds)
    private Handler handler;
    private Runnable requestRunnable;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        requestRunnable = new Runnable() {
            @Override
            public void run() {
                // Perform your request here
                sendRequestToServer();
                handler.postDelayed(this, REQUEST_INTERVAL);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(requestRunnable);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(requestRunnable);
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendRequestToServer() {
        // Code to send request to server goes here
        // This method will be called repeatedly based on the REQUEST_INTERVAL
        // For demonstration purposes, I'm showing a toast message
        Toast.makeText(this, "Request Sent to Server", Toast.LENGTH_SHORT).show();
    }
}

