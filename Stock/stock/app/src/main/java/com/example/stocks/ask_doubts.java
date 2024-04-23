package com.example.stocks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class ask_doubts extends AppCompatActivity {

    EditText e1;
    Button b1;
    ListView l2;
    SharedPreferences sh;
    String[] dbt,reply,date,lid,value;
    String eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_doubts);

        l2=(ListView)findViewById(R.id.lst);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip","");
        String url = "http://" + sh.getString("ip", "") + "/api/v_dbt";

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>(){
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject jsonObj = new JSONObject(new String(response.data));
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(), "evida und", Toast.LENGTH_LONG).show();
                                JSONArray js = jsonObj.getJSONArray("data");//from python

                                dbt = new String[js.length()];
                                reply = new String[js.length()];
                                date = new String[js.length()];
//                                lid = new String[js.length()];

                                value = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
//                                    lid[i] = u.getString("login_id");//dbcolumn name in double quotes
                                    date[i] = u.getString("date");//dbcolumn name in double quotes
                                    dbt[i] = u.getString("doubt");
                                    reply[i] = u.getString("reply");

                                    value[i] = "\nDoubt : " + dbt[i] +"\nDate : " + date[i] +"\nreply : " + reply[i] ;
                                    Toast.makeText(getApplicationContext(), "evida ethi epo", Toast.LENGTH_LONG).show();
                                }

                                Toast.makeText(getApplicationContext(), "custom image ina kanduuu", Toast.LENGTH_LONG).show();
                                l2.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, value));
//
                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                SharedPreferences o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                params.put("login_id", sh.getString("lid",""));//passing to python
//                params.put("exp_id", sh.getString("eid",""));
                return params;
            }

        };
        Volley.newRequestQueue(this).add(volleyMultipartRequest);

        e1=findViewById(R.id.editTextTextdoubt);
        b1=findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name=e1.getText() .toString();


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                sh.getString("ip","");
                String url = "http://" + sh.getString("ip", "") + "/api/a_doubts";

                VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                        new Response.Listener<NetworkResponse>() {
                            @Override
                            public void onResponse(NetworkResponse response) {
                                try {



                                    JSONObject obj = new JSONObject(new String(response.data));

                                    if(obj.getString("status").equals("ok")){
//
                                        Toast.makeText(getApplicationContext(), " success", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), ask_doubts.class);
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"Registration failed" ,Toast.LENGTH_SHORT).show();
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
                        params.put("exp_id", sh.getString("eid",""));
                        params.put("login_id", sh.getString("lid",""));//passing to python

                        params.put("dbt", first_name);//passing to python


                        //passing to python


                        return params;
                    }
                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        //  long imagename = System.currentTimeMillis();
                        //     params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
            }
        });


    }
}