package Model;

import android.widget.ImageView;

public class TuVung {
    private String tiengAnh;
    private String tiengViet;
    private String mota;
    private int imgmota;
    private int phatam;
    private boolean dahoc;
    private String chude;
    private String ghichu;

    public String getChude() {
        return chude;
    }

    public void setChude(String chude) {
        this.chude = chude;
    }

    public TuVung(String tiengAnh, String tiengViet, String mota, int imgmota, int phatam, boolean dahoc, String chude, String ghichu) {
        this.tiengAnh = tiengAnh;
        this.tiengViet = tiengViet;
        this.mota = mota;
        this.imgmota = imgmota;
        this.dahoc = dahoc;
        this.phatam = phatam;
        this.chude = chude;
        this.ghichu = ghichu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }



    public String getTiengAnh() {
        return tiengAnh;
    }

    public int getPhatam() {
        return phatam;
    }

    public void setPhatam(int phatam) {
        this.phatam = phatam;
    }

    public TuVung() {
    }

    public boolean isDahoc() {
        return dahoc;
    }

    public void setDahoc(boolean dahoc) {
        this.dahoc = dahoc;
    }



    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getTiengViet() {
        return tiengViet;
    }

    public void setTiengViet(String tiengViet) {
        this.tiengViet = tiengViet;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getImgmota() {
        return imgmota;
    }

    public void setImgmota(int imgmota) {
        this.imgmota = imgmota;
    }
}
