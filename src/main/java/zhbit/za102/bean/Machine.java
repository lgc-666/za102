package zhbit.za102.bean;

import java.io.Serializable;
import java.util.Date;

public class Machine implements Serializable {
    private Integer mid;

    private String machineid;

    private String adress;

    private String status;

    private Integer leastrssi;

    private Date beat;

    private String x;

    private String y;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid == null ? null : machineid.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getLeastrssi() {
        return leastrssi;
    }

    public void setLeastrssi(Integer leastrssi) {
        this.leastrssi = leastrssi;
    }

    public Date getBeat() {
        return beat;
    }

    public void setBeat(Date beat) {
        this.beat = beat;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }
}