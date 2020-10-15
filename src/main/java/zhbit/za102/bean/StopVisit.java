package zhbit.za102.bean;

import java.io.Serializable;
import java.util.Date;

public class StopVisit implements Serializable {
    private Integer stopVisitId;

    private String address;

    private Integer injudge;

    private Date inTime;

    private Date leftTime;

    private String rt;

    private Integer visitedTimes;

    private Date beat;

    private Integer handlejudge;

    private String mac;

    private Integer rssi;

    public Integer getStopVisitId() {
        return stopVisitId;
    }

    public void setStopVisitId(Integer stopVisitId) {
        this.stopVisitId = stopVisitId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getInjudge() {
        return injudge;
    }

    public void setInjudge(Integer injudge) {
        this.injudge = injudge;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Date leftTime) {
        this.leftTime = leftTime;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt == null ? null : rt.trim();
    }

    public Integer getVisitedTimes() {
        return visitedTimes;
    }

    public void setVisitedTimes(Integer visitedTimes) {
        this.visitedTimes = visitedTimes;
    }

    public Date getBeat() {
        return beat;
    }

    public void setBeat(Date beat) {
        this.beat = beat;
    }

    public Integer getHandlejudge() {
        return handlejudge;
    }

    public void setHandlejudge(Integer handlejudge) {
        this.handlejudge = handlejudge;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }
}