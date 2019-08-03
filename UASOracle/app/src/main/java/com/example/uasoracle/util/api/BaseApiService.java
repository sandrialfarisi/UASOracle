package com.example.uasoracle.util.api;

/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
import com.example.uasoracle.model.ResponseBarang;
import com.example.uasoracle.model.ResponseCustomer;
import com.example.uasoracle.model.ResponsePembelian;
import com.example.uasoracle.model.ResponsePenjualan;
import com.example.uasoracle.model.ResponseSupplier;

import retrofit2.Call;
import retrofit2.http.GET;


public interface BaseApiService {

    @GET("barang")
    Call<ResponseBarang> getSemuaBarang();

    @GET("customer")
    Call<ResponseCustomer> getSemuaCustomer();

    @GET("pembelian")
    Call<ResponsePembelian> getSemuaPembelian();

    @GET("penjualan")
    Call<ResponsePenjualan> getSemuaPenjualan();

    @GET("supplier")
    Call<ResponseSupplier> getSemuaSupplier();
}
