package zhbit.za102.bean;

import java.io.Serializable;
import java.util.Date;

public class Location implements Serializable {
    private Integer locationid;

    private String mac;

    private String adress;

    private String x;

    private String y;

    private Date beat;

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
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

    public Date getBeat() {
        return beat;
    }

    public void setBeat(Date beat) {
        this.beat = beat;
    }
}