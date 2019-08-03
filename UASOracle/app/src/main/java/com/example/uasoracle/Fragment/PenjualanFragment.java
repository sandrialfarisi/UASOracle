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
import com.example.uasoracle.model.PembelianItem;
import com.example.uasoracle.model.PenjualanItem;
import com.example.uasoracle.model.ResponsePembelian;
import com.example.uasoracle.model.ResponsePenjualan;
import com.example.uasoracle.util.api.BaseApiService;
import com.example.uasoracle.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PenjualanFragment extends Fragment {
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    List<String> listSpinner = new ArrayList<String>();
    List<String> listKdbarang = new ArrayList<String>();
    List<String> listKdcustomer = new ArrayList<String>();
    List<String> listTgl = new ArrayList<String>();
    List<String> listJml = new ArrayList<String>();
    List<String> listHarga = new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penjualan,container,false);

        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        TextView kd_barang = (TextView) view.findViewById(R.id.kodebarang);
        TextView kd_customer = (TextView) view.findViewById(R.id.kodecustomer);
        TextView tgl = (TextView) view.findViewById(R.id.tanggal);
        TextView jml = (TextView) view.findViewById(R.id.jumlah);
        TextView harga = (TextView) view.findViewById(R.id.harga);
        mContext = getContext();
        mApiService = UtilsApi.getAPIService();

        loading = ProgressDialog.show(mContext, null, "harap tunggu...", true, false);
        mApiService.getSemuaPenjualan().enqueue(new Callback<ResponsePenjualan>() {
            @Override
            public void onResponse(Call<ResponsePenjualan> call, Response<ResponsePenjualan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    List<PenjualanItem> semuapenjualanItems = response.body().getSemuapenjualan();

                    for (int i = 0; i < semuapenjualanItems.size(); i++) {
                        String kode = semuapenjualanItems.get(i).getKd_transaksi();
                        String kd_barang = semuapenjualanItems.get(i).getKd_barang();
                        String kd_customer = semuapenjualanItems.get(i).getKd_customer();
                        String tgl = semuapenjualanItems.get(i).getTgl();
                        String jml = semuapenjualanItems.get(i).getJml();
                        String harga = semuapenjualanItems.get(i).getHarga();
                        listSpinner.add(kode);
                        listKdbarang.add(kd_barang);
                        listKdcustomer.add(kd_customer);
                        listTgl.add(tgl);
                        listJml.add(jml);
                        listHarga.add(harga);
                    }

                    spin.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listSpinner));
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                                android.R.layout.simple_spinner_item, listSpinner);
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinner.setAdapter(adapter);
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data penjualan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePenjualan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedKdbarang = listKdbarang.get(position);
                String selectedKdcustomer = listKdcustomer.get(position);
                String selectedTanggal = listTgl.get(position);
                String selectedJumlah = listJml.get(position);
                String selectedHarga = listHarga.get(position);
                kd_barang.setText(selectedKdbarang);
                kd_customer.setText(selectedKdcustomer);
                tgl.setText(selectedTanggal);
                jml.setText(selectedJumlah);
                harga.setText(selectedHarga);
                Toast.makeText(mContext, "Kamu Memilih " + selectedKdbarang, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
