package com.example.stocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    SharedPreferences sh;
    String [] mydist={"low","average","high"};
    Spinner fdistrict;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1=(Button) findViewById(R.id.button4);
        e1=(EditText)findViewById(R.id.editTextTextPersonFName);
        e2=(EditText)findViewById(R.id.editTextTextPersonlName);
        e3=(EditText) findViewById(R.id.editTextTextPersonName5);
        e4=(EditText) findViewById(R.id.editTextTextPersonName8);
        e5=(EditText) findViewById(R.id.editTextPin);
        e6=(EditText)findViewById(R.id.editTextPhone);
        e7=(EditText)findViewById(R.id.editTextTextEmailAddress);
        e8=(EditText)findViewById(R.id.editTextTextPersonName6);
        e9=(EditText)findViewById(R.id.editTextTextPassword2);
        fdistrict=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adpt=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mydist);
        fdistrict.setAdapter(adpt);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name=e1.getText() .toString();
                String district=fdistrict.getSelectedItem().toString();
                String last_name= e2.getText() .toString();
                String post=e3.getText() .toString();
                String pin= e4.getText() .toString();
                String phone=e5.getText() .toString();
                String place= e6.getText() .toString();
                String email=e7.getText() .toString();
                String username=e8.getText() .toString();
                String password=e9.getText() .toString();

                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sh.getString("ip","");
                    String url = "http://" + sh.getString("ip", "") + "/api/signup";

            VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            try {



                                JSONObject obj = new JSONObject(new String(response.data));

                                if(obj.getString("status").equals("ok")){
    //
                                    Toast.makeText(getApplicationContext(), " success", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), Login.class);
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
                    params.put("user_id", sh.getString("user_id",""));//passing to python
                    params.put("login_id", sh.getString("lid",""));
                    params.put("type", district);
                    params.put("fname", first_name);//passing to python
                    params.put("lname", last_name);
                    params.put("place", place);
                    params.put("post", post);

                    params.put("pin", pin);
                    params.put("phone", phone);
                    params.put("email", email);
                    params.put("username", username);
                    params.put("password", password);


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