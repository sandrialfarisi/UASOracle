package com.example.uasoracle.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uasoracle.R;
import com.example.uasoracle.model.BarangItem;
import com.example.uasoracle.model.CustomerItem;
import com.example.uasoracle.model.ResponseBarang;
import com.example.uasoracle.model.ResponseCustomer;
import com.example.uasoracle.util.api.BaseApiService;
import com.example.uasoracle.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomerFragment extends Fragment {
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listNama = new ArrayList<String>();
    List<String> listAlamat = new ArrayList<String>();
    List<String> listNotlp = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer,container,false);

        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        TextView nama = (TextView) view.findViewById(R.id.namacustomer);
        TextView alamat = (TextView) view.findViewById(R.id.alamat);
        TextView notlp = (TextView) view.findViewById(R.id.nomortelpon);
        mContext = getContext();
        mApiService = UtilsApi.getAPIService();

        loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
        mApiService.getSemuaCustomer().enqueue(new Callback<ResponseCustomer>() {
            @Override
            public void onResponse(Call<ResponseCustomer> call, Response<ResponseCustomer> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<CustomerItem> semuacustomerItems = response.body().getSemuacustomer();

                    for (int i = 0; i < semuacustomerItems.size(); i++) {
                        String kode = semuacustomerItems.get(i).getKd_customer();
                        String nama = semuacustomerItems.get(i).getUsername();
                        String alamat = semuacustomerItems.get(i).getAlamat();
                        String no_tlp = semuacustomerItems.get(i).getNo_tlp();
                        listSpinner.add(kode);
                        listNama.add(nama);
                        listAlamat.add(alamat);
                        listNotlp.add(no_tlp);
                    }

                    spin.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listSpinner));
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                                android.R.layout.simple_spinner_item, listSpinner);
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinner.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data customer", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCustomer> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedNama = listNama.get(position);
                String selectedAlamat = listAlamat.get(position);
                String selectedNotlp = listNotlp.get(position);
                nama.setText(selectedNama);
                alamat.setText(selectedAlamat);
                notlp.setText(selectedNotlp);
                Toast.makeText(mContext, "Kamu Memilih " + selectedNama, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}