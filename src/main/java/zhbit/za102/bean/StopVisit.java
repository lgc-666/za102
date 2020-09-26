package zhbit.za102.bean;

public class StopVisit {
    private Integer stopVisitId;

    private String address;

    private Integer injudge;

    private String inTime;

    private String leftTime;

    private String rt;

    private Integer visitedTimes;

    private String beat;

    private Integer handlejudge;

    private String mac;

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

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime == null ? null : inTime.trim();
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime == null ? null : leftTime.trim();
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

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat == null ? null : beat.trim();
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
}