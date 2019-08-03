# UAS Pemrosesan Data Tersebar

Aplikasi Pembelian dan Penjualan dengan menggunakan database Oracle, dengan System Administrator menggunakan CodeIgniter dan Interface data dari seluruh table menggunakan Mobile Apps (Android). Komunikasi data antar Aplikasi dan System Administrator menggunakan *RESTful Service* di oracle.



## Requirements

- [Virtual Box](https://www.virtualbox.org/wiki/Downloads) (Virtual Server)
- [Oracle Developer Day 11g](https://www.oracle.com/technetwork/database/enterprise-edition/databaseappdev-vm-161299.html) (Database)
- [Codeigniter](https://www.codeigniter.com/) (Framework PHP)

## Tutorial

### Database

Aplikasi ini memiliki 5 table, yaitu :

1. [Customer](#table-customer)
2. [Barang](#table-barang)
3. [Penjualan](#table-penjualan)
4. [Pembelian](#table-pembelian)
5. [Supplier](#table-supplier)

### *RESTful Services*

![RESTful Services](./Image/RestFull/CRUD.png)

PUT dan DELETE menggunakan {id} untuk mengidentifikasi data yang akan dieksekusi.  
Metode HTTP yang digunakan dalam aplikasi ini adalah:

| Method | Description |
| ------ | ------ |
| **GET** | Hanya dapat membaca _resource_ |
| **POST** | Digunakan untuk membuat _resource_ baru |
| **PUT** | Digunakan untuk memperbarui _resource_ yang sudah ada atau membuat _resource_ baru |
| **DELETE** | Digunakan untuk menghapus _resource_ yang sudah ada|

**Berikut ScreenShoot dari table di dalam Database :** 
#### Table Barang

![Table Barang!](./Image/Database/T_Barang.png "Table Barang")

#### Table Customer

![Table Customer!](./Image/Database/T_Customer.png "Table Customer")

#### Table Pembelian

![Table Pembelian!](./Image/Database/T_Pembelian.png "Table Pembelian")

#### Table Penjualan

![Table Penjualan!](./Image/Database/T_Penjualan.png "Table Penjualan")

#### Table Supplier

![Table Supplier!](./Image/Database/T_Supplier.png "Table Supplier")

#### *RESTfull Service* pada Barang

- **GET Barang**  
![GET](./Image/RestFull/Get_Barang.png)

- **POST Barang**  
![POST](./Image/RestFull/Post_Barang.png)
![POST Paramter](./Image/RestFull/Post_Barang_Parameter.png)

- **PUT Barang**  
![PUT](./Image/RestFull/Put_Barang.png)
![PUT Paramter](./Image/RestFull/Put_Barang_Parameter.png)

- **DELETE Barang**  
![DELETE](./Image/RestFull/Delete_Barang.png)


#### *RESTful Service* pada Customer

- **GET Customer**  
![GET](./Image/RestFull/Get_Customer.png)

- **POST Customer**  
![POST](./Image/RestFull/Post_Customer.png)
![POST Paramter](./Image/RestFull/Post_Customer_Parameter.png)

- **PUT Customer**  
![PUT](./Image/RestFull/Put_Customer.png)
![PUT Paramter](./Image/RestFull/Put_Customer_Parameter.png)

- **DELETE Customer**  
![DELETE](./Image/RestFull/Delete_Customer.png)

#### *RESTful Service* pada Penjualan

- **GET Penjualan**  
![GET](./Image/RestFull/Get_Penjualan.png)

- **POST Penjualan**  
![POST](./Image/RestFull/Post_Penjualan.png)
![POST Paramter](./Image/RestFull/Post_Penjualan_Paramater.png)

#### *RESTful Service* pada Pembelian

- **GET Pembelian**  
![GET](./Image/RestFull/Get_Pembelian.png)

- **POST Pembelian**  
![POST](./Image/RestFull/Post_Pembelian.png)
![POST Paramter](./Image/RestFull/Post_Pembelian_Parameter.png)

#### *RESTful Service* pada Supplier

- **GET Supplier**  
![GET](./Image/RestFull/Get_Supplier.png)

- **POST Supplier**  
![POST](./Image/RestFull/Post_Supplier.png)

- **PUT Supplier**  
![PUT](./Image/RestFull/Put_Supplier.png)

- **DELETE Supplier**  
![DELETE](./Image/RestFull/Delete_Supplier.png)

### CodeIgniter

[Script](./oracle-uas/application/libraries/Api.php) dibawah ini merupakan script php yang digunakan untuk mengakses *RESTful Services* dari Oracle menggunakan library [Goutte](https://github.com/FriendsOfPHP/Goutte).

```php
use GuzzleHttp\Client;

defined('BASEPATH') or exit('No direct script access allowed');

class Api
{
    private $client;

    public function __construct()
    {
        // base url yang digunakan untuk mengakses RESTful API (Ganti URL Sesuai dengan URL milik kita)
        $this->client = new Client(['base_uri' => 'http://192.168.43.75:8888/apex/obe/']);
    }

    public function request($method, $uri, $data = [])
    {
        // data di convert menjadi data JSON
        $options['json'] = $data;

        // jika metode HTTP nya adalah DELETE maka response yang diberikan adalah status code nya
        if ($method == 'delete') {
            $request = $this->client->request($method, $uri);
            return $request->getStatusCode();
        }

        $request = $this->client->request($method, $uri, $options);

        // response yang diberikan berupa content nya
        return $request->getBody()->getContents();
    }
}
```

#### Tampilan Web

- Barang
![List Barang](./Image/SSweb/barang.png)

- Customer
![List Customer](./Image/SSweb/cutomer.png)

- Penjualan
![List Penjualan](./Image/SSweb/penjualan.png)

- Pembelian
![List Pembelian](./Image/SSweb/pembelian.png)

- Supplier
![List Supplier](./Image/SSweb/supplier.png)

## Tutorial Android
Pada aplikasi Android menampilkan seluruh data dari Tabel yang ada di dalam database menggunakan fungsi **GET** pada Restfull API yang sudah dibuat
## Requirements
Library yang digunakan : 
- [Retrofit](https://square.github.io/retrofit/) (Library HTTP API)
- [Android Studio](https://developer.android.com/studio) (Android IDE)

### Java

[File Java](./UASOracle/app/src/main/java/com/example/uasoracle/util/api) dibawah ini merupakan 3 Script Java yang digunakan untuk mengakses *RESTful Services* dari Oracle menggunakan library [Retrofit](https://square.github.io/retrofit/).


#### BaseApiService.java
```java
package com.example.uasoracle.util.api;  
  
/**  
 * Created by Sandri Alfarisi. * github : https://github.com/sandrialfarisi */import com.example.uasoracle.model.ResponseBarang;  
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
```

#### RetrofitClient.Java
```java
package com.example.uasoracle.util.api;  
/**  
 * Created by Sandri Alfarisi. * github : https://github.com/sandrialfarisi */import okhttp3.OkHttpClient;  
import okhttp3.logging.HttpLoggingInterceptor;  
import retrofit2.Retrofit;  
import retrofit2.converter.gson.GsonConverterFactory;  
  
public class RetrofitClient {  
  
    private static Retrofit retrofit = null;  
  
    public static Retrofit getClient(String baseUrl){  
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();  
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);  
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();  
  
        if (retrofit == null){  
            retrofit = new Retrofit.Builder()  
                    .baseUrl(baseUrl)  
                    .addConverterFactory(GsonConverterFactory.create())  
                    .client(client)  
                    .build();  
        }  
        return retrofit;  
    }  
}
```


#### UtilsApi.java
```java
package com.example.uasoracle.util.api;  
  
/**  
 * Created by Sandri Alfarisi. * github : https://github.com/sandrialfarisi */public class UtilsApi {  
  
    //ganti sesuai URL api masing-masing  
  public static final String BASE_URL_API = "http://192.168.1.14:8888/apex/obe/";  
  
    public static BaseApiService getAPIService(){  
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);  
    }  
}
```

#### Tampilan Android

- Barang
![Data Barang](./Image/SSandroid/Barang.png)

- Customer
![Data Customer](../Image/SSandroid/Customer.png)

- Penjualan
![Data Penjualan](./Image/SSandroid/Penjualan.png)

- Pembelian
![Data Pembelian](./Image/SSandroid/Pembelian.png)

- Supplier
![Data Supplier](./Image/SSandroid/Supplier.png)




### License

Copyright © 2019, [Sandri Alfarisi](https://github.com/sandrialfarisi).
