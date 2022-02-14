package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityMuctieu extends AppCompatActivity {
    EditText txtMucTieuTuMoi,txtMucTieuOnTap;
    Button btnLuu;
    public static int mucTieuTuMoi,mucTieuOnTap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muctieu);
        addControls();
        addEvents();


    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =getSharedPreferences("BTLTOEIC",MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                // luu giá trị mục tiêu
                mucTieuTuMoi=Integer.parseInt(txtMucTieuTuMoi.getText().toString());
                mucTieuOnTap=Integer.parseInt(txtMucTieuOnTap.getText().toString());
                editor.putInt("MUCTIEUTUMOI",mucTieuTuMoi);
                editor.putInt("MUCTIEUONTAP",mucTieuOnTap);
                editor.commit();
                finish();
            }
        });
    }

    private void addControls() {
        txtMucTieuOnTap=findViewById(R.id.txtMucTieuOnTap);
        txtMucTieuTuMoi=findViewById(R.id.txtMucTieuTuMoi);
        btnLuu=findViewById(R.id.btnLuu);

        HienThiGiaTriMucTieu();
    }

    private void HienThiGiaTriMucTieu() {
        txtMucTieuOnTap.setText(mucTieuOnTap+"");
        txtMucTieuTuMoi.setText(mucTieuTuMoi+"");
    }
}