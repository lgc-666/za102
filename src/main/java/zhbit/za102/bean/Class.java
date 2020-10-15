package zhbit.za102.bean;

import java.io.Serializable;

public class Class implements Serializable {
    private Integer classid;

    private String adress;

    private String x1;

    private String y1;

    private String x2;

    private String y2;

    private Integer stopjudge;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1 == null ? null : x1.trim();
    }

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1 == null ? null : y1.trim();
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2 == null ? null : x2.trim();
    }

    public String getY2() {
        return y2;
    }

    public void setY2(String y2) {
        this.y2 = y2 == null ? null : y2.trim();
    }

    public Integer getStopjudge() {
        return stopjudge;
    }

    public void setStopjudge(Integer stopjudge) {
        this.stopjudge = stopjudge;
    }
}