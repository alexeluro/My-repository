package com.example.hp.cryptocurrencydetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView resultText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        imageView = findViewById(R.id.imageView);
//        imageView.setImageResource(R.drawable.dmitry_unsplash);
        resultText = findViewById(R.id.result_text);
        resultText.setText(getIntent().getStringExtra("result"));

        Toast.makeText(this, "Success!!!", Toast.LENGTH_SHORT);
    }
}
