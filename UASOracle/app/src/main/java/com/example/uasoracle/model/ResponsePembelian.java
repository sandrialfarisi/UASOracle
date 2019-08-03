package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePembelian {

    @SerializedName("items")
    private List<PembelianItem> semuapembelian;

    public List<PembelianItem> getSemuapembelian() {
        return semuapembelian;
    }

    public void setSemuapembelian(List<PembelianItem> semuapembelian) {
        this.semuapembelian = semuapembelian;
    }


    @Override
    public String toString(){
        return
                "ResponseBarang{" +
                        "items = '" + semuapembelian + '\'' +
                        "}";
    }

}
