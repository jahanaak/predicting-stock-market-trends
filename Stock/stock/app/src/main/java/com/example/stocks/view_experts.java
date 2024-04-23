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

public class view_experts extends AppCompatActivity {

    ListView l2;
    String[] fname,lname,place,post,pin,phone,email,value,eids;
    SharedPreferences sh;
    public static String eid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experts);

        l2=(ListView) findViewById(R.id.listv);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ip","");
        String url = "http://" + sh.getString("ip", "") + "/api/v_experts";

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject jsonObj = new JSONObject(new String(response.data));
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                eids = new String[js.length()];
                                fname = new String[js.length()];
                                lname = new String[js.length()];
                                place = new String[js.length()];
                                post=new String[js.length()];
                                pin= new String[js.length()];
                                phone = new String[js.length()];
                                email = new String[js.length()];
                                value = new String[js.length()];

                                for (int i = 0; i < js.length(); i++) {

                                    JSONObject u = js.getJSONObject(i);
                                    eids[i] = u.getString("expert_id");//dbcolumn name in double quotes
                                    fname[i] = u.getString("firstname");//dbcolumn name in double quotes
                                    lname[i] = u.getString("lastname");
                                    place[i] = u.getString("place");//dbcolumn name in double quotes
                                    post[i] = u.getString("post");
                                    pin[i] = u.getString("pin");
                                    phone[i] = u.getString("phone");
                                    email[i] = u.getString("email");
                                    value[i] = "\nfname : " + fname[i] +"\nlname : " + lname[i] +"\nPlace : " + place[i] + lname[i] +"\nPost : " + post[i]+ "\nPin : " + pin[i]+ "\nphone : " + phone[i]+"\nemail : " + email[i];
                                }

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
                return params;
            }

        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
//        ++++++++++++++++++++++add+++

        l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                eid=eids[i];
                Toast.makeText(getApplicationContext(),"eid"+eids, Toast.LENGTH_LONG).show();

                SharedPreferences.Editor ed = sh.edit();
                ed.putString("login_id", sh.getString("lid",""));
                ed.putString("eid",eid);
                ed.commit();

                final CharSequence[] items1 = {"ask","rate","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(view_experts.this);

                builder.setItems(items1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items1[item].equals("ask")) {

                            Intent j = new Intent(getApplicationContext(), ask_doubts.class);
                            startActivity(j);

                            Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
                        } else if (items1[item].equals("rate")) {

                            Intent j=new Intent(getApplicationContext(),Review.class);
                            startActivity(j);

                            Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);

                        }else if (items1[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }

        });


    }
}