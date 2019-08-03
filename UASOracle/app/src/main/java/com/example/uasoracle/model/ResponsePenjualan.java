package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePenjualan {

    @SerializedName("items")
    private List<PenjualanItem> semuapenjualan;

    public List<PenjualanItem> getSemuapenjualan() {
        return semuapenjualan;
    }

    public void setSemuapenjualan(List<PenjualanItem> semuapenjualan) {
        this.semuapenjualan = semuapenjualan;
    }


    @Override
    public String toString(){
        return
                "ResponseBarang{" +
                        "items = '" + semuapenjualan + '\'' +
                        "}";
    }

}
