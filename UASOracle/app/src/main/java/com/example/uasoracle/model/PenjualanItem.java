package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

public class PenjualanItem {
    @SerializedName("kd_transaksi")
    private String kd_transaksi;
    @SerializedName("kd_barang")
    private String kd_barang;
    @SerializedName("kd_customer")
    private String kd_customer;
    @SerializedName("tgl")
    private String tgl;
    @SerializedName("jml")
    private String jml;
    @SerializedName("harga")
    private String harga;

    public String getKd_transaksi() {
        return kd_transaksi;
    }

    public void setKd_transaksi(String kd_transaksi) {
        this.kd_transaksi = kd_transaksi;
    }

    public String getKd_barang() {
        return kd_barang;
    }

    public void setKd_barang(String kd_barang) {
        this.kd_barang = kd_barang;
    }

    public String getKd_customer() {
        return kd_customer;
    }

    public void setKd_customer(String kd_customer) {
        this.kd_customer = kd_customer;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJml() {
        return jml;
    }

    public void setJml(String jml) {
        this.jml = jml;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }





    @Override
    public String toString(){
        return
                "SemuaPenjualanItem{" +
                        "kd_transaksi = '" + kd_transaksi + '\'' +
                        ",kd_barang = '" + kd_barang + '\'' +
                        ",kd_customer = '" + kd_customer + '\'' +
                        ",tgl = '" + tgl + '\'' +
                        ",jml = '" + jml + '\'' +
                        ",harga = '" + harga + '\'' +
                        "}";

    }
}
