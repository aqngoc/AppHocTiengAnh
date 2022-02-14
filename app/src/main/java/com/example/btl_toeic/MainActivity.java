package com.example.btl_toeic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

import SQLite.DataBaseTuVung;
import Model.TuVung;
import adapter.ChucNangAdapter;

import static com.example.btl_toeic.ActivityMuctieu.mucTieuOnTap;
import static com.example.btl_toeic.ActivityMuctieu.mucTieuTuMoi;

public class MainActivity extends AppCompatActivity {
    Button btnAppName;
    ListView lvDanhSachChucNang;
    ArrayList<String> DanhSachChucNang;
    ChucNangAdapter chucNangAdapter;
    public static DataBaseTuVung dataBaseTuVung;
    public static ArrayList<TuVung> dstv;
    //khai báo tên CSDL
    String DB_NAME="BTLANDROID.sqlite";
    //khai báo đường dẫn tới nơi lưu CSDL
    String DB_PATH="/databases/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        LayNgonNgu();
        //
        KiemTraDeLayDataBaseTuVungLanDau();

        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void KiemTraDeLayDataBaseTuVungLanDau() {
        SharedPreferences sharedPreferences = getSharedPreferences("BTLTOEIC",MODE_PRIVATE);
        boolean kiemtracaidatlandau = sharedPreferences.getBoolean("KIEMTRACAIDATLANDAU",true);
        if (kiemtracaidatlandau==true){
            XuLySaoChepCoSoDuLieuTuAssets();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("KIEMTRACAIDATLANDAU",false);
            editor.commit();
        }
    }

    private void addEvents() {
        lvDanhSachChucNang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chucnangduocchon = DanhSachChucNang.get(position);
                if (chucnangduocchon==(getResources().getText(R.string.hoc_tu_moi).toString())){
                    XuLyChucNangHocTuMoi();
                }
                if(chucnangduocchon==(getResources().getText(R.string.on_tap).toString())){
                    XuLyChucNangOnTap();
                }
                if(chucnangduocchon==(getResources().getText(R.string.tra_tu).toString())){
                    XuLyChucNangTraTu();
                }
                if(chucnangduocchon==(getResources().getText(R.string.muc_tieu).toString())){
                    XuLyChucNangMucTieu();
                }
                if (chucnangduocchon==(getResources().getText(R.string.phan_hoi).toString())){
                    XuLyChucNangPhanHoi();
                }
                if(chucnangduocchon==(getResources().getText(R.string.thoatt).toString())){
//                    LuuDuLieu();
                    System.exit(0);

                }
            }
        });
        btnAppName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
    }
    private void addControls() {
        btnAppName=findViewById(R.id.btnAppName);
        dataBaseTuVung = new DataBaseTuVung(this,DB_NAME,null,1);
        dstv = new ArrayList<TuVung>();
//        dstv.add(new TuVung("Opligate","Bắt buộc","jagljsfhgksjfdhg",R.drawable.opligate,R.raw.opligate,false));
//        dstv.add(new TuVung("Abide by","Tuân theo","jaggfgfgfhgksjfdhg",R.drawable.abideby,R.raw.abideby,false));
//        dstv.add(new TuVung("Teacher","Giáo Viên","jagljsfhgksjfdhg",R.drawable.opligate,R.raw.opligate,false));
//        dstv.add(new TuVung("Student","Học Sinh","jaggfgfgfhgksjfdhg",R.drawable.abideby,R.raw.abideby,false));

        HienThiDanhSachChucNang();
        LayDuLieuTuBangDuLieuTuVung();
        LayGiaTriMucTieu();
    }

//    -----------------------Khu thiết lập ngôn ngữ khi bắt đầu chạy lại ứng dụng-------------------
    private void LayNgonNgu() {
        SharedPreferences sharedPreferences =getSharedPreferences("BTLTOEIC",MODE_PRIVATE);
        String nn = sharedPreferences.getString("NGONNGU","vi");
        thaydoingonngu(nn);
    }
    private void thaydoingonngu(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
    }

//    ----------------------Khu xử lý CSDL-----------------------------------
    private void XuLySaoChepCoSoDuLieuTuAssets() {
        File dbFile = getDatabasePath(DB_NAME);
        if(!dbFile.exists()){
            SaoChepCoSoDuLieuTuAssets();
        }else {
            dbFile.delete();
            SaoChepCoSoDuLieuTuAssets();
            //Nếu file chưa có thì chép vào, có rồi thì xóa đi chép lại
        }
    }

    private void SaoChepCoSoDuLieuTuAssets() {
        try {
            //lấy nội dung trong file sqlite
            InputStream myInput = getAssets().open(DB_NAME);
            //lay duong dan de sao chep vào
            String outFileName = getApplicationInfo().dataDir+DB_PATH+DB_NAME;

            //Kiem tra file do da ton tai hay chua
            File f = new File(getApplicationInfo().dataDir+DB_PATH);
            if (!f.exists()){
                f.mkdir();
            }
            //Mo mot CSDL rong la InputStream
            //Chep du lieu tu myInput vao myOutput
            OutputStream myOutPut = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int len;
            //lan doc buffer
            while ((len=myInput.read(buffer))>0){
                //ghi vao myOutput
                myOutPut.write(buffer,0,len);
            }
            myOutPut.flush();
            //lam rong file myOutput
            myInput.close();
            myOutPut.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void LayDuLieuTuBangDuLieuTuVung() {
        boolean daHoc;
        Cursor cursor = dataBaseTuVung.GetData("SELECT * FROM TuVung");
        while (cursor.moveToNext()){
            String tiengAnh = cursor.getString(0);
            String tiengViet = cursor.getString(1);
            String moTa = cursor.getString(2);
            String imgMoTaTam = cursor.getString(3);
            String phatAmTam = cursor.getString(4);
            int imgMoTa = getResId(imgMoTaTam,R.drawable.class);
            int phatAm = getResId(phatAmTam,R.raw.class);
            int dh = cursor.getInt(5);
            if (dh==0){
                daHoc = false;
            }else  daHoc=true;
            String chuDe = cursor.getString(6);
            String ghiChu = cursor.getString(7);

            dstv.add(new TuVung(tiengAnh,tiengViet,moTa,imgMoTa,phatAm,daHoc,chuDe,ghiChu));
        }
    }

    // chuyển string thành id hệ thống
    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

//    private void TaoBangDuLieuTuVung() {
//        dataBaseTuVung = new DataBaseTuVung(this,DB_NAME,null,1);
//        dataBaseTuVung.QueryData("CREATE TABLE IF NOT EXISTS TuVung(TiengAnh VARCHAR(50),TiengViet VARCHAR(50)," +
//                "MoTa VARCHAR(50), ImgMoTa VARCHAR(50), PhatAm VARCHAR(50), DaHoc INTEGER)");
//    }

    private void LuuDuLieu() {
        for(TuVung tv:dstv){
            int dh;
            if (tv.isDahoc()==false){
                dh=0;
            }else dh=1;
            dataBaseTuVung.QueryData("INSERT INTO TuVung VALUES('"+tv.getTiengAnh()+"','"+tv.getTiengViet()+"','"+
                    tv.getMota()+"',"+tv.getImgmota()+","+tv.getPhatam()+","+dh+")");

        }
    }


//    ----------------- Khu xử lý chức năng-----------------------------------------------------------------
    private void XuLyChucNangPhanHoi() {
        Intent intent = new Intent(MainActivity.this,ActivityPhanHoi.class);
        startActivity(intent);
    }

    private void XuLyChucNangMucTieu() {
        Intent intent = new Intent(MainActivity.this,ActivityMuctieu.class);
        startActivity(intent);
    }


    private void XuLyChucNangTraTu() {
        Intent intent = new Intent(MainActivity.this,ActivityTratu.class);
        startActivity(intent);
    }

    private void XuLyChucNangOnTap() {
        Intent intent = new Intent(MainActivity.this,ActivityOntap.class);
        startActivity(intent);
    }

    private void XuLyChucNangHocTuMoi() {
        Intent intent = new Intent(MainActivity.this,ActivityChonChuDeTuMoi.class);
        startActivity(intent);
    }


    private void LayGiaTriMucTieu() {
        SharedPreferences sharedPreferences =getSharedPreferences("BTLTOEIC",MODE_PRIVATE);
        mucTieuTuMoi = sharedPreferences.getInt("MUCTIEUTUMOI",0);
        mucTieuOnTap = sharedPreferences.getInt("MUCTIEUONTAP",0);
    }


    private void HienThiDanhSachChucNang() {
        lvDanhSachChucNang = findViewById(R.id.lvDanhSachChucNang);
        DanhSachChucNang = new ArrayList<String>();
        DanhSachChucNang.add(getResources().getText(R.string.tra_tu).toString());
        DanhSachChucNang.add(getResources().getText(R.string.hoc_tu_moi).toString());
        DanhSachChucNang.add(getResources().getText(R.string.on_tap).toString());
        DanhSachChucNang.add(getResources().getText(R.string.muc_tieu).toString());
        DanhSachChucNang.add(getResources().getText(R.string.phan_hoi).toString());
        DanhSachChucNang.add(getResources().getText(R.string.thoatt).toString());


        chucNangAdapter = new ChucNangAdapter(MainActivity.this,R.layout.layoutchucnang,DanhSachChucNang);
        lvDanhSachChucNang.setAdapter(chucNangAdapter);
    }

//    --------------------------------Khu tạo Menu-------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btnThongTinApp){
            XuLyMenuThongTinApp();
        }else{
            if (item.getItemId()==R.id.btnNgonNgu){
                XuLyMenuNgonNgu();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void XuLyMenuNgonNgu() {
        Intent intent = new Intent(MainActivity.this,ActivityChonNgonNgu.class);
        startActivity(intent);
    }

    private void XuLyMenuThongTinApp() {
        Intent intent = new Intent(MainActivity.this,ActivityThongTinApp.class);
        startActivity(intent);
    }
}