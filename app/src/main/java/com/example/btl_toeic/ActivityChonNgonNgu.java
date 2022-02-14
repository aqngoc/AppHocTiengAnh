package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.RadialGradient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class ActivityChonNgonNgu extends AppCompatActivity {
    RadioButton radTiengAnh,radTiengViet;
    Button btnLuu;
    TextView tvNgonNgu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_ngon_ngu);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnLuu=findViewById(R.id.btnLuu);
        radTiengAnh=findViewById(R.id.radTiengAnh);
        radTiengViet=findViewById(R.id.radTiengViet);
        tvNgonNgu=findViewById(R.id.tvNgonNgu);

        tvNgonNgu.setText(getResources().getText(R.string.ngon_ngu));
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radTiengAnh.isChecked()){
                    XuLyNgonNguTiengAnh();
                }else{
                    XuLyNgonNguTiengViet();
                }
            }
        });
    }

    private void XuLyNgonNguTiengViet() {
        thaydoingonngu("vi");
        refeshLayout();
        LuuNgonNgu("vi");
    }

    private void XuLyNgonNguTiengAnh() {
        thaydoingonngu("en");
        refeshLayout();
        LuuNgonNgu("en");
    }

    private void thaydoingonngu(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
    }

    private void LuuNgonNgu(String lang) {
        SharedPreferences sharedPreferences =getSharedPreferences("BTLTOEIC",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NGONNGU",lang);
        editor.commit();
    }

    //hàm refesh ngôn ngữ mà ko cần recreat() activity;
    private void refeshLayout(){
        Intent intent = getIntent();
//        overridePendingTransition(0, 0);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
//        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}