package com.example.uasoracle.util.api;

/**
 * Created by Sandri Alfarisi.
 * github : https://github.com/sandrialfarisi
 */
public class UtilsApi {

    //ganti sesuai URL api masing-masing
    public static final String BASE_URL_API = "http://192.168.1.14:8888/apex/obe/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
