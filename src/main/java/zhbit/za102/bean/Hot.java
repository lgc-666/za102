package zhbit.za102.bean;

import java.io.Serializable;
import java.util.Date;

public class Hot implements Serializable {
    private Integer hotid;

    private String x;

    private String y;

    private String adress;

    private Date timeStart;

    private Date timeEnd;

    public Integer getHotid() {
        return hotid;
    }

    public void setHotid(Integer hotid) {
        this.hotid = hotid;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }
}