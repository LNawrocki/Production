package pl.ln.classes;

import java.util.List;

public class Order {
    private String orderNumber = "";
    private String orderID = "";
    private String client = "";
    private String agent = "";
    private String delDate = "";
    private String quality = "";
    private String country = "";
    private String delType = "";
    private String finalDest = "";
    private String additionalInfo = "";
    private List<Pos> pos;

    public String getOrderNumber() {
        return orderNumber;
    }

    public List<Pos> getPos() {
        return pos;
    }
    public void setPos(List<Pos> pos) {
        this.pos = pos;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public String getDelDate() {
        return delDate;
    }
    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDelType() {
        return delType;
    }
    public void setDelType(String delType) {
        this.delType = delType;
    }
    public String getFinalDest() {
        return finalDest;
    }
    public void setFinalDest(String finalDest) {
        this.finalDest = finalDest;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderID='" + orderID + '\'' +
                ", client='" + client + '\'' +
                ", agent='" + agent + '\'' +
                ", delDate='" + delDate + '\'' +
                ", quality='" + quality + '\'' +
                ", country='" + country + '\'' +
                ", delType='" + delType + '\'' +
                ", finalDest='" + finalDest + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", pos=" + pos +
                '}';
    }
}
