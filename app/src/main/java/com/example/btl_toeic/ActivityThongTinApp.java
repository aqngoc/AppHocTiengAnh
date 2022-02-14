package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityThongTinApp extends AppCompatActivity {
    TextView txtThongTinApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_app);
        txtThongTinApp=findViewById(R.id.txtThongTinApp);

        txtThongTinApp.setText("Môn học: Lập trình Android \n\n" +
                "Tên App: Toiec PTIT \n\n"+
                "Giảng Viên: Nguyễn Hoàng Anh \n\n" +
                "Sinh Viên: An Quang Ngọc \n\n" +
                "MSV: B17DCAT135 \n\n");
    }
}