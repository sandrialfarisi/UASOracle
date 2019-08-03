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
import com.example.uasoracle.model.CustomerItem;
import com.example.uasoracle.model.ResponseCustomer;
import com.example.uasoracle.model.ResponseSupplier;
import com.example.uasoracle.model.SupplierItem;
import com.example.uasoracle.util.api.BaseApiService;
import com.example.uasoracle.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SupplierFragment extends Fragment {
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listNamasupplier = new ArrayList<String>();
    List<String> listAlamat = new ArrayList<String>();
    List<String> listNotlp = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier,container,false);

        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        TextView nama = (TextView) view.findViewById(R.id.namasupplier);
        TextView alamat = (TextView) view.findViewById(R.id.alamat);
        TextView notlp = (TextView) view.findViewById(R.id.nomortelpon);
        mContext = getContext();
        mApiService = UtilsApi.getAPIService();

        loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
        mApiService.getSemuaSupplier().enqueue(new Callback<ResponseSupplier>() {
            @Override
            public void onResponse(Call<ResponseSupplier> call, Response<ResponseSupplier> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<SupplierItem> semuasupplierItems = response.body().getSemuasupplier();

                    for (int i = 0; i < semuasupplierItems.size(); i++) {
                        String kode = semuasupplierItems.get(i).getId_supplier();
                        String nama = semuasupplierItems.get(i).getNm_supplier();
                        String alamat = semuasupplierItems.get(i).getAlamat();
                        String no_tlp = semuasupplierItems.get(i).getNo_tlp();
                        listSpinner.add(kode);
                        listNamasupplier.add(nama);
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
                    Toast.makeText(mContext, "Gagal mengambil data supplier", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSupplier> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedNama = listNamasupplier.get(position);
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
