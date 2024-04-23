package com.example.stocks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class user_home extends AppCompatActivity implements JsonResponse {
//    Button ;
    CardView b1,b2,b3,b4,b5,b6;
    TextView t1;
    public static String x,y;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        // Set up the initial delay and interval
        int initialDelay = 0;  // delay in milliseconds
        int interval = 10000;  // interval in milliseconds (e.g., 10 seconds)

        // Create a Runnable to be executed periodically
        runnable = new Runnable() {
            @Override
            public void run() {
                // Perform your task here
                // This will be called every 'interval' milliseconds
                // Replace this with your code to send a request
                getRequest();

                // Re-schedule the task after 'interval' milliseconds
                handler.postDelayed(this, interval);
            }
        };

        // Schedule the task
        handler.postDelayed(runnable, initialDelay);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)user_home.this;
//        String q = "/ViewReview?turf_id="+ViewTurf.turf_ids+"&login_id="+Login.lid;
        String q = "/viewout";
        q=q.replace(" ", "%20");
        JR.execute(q);

        t1=(TextView)findViewById(R.id.out);
//        startService(new Intent(getApplicationContext(),Requestforservices.class));
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l=new Intent(getApplicationContext(),view_tips.class);
                startActivity(l);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(), view_notification.class);
                startActivity(k);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),view_experts.class);
                startActivity(k);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),complaint.class);
                startActivity(k);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),Login.class);
                startActivity(k);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),viewingraph.class);
                startActivity(j);
//                Intent k=new Intent(getApplicationContext(),viewingraph.class);
//                startActivity(k);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {

        try {
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("predictout"))
            {
                try{

                    String status=jo.getString("status");
                    Log.d("==========", status);
//                    Toast.makeText(getApplicationContext(),"helloooooooo   "+status, Toast.LENGTH_SHORT).show();

                    if(status.equalsIgnoreCase("ok")){

                        t1.setText("Stock Price is : "+jo.getString("data"));

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }

        }

        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void getRequest() {
        // Replace this with your code to make a network request
        // You can use libraries like Retrofit or AsyncTask for network operations
        // For simplicity, I'm just printing a message here
//        Log.d("MyActivity", "Making a network request...");

        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)user_home.this;
//        String q = "/ViewReview?turf_id="+ViewTurf.turf_ids+"&login_id="+Login.lid;
        String q = "/predictout";
        q=q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callbacks to prevent memory leaks
        handler.removeCallbacks(runnable);
    }
}