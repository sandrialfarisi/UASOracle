package com.example.uasoracle.model;
/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCustomer {

    @SerializedName("items")
    private List<CustomerItem> semuacustomer;

    public List<CustomerItem> getSemuacustomer() {
        return semuacustomer;
    }

    public void setSemuacustomer(List<CustomerItem> semuacustomer) {
        this.semuacustomer = semuacustomer;
    }


    @Override
    public String toString(){
        return
                "ResponseBarang{" +
                        "items = '" + semuacustomer + '\'' +
                        "}";
    }

}
