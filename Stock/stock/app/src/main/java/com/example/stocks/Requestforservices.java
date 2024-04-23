package com.example.stocks;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class Requestforservices extends Service implements JsonResponse{

    private static final int REQUEST_INTERVAL = 30000; // Request interval in milliseconds (60 seconds)
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
        Toast.makeText(this, "Request Sent", Toast.LENGTH_SHORT).show();

        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Requestforservices.this;
//        String q = "/ViewReview?turf_id="+ViewTurf.turf_ids+"&login_id="+Login.lid;
        String q = "/predictout";
        q=q.replace(" ", "%20");
        JR.execute(q);
    }

    public void response(JSONObject jo) {
        // TODO Auto-generated method stub


    }

}
