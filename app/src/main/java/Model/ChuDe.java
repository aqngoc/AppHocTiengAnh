package Model;

public class ChuDe {
    private String tenChuDe;
    private String hocDuoc;

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public ChuDe() {
    }

    public ChuDe(String tenChuDe, String hocDuoc) {
        this.tenChuDe = tenChuDe;
        this.hocDuoc = hocDuoc;
    }

    public String getHocDuoc() {
        return hocDuoc;
    }

    public void setHocDuoc(String hocDuoc) {
        this.hocDuoc = hocDuoc;
    }
}
