package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.TuVung;

import static com.example.btl_toeic.ActivityChonChuDeTuMoi.chuDeAdapter;
import static com.example.btl_toeic.ActivityChonChuDeTuMoi.dscd;
import static com.example.btl_toeic.ActivityMuctieu.mucTieuTuMoi;
import static com.example.btl_toeic.MainActivity.dataBaseTuVung;
import static com.example.btl_toeic.MainActivity.dstv;

public class ActivityHoctumoi extends AppCompatActivity {

    int i=0;
    public static int demsoluongtumoidahoc=0;
    TextView txtTuMoi,txtNghiaCuaTu;
    ImageView imgMoTa;
    Button btnShowKetQua,btnTiep,btnDaHoc;
    ImageButton btnPhatAm, btnGhiChu;
    MediaPlayer mediaPlayer;
    String tenchude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoctumoi);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                txtNghiaCuaTu.setText("");
                imgMoTa.setImageResource(0);
                HocTuMoi();
            }
        });

    }

    private void addControls() {
        txtNghiaCuaTu = findViewById(R.id.txtNghiaCuaTu);
        txtTuMoi=findViewById(R.id.txtTuMoi);
        imgMoTa=findViewById(R.id.imgMoTa);
        btnShowKetQua=findViewById(R.id.btnShowKetQua);
        btnTiep = findViewById(R.id.btnTiep);
        btnPhatAm = findViewById(R.id.btnPhatAm);
        btnDaHoc=findViewById(R.id.btnDaHoc);
        btnGhiChu=findViewById(R.id.btnGhiChu);

        Intent intent = getIntent();
        tenchude=intent.getStringExtra("TENCHUDE");
        //Lay tu moi dau tien
        HocTuMoi();
    }

    private void HocTuMoi() {
        //L???y l???n l?????t t??? v???ng t??? v??? tr?? i=0 n???u kh??ng th???a m??n t??ng i v?? ????? quy
        TuVung tv = dstv.get(i);
        if ((!tv.isDahoc())&&(tv.getChude().equalsIgnoreCase(tenchude))){
            //Lay tu moi
            txtTuMoi.setText(tv.getTiengAnh());

            //Phat am thanh
            btnPhatAm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer = MediaPlayer.create(ActivityHoctumoi.this,tv.getPhatam());
                    mediaPlayer.start();
                }
            });


            btnShowKetQua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtNghiaCuaTu.setText(tv.getTiengViet()+"\n\n\n"+tv.getMota());
                    imgMoTa.setImageResource(tv.getImgmota());
                    mediaPlayer = MediaPlayer.create(ActivityHoctumoi.this,tv.getPhatam());
                    mediaPlayer.start();
                }
            });

            btnDaHoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setDahoc(true);
                    demsoluongtumoidahoc++;
                    dataBaseTuVung.QueryData("UPDATE TuVung SET dahoc="+1+" WHERE TiengAnh='"+tv.getTiengAnh()+"';");
                    KiemTraMucTieuHocTuMoi();
                    KiemTraHocHetTuMoi();
                }
            });

            btnGhiChu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ActivityHoctumoi.this,ActivityGhiChu.class);
                    intent.putExtra("GHICHU",tv.getGhichu());
                    intent.putExtra("VITRI",i);
                    startActivity(intent);
                }
            });

            //c???p nh???t l???i th???ng k?? ??? Activity ch???n ch??? ????? m???i
            dscd.clear();
            ActivityChonChuDeTuMoi.XuLyThongKeHocDuoc();
            chuDeAdapter.notifyDataSetChanged();
        }else {
            i++;
            HocTuMoi();
        }
}

    private void KiemTraHocHetTuMoi() {
        if(i==(dstv.size()-1)){
            Intent intent = new Intent(ActivityHoctumoi.this,ActivityChucmung.class);
            intent.putExtra("CHUCMUNG","Ch??c M???ng \n b???n ???? h???c h???t t??? m???i Toiec!");
            startActivity(intent);
        }
    }

    private void KiemTraMucTieuHocTuMoi() {
        if((demsoluongtumoidahoc==mucTieuTuMoi) && (demsoluongtumoidahoc>0)){
            Intent intent = new Intent(ActivityHoctumoi.this,ActivityChucmung.class);
            intent.putExtra("CHUCMUNG","Ch??c M???ng \n b???n ???? ?????t m???c ti??u h???c t??? m???i l???n h???c n??y!");
            startActivity(intent);
        }
    }
}