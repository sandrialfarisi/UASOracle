package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

public class SupplierItem {
    @SerializedName("id_supplier")
    private String id_supplier;
    @SerializedName("nm_supplier")
    private String nm_supplier;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("no_tlp")
    private String no_tlp;

    public String getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getNm_supplier() {
        return nm_supplier;
    }

    public void setNm_supplier(String nm_supplier) {
        this.nm_supplier = nm_supplier;
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
                        "id_supplier = '" + id_supplier + '\'' +
                        ",nm_supplier = '" + nm_supplier + '\'' +
                        ",alamat = '" + alamat + '\'' +
                        ",no_tlp = '" + no_tlp + '\'' +
                        "}";

    }
}
