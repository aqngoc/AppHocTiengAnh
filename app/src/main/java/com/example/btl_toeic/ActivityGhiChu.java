package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.TuVung;

import static com.example.btl_toeic.MainActivity.dataBaseTuVung;
import static com.example.btl_toeic.MainActivity.dstv;

public class ActivityGhiChu extends AppCompatActivity {
    EditText txtGhiChu;
    Button btnLuu;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghi_chu);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuVung tv = dstv.get(i);
                dataBaseTuVung.QueryData("UPDATE TuVung SET GhiChu='"+ txtGhiChu.getText().toString() +"' WHERE TiengAnh='"+tv.getTiengAnh()+"';");
                tv.setGhichu(txtGhiChu.getText().toString());
                dstv.set(i,tv);
                finish();
            }
        });
    }

    private void addControls() {
        txtGhiChu=findViewById(R.id.txtGhiChu);
        btnLuu=findViewById(R.id.btnLuu);
        Intent intent = getIntent();
        String ghichu=intent.getStringExtra("GHICHU");
        i=intent.getIntExtra("VITRI",0);
        txtGhiChu.setText(ghichu);
    }
}