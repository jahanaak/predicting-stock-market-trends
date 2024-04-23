package com.example.stocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText a;
    Button r;
    SharedPreferences sh;
    public static String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        a = findViewById(R.id.editTextTextPersonName2);
        r = findViewById(R.id.button);
        r.setOnClickListener(this);
        a.setText(sh.getString("ip","192.168."));
    }

    @Override
    public void onClick(View view) {
        ip = a.getText().toString();
        if (ip.length() == 0) {
            a.setError("");
        } else {

            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip);
            ed.putString("url", "http://" + ip + ":5003");
            ed.commit();
//            startActivity(new Intent(getApplicationContext(),login.class));
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
    }
}