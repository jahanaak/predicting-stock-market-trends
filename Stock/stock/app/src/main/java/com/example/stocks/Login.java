package com.example.stocks;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText e1,e2;
    Button b1;
    TextView t2;
    String uname,pass;
    public static String log_id,user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=findViewById(R.id.uname);
        e2=findViewById(R.id.pass);
        t2=findViewById(R.id.textView8);
        b1=findViewById(R.id.btn2);
//        b2=findViewById(R.id.button3);
        t2.setOnClickListener(this);
        b1.setOnClickListener(this);
//        stopService(new Intent(getApplicationContext(),Requestforservices.class));
    }

    @Override
    public void onClick(View v) {

        if (v == b1) {
            String username = e1.getText().toString();
            String password = e2.getText().toString();
            if (username.length() == 0) {
                e1.setError("");
            } else if (password.length() == 0) {
                e2.setError("");
            } else {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                sh.getString("ip","");
                sh.getString("ip", "");
                String url = "http://" + sh.getString("ip", "") + "/api/login_here";
                VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,

                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                try {


                                    JSONObject obj = new JSONObject(new String(response.data));

                                    if (obj.getString("status").equals("ok")) {
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", obj.getString("lid"));
                                        ed.commit();
                                        Toast.makeText(getApplicationContext(), " success", Toast.LENGTH_SHORT).show();

//                                        startService(new Intent(getApplicationContext(),Requestforservices.class));

                                        Intent i = new Intent(getApplicationContext(), user_home.class);
                                        startActivity(i);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        params.put("username", username);//passing to python
                        params.put("password", password);//passing to python


                        return params;
                    }

                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();

                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);

            }

        } else {
            Intent h = new Intent(getApplicationContext(), signup.class);
            startActivity(h);

        }


    }
}