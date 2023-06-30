package pl.ln.entity;

import pl.ln.entity.Pos;

import java.util.List;

public class Order {
    private String orderNumber = "";
    private String orderId = "";
    private String client = "";
    private String agent = "";
    private String deliveryDate = "";
    private String quality = "";
    private String country = "";
    private String deliveryType = "";
    private String finalDest = "";
    private String additionalInfo = "";
    private List<Pos> pos;
    private String orderDate = "";
    private String orderNo = "";

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

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
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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
    public String getDeliveryType() {
        return deliveryType;
    }
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
                ", orderID='" + orderId + '\'' +
                ", client='" + client + '\'' +
                ", agent='" + agent + '\'' +
                ", delDate='" + deliveryDate + '\'' +
                ", quality='" + quality + '\'' +
                ", country='" + country + '\'' +
                ", delType='" + deliveryType + '\'' +
                ", finalDest='" + finalDest + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", pos=" + pos +
                '}';
    }
}
