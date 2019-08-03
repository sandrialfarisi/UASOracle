package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSupplier {

    @SerializedName("items")
    private List<SupplierItem> semuasupplier;

    public List<SupplierItem> getSemuasupplier() {
        return semuasupplier;
    }

    public void setSemuasupplier(List<SupplierItem> semuasupplier) {
        this.semuasupplier = semuasupplier;
    }


    @Override
    public String toString(){
        return
                "ResponseBarang{" +
                        "items = '" + semuasupplier + '\'' +
                        "}";
    }

}
