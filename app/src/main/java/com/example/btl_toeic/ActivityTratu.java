package com.example.btl_toeic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Model.TuVung;

import static com.example.btl_toeic.MainActivity.dstv;

public class ActivityTratu extends AppCompatActivity {
    ImageButton btnPhatAmKetQuaTraTu,btnTraTu;
    AutoCompleteTextView autotxtTraTu;
    ImageView imgKetQuaTraTu;
    TextView txtKetQuaTraTu;
    ArrayList<String> dst;
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratu);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnPhatAmKetQuaTraTu = findViewById(R.id.btnPhatAmKetQuaTraTu);
        btnTraTu = findViewById(R.id.btnTraTu);
        autotxtTraTu = findViewById(R.id.autotxtTraTu);
        imgKetQuaTraTu = findViewById(R.id.imgKetQuaTraTu);
        txtKetQuaTraTu = findViewById(R.id.txtKetQuaTraTu);
        dst = new ArrayList<>();
        LayDanhSachTu();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dst);
        autotxtTraTu.setAdapter(arrayAdapter);
        autotxtTraTu.setThreshold(2);
    }

    private void LayDanhSachTu() {
        for (TuVung tv:dstv){
            dst.add(tv.getTiengAnh());
        }
    }

    private void addEvents() {
        btnTraTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(TuVung tv:dstv){
                    if (tv.getTiengAnh().equalsIgnoreCase(autotxtTraTu.getText().toString())){
                        txtKetQuaTraTu.setText(tv.getTiengViet()+"\n\n"+tv.getMota());
                        imgKetQuaTraTu.setImageResource(tv.getImgmota());

                        btnPhatAmKetQuaTraTu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityTratu.this,tv.getPhatam());
                                mediaPlayer.start();
                            }
                        });
                        break;
                    }
                }
            }
        });
    }
}