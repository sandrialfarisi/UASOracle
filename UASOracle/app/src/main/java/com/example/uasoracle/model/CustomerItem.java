package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

public class CustomerItem {

    @SerializedName("kd_customer")
    private String kd_customer;
    @SerializedName("username")
    private String username;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_tlp")
    private String no_tlp;

    public String getKd_customer() {
        return kd_customer;
    }

    public void setKd_customer(String kd_customer) {
        this.kd_customer = kd_customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    @Override
    public String toString(){
        return
                "SemuaPenjualanItem{" +
                        "kd_customer = '" + kd_customer + '\'' +
                        ",username = '" + username + '\'' +
                        ",alamat = '" + alamat + '\'' +
                        ",no_tlp = '" + no_tlp + '\'' +
                        "}";

    }
}
