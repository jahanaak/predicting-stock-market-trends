package com.example.stocks;


import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ProgressBar;

        import androidx.appcompat.app.AppCompatActivity;

public class Progressbaractivity extends AppCompatActivity {

    private ProgressBar loadingPB;
    boolean isProgressVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbaractivity);

        loadingPB = findViewById(R.id.idPBLoading);
        loadingPB.setVisibility(View.VISIBLE);


    }

    }
