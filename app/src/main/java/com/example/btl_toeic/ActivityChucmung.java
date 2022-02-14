package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityChucmung extends AppCompatActivity {
    TextView txtChucMung;
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucmung);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        btnOk = findViewById(R.id.btnOk);
        txtChucMung=findViewById(R.id.txtChucMung);
        Intent intent =getIntent();
        String chucmung = intent.getStringExtra("CHUCMUNG");
        txtChucMung.setText(chucmung);

    }
}