package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPhanHoi extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1999 ;
    Button btnGui;
    ImageButton btnGoi;
    TextView txtSoDienThoai;
    EditText txtNoiDungPhanHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnGui=findViewById(R.id.btnGui);
        btnGoi=findViewById(R.id.btnGoi);
        txtSoDienThoai=findViewById(R.id.txtSoDienThoai);
        txtNoiDungPhanHoi=findViewById(R.id.txtNoiDungPhanHoi);
    }

    private void addEvents() {
        btnGoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyGoi();
            }
        });
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyNhanTin();
            }
        });
    }

    private void XuLyNhanTin() {
        Uri uri = Uri.parse("smsto:" + txtSoDienThoai.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", txtNoiDungPhanHoi.getText().toString());
        try {
            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(ActivityPhanHoi.this, "Gửi tin nhắn không thành công! " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    private void XuLyGoi() {
        Uri uri = Uri.parse("tel:"+txtSoDienThoai.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
        //call_phone là quyền nguy hiểm cho nên từ SDK >23 và adroid >6.0 --> phải yêu cầu quyền lại khi ứng dụng chạy
        if (ContextCompat.checkSelfPermission(ActivityPhanHoi.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ActivityPhanHoi.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            try {
                startActivity(intent);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}