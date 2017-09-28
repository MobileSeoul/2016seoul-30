package com.justacomm.gyungjobi.gyungjobi.common;


public class ListViewItem {
    private String title ;
    private String eventInfo ;
    private String cost ;
    private String sn;

    public void setTitle(String title) {
        this.title = title ;
    }
    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo ;
    }
    public void setCost(String cost) {
        this.cost = cost ;
    }
    public void setSn(String sn) {
        this.sn = sn ;
    }

    public String getTitle() {
        return this.title ;
    }
    public String getEventInfo() {
        return this.eventInfo ;
    }
    public String getCost() {
        return this.cost ;
    }
    public String getSn() {
        return this.sn ;
    }

}