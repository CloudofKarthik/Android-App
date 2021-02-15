package com.example.project1;

import java.util.Date;

public class Model {

    private String imageUrl;
    private String seller;
    private String seller_pwd;
    private Long seller_c;
    private Integer seller_id;
    private String seller_item;
    private Integer seller_price;
    private Integer current_price;
    private String seller_date;
    private String seller_finaldate;
    private String seller_finaltime;
    private int seller_status;
    private String buyer_name;
    private Long buyer_ph;
    public Model(){

    }
    public Model(String imageUrl){ this.imageUrl = imageUrl; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSeller_pwd() {
        return seller_pwd;
    }

    public void setSeller_pwd(String seller_pwd) {
        this.seller_pwd = seller_pwd;
    }

    public Long getSeller_c() {
        return seller_c;
    }

    public void setSeller_c(Long seller_c) {
        this.seller_c = seller_c;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_item() {
        return seller_item;
    }

    public void setSeller_item(String seller_item) {
        this.seller_item = seller_item;
    }

    public Integer getSeller_price() {
        return seller_price;
    }

    public void setSeller_price(Integer seller_price) {
        this.seller_price = seller_price;
    }

    public String getSeller_date() { return seller_date; }

    public void setSeller_date(String seller_date) { this.seller_date = seller_date; }

    public String getSeller_finaldate() { return seller_finaldate; }

    public void setSeller_finaldate(String seller_finaldate) { this.seller_finaldate = seller_finaldate; }

    public String getSeller_finaltime() { return seller_finaltime; }

    public void setSeller_finaltime(String seller_finaltime) { this.seller_finaltime = seller_finaltime; }

    public Integer getCurrent_price() {  return current_price; }

    public void setCurrent_price(Integer current_price) { this.current_price = current_price; }

    public int getSeller_status() { return seller_status; }

    public void setSeller_status(int seller_status) { this.seller_status = seller_status; }

    public String getBuyer_name() { return buyer_name; }

    public void setBuyer_name(String buyer_name) { this.buyer_name = buyer_name; }

    public Long getBuyer_ph() { return buyer_ph; }

    public void setBuyer_ph(Long buyer_ph) { this.buyer_ph = buyer_ph; }
}
