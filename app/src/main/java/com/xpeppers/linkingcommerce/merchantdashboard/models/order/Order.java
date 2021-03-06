package com.xpeppers.linkingcommerce.merchantdashboard.models.order;

import com.google.gson.annotations.SerializedName;

public class Order {

    private int id;
    private String title;
    @SerializedName(value="buyer_email")
    private String buyerEmail;
    private String status;
    @SerializedName(value="purchase_date")
    private String date;
    private Coupon coupon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyer_email) {
        this.buyerEmail = buyer_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public String getCouponCode() {
        return getCoupon().getCode();
    }

}
