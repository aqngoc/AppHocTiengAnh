package com.example.btl_toeic;
//da comment
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import Model.ChuDe;
import adapter.ChuDeAdapter;

import static com.example.btl_toeic.MainActivity.dataBaseTuVung;

public class ActivityChonChuDeTuMoi extends AppCompatActivity {
    GridView gvChuDe;
    public static ChuDeAdapter chuDeAdapter;
    public static ArrayList<ChuDe> dscd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chu_de_tu_moi);
        addControls();
        addEvents();
    }

    private void addEvents() {
        gvChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityChonChuDeTuMoi.this,ActivityHoctumoi.class);
                String tenchude = dscd.get(position).getTenChuDe();
                intent.putExtra("TENCHUDE",tenchude);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        gvChuDe = findViewById(R.id.gvChuDe);
        gvChuDe.setNumColumns(2);
        gvChuDe.setHorizontalSpacing(100);
        gvChuDe.setVerticalSpacing(100);
        dscd = new ArrayList<ChuDe>();

        XuLyThongKeHocDuoc();
        chuDeAdapter = new ChuDeAdapter(ActivityChonChuDeTuMoi.this,R.layout.layoutchude,dscd);
        gvChuDe.setAdapter(chuDeAdapter);
    }

    public static void XuLyThongKeHocDuoc() {
        Cursor cursor = dataBaseTuVung.GetData("SELECT DISTINCT ChuDe FROM TuVung");
        while (cursor.moveToNext()){
            String tencd = cursor.getString(0);
            int tong=0,sotuhoc = 0;
            Cursor cursortongtu = dataBaseTuVung.GetData("SELECT count(*) FROM TuVung WHERE ChuDe='"+tencd+"'");
            while (cursortongtu.moveToNext()) {
                tong = cursortongtu.getInt(0);
            }
            Cursor cursorsotuhoc = dataBaseTuVung.GetData("SELECT count(*) FROM TuVung WHERE ChuDe='"+tencd+"' AND DaHoc=1;");
            while (cursorsotuhoc.moveToNext()){
                sotuhoc = cursorsotuhoc.getInt(0);
            }
            String hocduoc=sotuhoc +"/"+tong;
            dscd.add(new ChuDe(tencd,hocduoc));
        }
    }
}
